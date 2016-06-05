(function (w) {
	console.log('app.js');
	var APP = {
		items : [],
		root : document.getElementById('content')
	};
 	var d = document,
			byId = function(id) {
				return d.getElementById(id);
			};

	var routeInfo = window.location.pathname.split('/');
	if(routeInfo[1].length > 0) {
		console.log('listId present: ', routeInfo[1]);
		loadTemplate('list');
	} else {
		console.log('no list id: ', routeInfo);
		loadTemplate('welcome');
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
							req('GET', '/newList', function (res) {
								console.log('new list res: ', res);
							})
						}
					});

				});
				break;
		}
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

