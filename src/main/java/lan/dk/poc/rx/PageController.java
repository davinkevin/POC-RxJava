package lan.dk.poc.rx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kevin on 29/04/2016 for POC-RxJava
 */
@Controller
public class PageController {

    @RequestMapping("")
    public String page(){
        return "index";
    }
}
