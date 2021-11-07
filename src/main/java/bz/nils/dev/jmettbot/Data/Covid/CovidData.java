package bz.nils.dev.jmettbot.Data.Covid;

public class CovidData {
    private final String cases7per100k;
    private final String lastUpdate;

    public CovidData(String cases7per100k, String lastUpdate) {
        this.cases7per100k = cases7per100k;
        this.lastUpdate = lastUpdate;
    }

    public String getCases7per100k() {
        return cases7per100k;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
