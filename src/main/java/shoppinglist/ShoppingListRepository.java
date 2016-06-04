package shoppinglist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shoppinglist.model.ShoppingList;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList, String> {

}
