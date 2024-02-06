package seacrest.bank_app;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Client {
    @GetMapping
    public String start() {
        return "index";
    }
}
