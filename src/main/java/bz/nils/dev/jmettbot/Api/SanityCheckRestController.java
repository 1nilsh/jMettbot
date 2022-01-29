package bz.nils.dev.jmettbot.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/sanitycheck")
@RestController
public class SanityCheckRestController {

    @GetMapping
    public String get() {
        return "GET method called";
    }

    @PostMapping
    public String post() {
        return "POST method called";
    }
}
