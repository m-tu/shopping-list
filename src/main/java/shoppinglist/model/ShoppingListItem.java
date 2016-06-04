package shoppinglist.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ShoppingListItem {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  long id;
  @JoinColumn(name = "listId")
  @ManyToOne(fetch = FetchType.LAZY)
  ShoppingList list;
  boolean isBought;
  public String item;
  public Date boughtDate;
  public Date addedDate;

  public ShoppingListItem() {

  }

  public ShoppingListItem(String item, ShoppingList list) {
    this.item = item;
    this.addedDate = new Date();
    this.list = list;
  }
}
