package bz.nils.dev.jmettbot.Data.Covid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CovidApiResponse {
    public ArrayList<Feature> features;
}

class Feature {
    public Attributes attributes;
}

class Attributes {
    public Integer cases;
    public Integer deaths;
    public Float cases_per_100k;
    public String county;
    public String last_update;
    public Float cases7_per_100k;
    public String cases7_per_100k_txt;
}
