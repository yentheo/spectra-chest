package one.spectra.better_chests.abstractions.event;

public interface EventHandler<T> {
    void handle(T event);
}
