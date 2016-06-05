package shoppinglist.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ShoppingListItem {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  public long id;
  @JoinColumn(name = "listId")
  @ManyToOne(fetch = FetchType.LAZY)
  ShoppingList list;
  public boolean isBought;
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

  public void buyItem() {
    if (!isBought) {
      isBought = true;
      boughtDate = new Date();
    }
  }
}
