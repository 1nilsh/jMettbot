package bz.nils.dev.jmettbot.Api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequestMapping("api/update")
@RestController
public class TelegramUpdateRestController {
    @PostMapping
    public void postUpdate(Update update) {

    }
}
