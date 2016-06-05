package shoppinglist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  @RequestMapping("/")
  public String index() {
    return "html/index.html";
  }

  @RequestMapping("/list/{listId}")
  public String list(@PathVariable("listId") String listId) {
    System.out.println("Getting list by id: " + listId);
    return "html/index.html";
  }

}
