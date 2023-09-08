package one.spectra.better_chests.inventory.fillers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import one.spectra.better_chests.FakeItemStack;
import one.spectra.better_chests.abstractions.ItemStack;
import one.spectra.better_chests.inventory.Inventory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class DefaultFillerTests {

    private DefaultFiller _sut;

    @BeforeEach
    public void setUp() {
        _sut = new DefaultFiller(mock(Logger.class));
    }

    @Test
    public void fill_putsStacksGroupedInColumns() {
        var woodStacks = new ArrayList<ItemStack>();
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        var ironStacks = new ArrayList<ItemStack>();
        ironStacks.add(new FakeItemStack(30, "Iron"));

        var groups = new ArrayList<List<ItemStack>>();
        groups.add(woodStacks);
        groups.add(ironStacks);
        
        var myInventory = mock(Inventory.class);

        _sut.fill(myInventory, groups, false);

        verify(myInventory).putInSlot(eq(0), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(1), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(2), argThat(x -> x.getAmount() == 30 && x.getMaterialKey() == "Iron"));
    }

    @Test
    public void canFill_whenChestSize4_returnsTrueIf4Stacks() {
        var woodStacks = new ArrayList<ItemStack>();
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        var goldStacks = new ArrayList<ItemStack>();
        goldStacks.add(new FakeItemStack(30, "Gold"));
        var ironStacks = new ArrayList<ItemStack>();
        ironStacks.add(new FakeItemStack(64, "Iron"));

        var groups = new ArrayList<List<ItemStack>>();
        groups.add(woodStacks);
        groups.add(goldStacks);
        groups.add(ironStacks);
        
        var myInventory = mock(Inventory.class);
        when(myInventory.getSize()).thenReturn(27);

        var result = _sut.canFill(myInventory, groups);

        assertTrue(result);
    }

    @Test
    public void canFill_whenChestSize5_returnsFalseIf6Stacks() {
        var woodStacks = new ArrayList<ItemStack>();
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        
        var groups = new ArrayList<List<ItemStack>>();
        groups.add(woodStacks);

        var myInventory = mock(Inventory.class);
        when(myInventory.getSize()).thenReturn(5);
        
        var result = _sut.canFill(myInventory, groups);

        assertFalse(result);
    }
}
