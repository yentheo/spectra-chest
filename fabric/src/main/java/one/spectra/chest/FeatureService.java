package one.spectra.chest;

public class FeatureService {

    private String[] _features;

    public void registerServerFeatures(String[] features) {
        _features = features;
    }

    public String[] getServerFeatures() {
        return _features;
    }
}
