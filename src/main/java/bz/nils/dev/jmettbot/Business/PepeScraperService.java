package bz.nils.dev.jmettbot.Business;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PepeScraperService {
    private final String COLLECTION_URL = "https://rare-pepe.com/";
    private final String USER_SUBMISSIONS_URL = "https://rare-pepe.com/user-submissions/";

    private final Pattern wordpressPreviewPattern = Pattern.compile("(-\\d{1,4}x\\d{1,4})");

    public List<String> getPepes() {
        List<String> collection = crawl(COLLECTION_URL);
        List<String> userSubmissions = crawl(USER_SUBMISSIONS_URL);

        collection.addAll(userSubmissions);

        return collection;
    }

    private List<String> crawl(String url) {
        List<String> result = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            List<Element> elements = doc.select("#gallery-1 figure img");
            for (Element element : elements) {
                String imgUrl = element.attr("src");
                imgUrl = wordpressPreviewPattern.matcher(imgUrl).replaceAll("");
                result.add(imgUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
