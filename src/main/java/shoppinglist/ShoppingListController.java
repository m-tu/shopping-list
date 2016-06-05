package shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shoppinglist.model.ShoppingList;
import shoppinglist.model.ShoppingListItem;

import java.util.List;

@RestController
public class ShoppingListController {
  @Autowired
  ShoppingListService shoppingListService;

  @RequestMapping("/{listId}")
  public ShoppingList list(@PathVariable String listId) {
    return shoppingListService.getList(listId);
  }

  @RequestMapping("/newlist")
  public ShoppingList newList(@RequestParam(required = false) String name) {
    return shoppingListService.createList(name);
  }

  @RequestMapping("/{listId}/addItem")
  public String addItem(@PathVariable String listId, @RequestParam String item) {
    shoppingListService.addItem(listId, item);
    return "";
  }


}
