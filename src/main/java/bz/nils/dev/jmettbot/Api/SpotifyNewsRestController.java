package bz.nils.dev.jmettbot.Api;

import bz.nils.dev.jmettbot.Business.SpotifyNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/spotify/{apikey}")
@RestController
public class SpotifyNewsRestController {
    private SpotifyNewsService spotifyNewsService;

    @Autowired
    public SpotifyNewsRestController(SpotifyNewsService spotifyNewsService) {
        this.spotifyNewsService = spotifyNewsService;
    }

    @PostMapping
    public void postNews(@RequestBody SpotifyNews news) {
        spotifyNewsService.sendSpotifyNewsNotification(news.message);
    }
}

class SpotifyNews {
    public String message;
}