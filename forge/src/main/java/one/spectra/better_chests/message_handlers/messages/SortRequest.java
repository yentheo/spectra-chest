package one.spectra.better_chests.message_handlers.messages;

public class SortRequest implements Message {
    public boolean sortPlayerInventory = false;

    public SortRequest() {
    }

    public SortRequest(boolean sortPlayerInventory) {
        this.sortPlayerInventory = sortPlayerInventory;
    }
}
