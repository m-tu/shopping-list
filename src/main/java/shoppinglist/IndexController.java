package shoppinglist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

  @RequestMapping("/")
  public String index() {
    return "/html/index.html";
  }

  @RequestMapping(value = "/list/{someid}")
  public String list(@PathVariable("someid") String someid) {
    return "/html/index.html";
  }

}
