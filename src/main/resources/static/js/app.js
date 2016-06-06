(function (w) {
	var APP = {
		listId: null,
		items : [],
		root : document.getElementById('content')
	};
	var d = document,
			byId = function(id) {
				return d.getElementById(id);
			};

	var routeInfo = window.location.pathname.split('/');

	if(routeInfo[2].length > 0) {
		console.log('listId present: ', routeInfo[2]);
		APP.listId = routeInfo[2];
		loadTemplate('list');
	} else {
		console.log('no list id: ', routeInfo);
		loadTemplate('welcome');
	}

	function getSearchParams() {
		var params = location.search.substring(1).split('&');
		var result = {};
		params.forEach(function (p) {
			p = p.split('=');
			result[p[0]] = p[1];
		});

		return result;
	}

	function loadTemplate(name) {
		switch (name) {
			case 'welcome':
				req('GET', '/html/welcome.html', function (response) {
					APP.root.innerHTML = response;
					var name = byId('listName');
					byId('btnCreateList').addEventListener('click', function () {
						if(name.value.length !== 0) {
							console.log('creating new list');
							req('GET', '/newlist', function (res) {
								res = JSON.parse(res);
								// APP.items = res.items || [];
								// APP.name = res.name;
								APP.listId = res.id;
								console.log('res.id:', res.id);
								window.history.pushState({}, "", "/list/" + res.id);
								loadTemplate('list')
							})
						}
					});

				});
				break;
			case 'list':
				req('GET', '/html/list.html', function (response) {
					APP.root.innerHTML = response;
					APP.itemList = byId('itemList');

					if(APP.listId) {
						req('GET', '/' + APP.listId, function (response) {
							if(response.length !== 0) {
								response = JSON.parse(response);
								console.log('response', response);

								response.items.forEach(function (item) {
									addItem(item.item);
								})
							}
						});
					}


					var itemInput = byId('inputAddItem');

					byId('btnAddItem').addEventListener('click', function () {
						if(itemInput.value.length > 0) {
							APP.items.push(itemInput.value);
							persistItem(itemInput.value);
						}
					});

				});
				break;
		}
	}

	function persistItem(val) {
		req('GET', '/' +APP.listId + '/addItem?item=' + val, function () {
			addItem(val);
		});
	}

	function addItem(val) {
		var item = d.createElement('li');
		item.innerText = val;
		APP.itemList.appendChild(item);
		item.addEventListener('click', function () {
			console.log('remove item');
			item.remove();
		});
	}

	function req(method, url, cb) {
		var xhr = new XMLHttpRequest();
		xhr.open(method, url);
		xhr.addEventListener('load', function (res) {
			cb(xhr.responseText)
		});
		xhr.send();
	}

	w.APP = {
		req: req
	}
})(window);

