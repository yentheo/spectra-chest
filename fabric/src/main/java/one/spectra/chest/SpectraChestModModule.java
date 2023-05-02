package one.spectra.chest;

import com.google.inject.AbstractModule;

import one.spectra.chest.commands.CommandHub;
import one.spectra.chest.commands.FeaturesCommand;
import one.spectra.chest.communication.receivers.FeatureReceiver;
import one.spectra.chest.communication.receivers.ReceiverHub;

public class SpectraChestModModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FeatureReceiver.class);
        bind(ReceiverHub.class).asEagerSingleton();
        bind(FeatureService.class).asEagerSingleton();

        bind(FeaturesCommand.class);
        bind(CommandHub.class).asEagerSingleton();
    }
}
