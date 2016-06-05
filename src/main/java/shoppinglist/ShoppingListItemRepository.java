package shoppinglist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shoppinglist.model.ShoppingListItem;

import java.util.List;

@Repository
public interface ShoppingListItemRepository extends CrudRepository<ShoppingListItem, Long> {
  List<ShoppingListItem> findByListIdAndIsBoughtFalse(String listId);

}
