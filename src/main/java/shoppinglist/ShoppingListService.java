package shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import shoppinglist.model.ShoppingList;
import shoppinglist.model.ShoppingListItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShoppingListService {
  @Autowired
  ShoppingListRepository repository;

  public ShoppingList createList(String name) {
    ShoppingList list = new ShoppingList(name);
    return repository.save(list);
  }

  public void addItem(String listId, String item) {
    ShoppingList list = repository.findOne(listId);
    list.items.add(new ShoppingListItem(item, list));
    repository.save(list);
  }

  public List<ShoppingListItem> getItems(String listId) {
    return repository.findOne(listId).items;
  }
}
