package bz.nils.dev.jmettbot.Api;

import bz.nils.dev.jmettbot.Business.DailyBulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/daily-bulletin/${mettbot.bot.apitoken}")
@RestController
public class DailyBulletinRestController {
    private final DailyBulletinService dailyBulletinService;

    @Autowired
    public DailyBulletinRestController(DailyBulletinService dailyBulletinService) {
        this.dailyBulletinService = dailyBulletinService;
    }

    @GetMapping
    public void triggerNotification() {
        dailyBulletinService.sendBulletin();
    }
}
