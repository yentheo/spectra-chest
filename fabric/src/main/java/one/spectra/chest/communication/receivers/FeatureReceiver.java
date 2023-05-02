package one.spectra.chest.communication.receivers;

import com.google.inject.Inject;

import net.minecraft.util.Identifier;
import one.spectra.chest.FeatureService;

public class FeatureReceiver implements Receiver<String[]> {
    public Identifier getChannel() {
        return new Identifier("spectra-chest_client", "features");
    }

    private FeatureService _featureService;

    @Inject
    public FeatureReceiver(FeatureService featureService) {
        _featureService = featureService;
    }

    @Override
    public void handle(String[] message) {
        _featureService.registerServerFeatures(message);
    }
}
