package shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shoppinglist.model.ShoppingList;
import shoppinglist.model.ShoppingListItem;

@RestController
public class ShoppingListController {
  @Autowired
  ShoppingListService shoppingListService;

  @RequestMapping("/{listId}")
  public ShoppingList list(@PathVariable String listId) {
    System.out.printf("Getting list: " + listId);
    return shoppingListService.getList(listId);
  }

  @RequestMapping("/newlist")
  public ShoppingList newList(@RequestParam(required = false) String name) {
    System.out.println("Creating list with name: " + name);
    return shoppingListService.createList(name);
  }

  @RequestMapping("/{listId}/addItem")
  public ShoppingListItem addItem(@PathVariable String listId, @RequestParam String item) {
    return shoppingListService.addItem(listId, item);
  }

  @RequestMapping("/{listId}/deleteItem")
  public void deleteItem(@PathVariable String listId, @RequestParam long itemId) {
    shoppingListService.deleteItem(listId, itemId);
  }

  @RequestMapping("/{listId}/buyItem")
  public ShoppingListItem buyItem(@PathVariable String listId, @RequestParam long itemId) {
    return shoppingListService.buyItem(listId, itemId);
  }


}
