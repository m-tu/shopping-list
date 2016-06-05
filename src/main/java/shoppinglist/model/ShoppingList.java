package shoppinglist.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class ShoppingList {
  @Id
  public String id;
  @NotNull
  public Date creationDate;
  public String name;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "list")
  public List<ShoppingListItem> items;

  public ShoppingList() {
    creationDate = new Date();
    id = String.valueOf(new Date().getTime());

  }

  public ShoppingList(String name) {
    this();
    this.name = name;
  }
}
