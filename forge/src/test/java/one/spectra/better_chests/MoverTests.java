package one.spectra.better_chests;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import one.spectra.better_chests.abstractions.ItemStack;
import one.spectra.better_chests.inventory.Inventory;
import one.spectra.better_chests.inventory.InventoryCreator;

@ExtendWith(MockitoExtension.class)
public class MoverTests {

    private Mover _sut;

    private InventoryCreator _inventoryFactory;

    @BeforeEach
    public void setUp() {
        _inventoryFactory = mock(InventoryCreator.class);

        _sut = new Mover(_inventoryFactory);
    }

    @Test
    public void move_movesItemsFromCopiedDestinationToRealDestination() {
        var sourceInventory = mock(Inventory.class);
        var destinationInventory = mock(Inventory.class);

        var copiedSourceInventory = mock(Inventory.class);
        var copiedDestinationInventory = mock(Inventory.class);

        when(_inventoryFactory.create(sourceInventory)).thenReturn(copiedSourceInventory);
        when(_inventoryFactory.create(destinationInventory)).thenReturn(copiedDestinationInventory);

        var sourceItemStacks = new ArrayList<ItemStack>();
        sourceItemStacks.add(new FakeItemStack(33, "Wood"));
        var destinationItemStacks = new ArrayList<ItemStack>();
        destinationItemStacks.add(new FakeItemStack(64, "Wood"));
        destinationItemStacks.add(new FakeItemStack(64, "Wood"));
        destinationItemStacks.add(new FakeItemStack(64, "Wood"));
        destinationItemStacks.add(new FakeItemStack(64, "Wood"));
        destinationItemStacks.add(new FakeItemStack(64, "Iron"));

        when(copiedSourceInventory.getItemStacks()).thenReturn(sourceItemStacks);
        when(copiedDestinationInventory.getItemStacks()).thenReturn(destinationItemStacks);

        _sut.move(sourceInventory, destinationInventory);

        verify(sourceInventory).putInSlot(eq(0), argThat(x -> x.getAmount() == 33 && x.getMaterialKey() == "Wood"));
        verify(destinationInventory).putInSlot(eq(0), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(destinationInventory).putInSlot(eq(1), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(destinationInventory).putInSlot(eq(2), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(destinationInventory).putInSlot(eq(3), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(destinationInventory).putInSlot(eq(4), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Iron"));
    }

}
