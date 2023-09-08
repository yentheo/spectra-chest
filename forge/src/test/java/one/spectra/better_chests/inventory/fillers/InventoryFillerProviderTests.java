package one.spectra.better_chests.inventory.fillers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import one.spectra.better_chests.inventory.Inventory;
import one.spectra.better_chests.abstractions.ItemStack;

@ExtendWith(MockitoExtension.class)
public class InventoryFillerProviderTests {

    @Test
    public void getInventoryFiller_WhenNoneOfTheSetCanFill_ReturnsDefaultFiller() {
        var inventory = mock(Inventory.class);
        var groups = new ArrayList<List<ItemStack>>();

        var fillers = new ArrayList<Filler>();
        var fillerOne = mock(Filler.class);
        when(fillerOne.canFill(inventory, groups)).thenReturn(false);
        fillers.add(fillerOne);
        var fillerTwo = mock(Filler.class);
        when(fillerTwo.canFill(inventory, groups)).thenReturn(false);
        fillers.add(fillerTwo);
        var fillerThree = mock(Filler.class);
        when(fillerThree.canFill(inventory, groups)).thenReturn(false);
        fillers.add(fillerThree);
        var defaultFiller = mock(Filler.class);

        var _sut = new InventoryFillerProvider(fillers, defaultFiller);

        var filler = _sut.getInventoryFiller(inventory, groups);

        assertEquals(defaultFiller, filler);
        
        var inOrder = inOrder(fillerOne, fillerTwo, fillerThree);
        inOrder.verify(fillerOne).canFill(inventory, groups);
        inOrder.verify(fillerTwo).canFill(inventory, groups);
        inOrder.verify(fillerThree).canFill(inventory, groups);
    }

    @Test
    public void getInventoryFiller_WhenAllOfTheSetCanFill_ReturnsFirst() {
        var inventory = mock(Inventory.class);
        var groups = new ArrayList<List<ItemStack>>();

        var fillers = new ArrayList<Filler>();
        var fillerOne = mock(Filler.class);
        lenient().when(fillerOne.canFill(inventory, groups)).thenReturn(true);
        fillers.add(fillerOne);
        var fillerTwo = mock(Filler.class);
        lenient().when(fillerTwo.canFill(inventory, groups)).thenReturn(true);
        fillers.add(fillerTwo);
        var fillerThree = mock(Filler.class);
        lenient().when(fillerThree.canFill(inventory, groups)).thenReturn(true);
        fillers.add(fillerThree);
        var defaultFiller = mock(Filler.class);

        var _sut = new InventoryFillerProvider(fillers, defaultFiller);

        var filler = _sut.getInventoryFiller(inventory, groups);

        assertEquals(fillerOne, filler);
    }
}
