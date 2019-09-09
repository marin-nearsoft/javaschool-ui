package JavaSchool.ShippingApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class TypeController {
    @RequestMapping("/type")
    public List<String> GetTypes() {
        List<String> list = new LinkedList<>();

        list.add("Type 1");
        list.add("Type 2");
        list.add("Type 3");

        return list;
    }
}