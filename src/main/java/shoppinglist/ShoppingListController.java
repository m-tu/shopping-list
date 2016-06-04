package shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shoppinglist.model.ShoppingList;
import shoppinglist.model.ShoppingListItem;

import java.util.List;

@RestController
public class ShoppingListController {
  @Autowired
  ShoppingListService shoppingListService;
  @RequestMapping("/")
  public String index() {
    ShoppingList list = shoppingListService.createList("My shiny list");

    return "Created list with id " + list.id + " and name " + list.name;
  }

  @RequestMapping("/{listId}")
  public String list(@PathVariable String listId) {
    shoppingListService.addItem(listId, "Shiny new item");
    List<ShoppingListItem> items = shoppingListService.getItems(listId);
    StringBuilder sb = new StringBuilder("List now has items:\n");
    items.forEach(item -> sb.append(item.item).append("\n"));
    return sb.toString();
  }
}
