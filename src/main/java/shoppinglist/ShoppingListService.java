package shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppinglist.model.ShoppingList;
import shoppinglist.model.ShoppingListItem;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShoppingListService {
  @Autowired
  ShoppingListRepository repository;
  @Autowired
  ShoppingListItemRepository itemRepository;

  public ShoppingList createList(String name) {
    ShoppingList list = new ShoppingList(name);
    return repository.save(list);
  }

  public ShoppingListItem addItem(String listId, String item) {
    ShoppingList list = repository.findOne(listId);
    ShoppingListItem newItem = new ShoppingListItem(item, list);
    itemRepository.save(newItem);
    return newItem;
  }

  public List<ShoppingListItem> getItems(String listId) {
    return repository.findOne(listId).items;
  }

  public ShoppingList getList(String listId) {
    return repository.findWithNonDeletedItems(listId);
  }

  @Transactional
  public void deleteItem(String listId, long itemId) {
    itemRepository.deleteByIdAndListId(itemId, listId);
  }

  public ShoppingListItem buyItem(String listId, long itemId) {
    ShoppingListItem item = itemRepository.findByIdAndListId(itemId, listId);
    if (item != null) {
      item.buyItem();
      itemRepository.save(item);
    }
    return item;
  }
}
