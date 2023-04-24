package one.spectra.better_chests.inventory.fillers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import one.spectra.better_chests.FakeItemStack;
import one.spectra.better_chests.abstractions.ItemStack;
import one.spectra.better_chests.inventory.Inventory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class RowFillerTests {

    private RowFiller _sut;

    @BeforeEach
    public void setUp() {
        _sut = new RowFiller();
    }

    @Test
    public void fill_putsStacksGroupedInColumns() {
        var stacks = new ArrayList<ItemStack>();
        stacks.add(new FakeItemStack(3, "Wood"));
        stacks.add(new FakeItemStack(64, "Wood"));
        stacks.add(new FakeItemStack(30, "Iron"));
        
        var myInventory = mock(Inventory.class);
        when(myInventory.getColumns()).thenReturn(9);

        _sut.fill(myInventory, stacks);

        verify(myInventory).putInSlot(eq(0), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(1), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(9), argThat(x -> x.getAmount() == 30 && x.getMaterialKey() == "Iron"));
    }

    @Test
    public void canFill_whenSmallChestSize_returnsTrueIf3MaterialTypesAnd4StacksInTotal() {
        var stacks = new ArrayList<ItemStack>();
        stacks.add(new FakeItemStack(3, "Wood"));
        stacks.add(new FakeItemStack(64, "Wood"));
        stacks.add(new FakeItemStack(30, "Iron"));
        stacks.add(new FakeItemStack(64, "Gold"));
        
        var myInventory = mock(Inventory.class);
        when(myInventory.getColumns()).thenReturn(9);
        when(myInventory.getRows()).thenReturn(3);

        var result = _sut.canFill(myInventory, stacks);

        assertTrue(result);
    }

    @Test
    public void canFill_whenSmallChestSize_returnsFalseIfMaterialTypeHas10Stacks() {
        var stacks = new ArrayList<ItemStack>();
        stacks.add(new FakeItemStack(3, "Wood"));
        stacks.add(new FakeItemStack(64, "Wood"));
        stacks.add(new FakeItemStack(64, "Wood"));
        stacks.add(new FakeItemStack(64, "Wood"));
        stacks.add(new FakeItemStack(64, "Wood"));
        stacks.add(new FakeItemStack(64, "Wood"));
        stacks.add(new FakeItemStack(64, "Wood"));
        stacks.add(new FakeItemStack(64, "Wood"));
        stacks.add(new FakeItemStack(64, "Wood"));
        stacks.add(new FakeItemStack(64, "Wood"));
        stacks.add(new FakeItemStack(30, "Iron"));
        stacks.add(new FakeItemStack(64, "Gold"));
        
        var myInventory = mock(Inventory.class);
        when(myInventory.getColumns()).thenReturn(9);
        when(myInventory.getRows()).thenReturn(3);

        var result = _sut.canFill(myInventory, stacks);

        assertFalse(result);
    }

    @Test
    public void canFill_whenSmallChestSize_returnsFalseIf4MaterialTypes() {
        var stacks = new ArrayList<ItemStack>();
        stacks.add(new FakeItemStack(3, "Wood"));
        stacks.add(new FakeItemStack(64, "Stone"));
        stacks.add(new FakeItemStack(64, "Bronze"));
        stacks.add(new FakeItemStack(64, "Stairs"));
        
        var myInventory = mock(Inventory.class);
        when(myInventory.getColumns()).thenReturn(9);
        when(myInventory.getRows()).thenReturn(3);

        var result = _sut.canFill(myInventory, stacks);

        assertFalse(result);
    }
}
