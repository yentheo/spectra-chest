package one.spectra.better_chests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import one.spectra.better_chests.abstractions.ItemStack;
import one.spectra.better_chests.inventory.Inventory;
import one.spectra.better_chests.inventory.InventoryCreator;
import one.spectra.better_chests.inventory.fillers.Filler;
import one.spectra.better_chests.inventory.fillers.InventoryFillerProvider;

@ExtendWith(MockitoExtension.class)
public class SorterTests {
    @Test
    void sort_sortsTheGivenInventory() {
        var inventoryFillerProvider = mock(InventoryFillerProvider.class);
        var inventoryFactory = mock(InventoryCreator.class);
        var filler = mock(Filler.class);
        var tempInventory = mock(Inventory.class);
        var myInventory = mock(Inventory.class);

        List<ItemStack> fakeContents = new ArrayList<ItemStack>();
        var itemStackOne = new FakeItemStack(5, "Wood");
        var itemStackTwo = new FakeItemStack(30, "Iron");
        fakeContents.add(itemStackOne);
        fakeContents.add(itemStackTwo);

        when(inventoryFillerProvider.getInventoryFiller(eq(myInventory), anyList())).thenReturn(filler);

        when(inventoryFactory.create(any(Integer.class))).thenReturn(tempInventory);
        when(myInventory.getItemStacks()).thenReturn(fakeContents);
        when(tempInventory.getItemStacks()).thenReturn(fakeContents);

        var sorter = new Sorter(inventoryFactory, inventoryFillerProvider, mock(Logger.class));

        sorter.sort(myInventory, false, false);

        verify(myInventory).getItemStacks();
        verify(myInventory).clear();
    }
}