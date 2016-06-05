package shoppinglist;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shoppinglist.model.ShoppingList;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList, String> {

  @Query("select sl from ShoppingList sl left join fetch sl.items i where sl.id=:id and i.isBought=false")
  ShoppingList findWithNonDeletedItems(@Param("id") String id);

}
