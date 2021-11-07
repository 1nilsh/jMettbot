package bz.nils.dev.jmettbot.Data.Covid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CovidDataService {
    private final String REST_URI = "https://services7.arcgis.com/mOBPykOjAyBO2ZKk/arcgis/rest/services/RKI_Landkreisdaten/FeatureServer/0/query?where=county = 'SK HAMBURG' OR county = 'LK LÜNEBURG'&outFields=cases,deaths,cases_per_100k,county,last_update,cases7_per_100k,cases7_per_100k_txt&returnGeometry=false&outSR=4326&f=json";

    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public CovidDataService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public Map<String, CovidData> getCovidData() {
        Map<String, CovidData> resultMap = new HashMap<>();

        CovidApiResponse response = makeRequest();

        for (Feature feature : response.features) {
            String key = "unknown";
            if (feature.attributes.county.equals("SK Hamburg")) {
                key = "HH";
            }
            if (feature.attributes.county.equals("LK Lüneburg")) {
                key = "LG";
            }
            resultMap.put(
                    key,
                    new CovidData(
                            feature.attributes.cases7_per_100k_txt,
                            feature.attributes.last_update
                    )
            );

        }

        return resultMap;
    }

    private CovidApiResponse makeRequest() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        return restTemplate.getForObject(REST_URI, CovidApiResponse.class);
    }
}
