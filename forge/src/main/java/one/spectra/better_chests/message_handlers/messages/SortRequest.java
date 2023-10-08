package one.spectra.better_chests.message_handlers.messages;

public class SortRequest implements Message {
    public boolean sortPlayerInventory = false;
    public boolean defaultSpread = false;
    public boolean defaultAlphabeticalSort = false;

    public SortRequest() {
    }

    public SortRequest(boolean sortPlayerInventory, boolean defaultSpread, boolean defaultAlphabeticalSort) {
        this.sortPlayerInventory = sortPlayerInventory;
        this.defaultSpread = defaultSpread;
        this.defaultAlphabeticalSort = defaultAlphabeticalSort;
    }
}
