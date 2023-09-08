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
import one.spectra.better_chests.inventory.Spreader;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class RowFillerTests {

    private RowFiller _sut;

    private Spreader _spreader;

    @BeforeEach
    public void setUp() {
        _spreader = mock(Spreader.class);
        _sut = new RowFiller(_spreader, mock(Logger.class));
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
        when(myInventory.getColumns()).thenReturn(9);

        _sut.fill(myInventory, groups, false);

        verify(myInventory).putInSlot(eq(0), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(1), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(9), argThat(x -> x.getAmount() == 30 && x.getMaterialKey() == "Iron"));
    }

    @Test
    public void fill_whenSmallChestSize_returnsTrueIfMaterialTypeHas10Stacks_AndOnlyOneOtherItemTypeIsPresent() {
        var woodStacks = new ArrayList<ItemStack>();
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        var goldStacks = new ArrayList<ItemStack>();
        goldStacks.add(new FakeItemStack(64, "Gold"));

        var groups = new ArrayList<List<ItemStack>>();
        groups.add(woodStacks);
        groups.add(goldStacks);

        var myInventory = mock(Inventory.class);
        when(myInventory.getColumns()).thenReturn(9);

        _sut.fill(myInventory, groups, false);

        verify(myInventory).putInSlot(eq(0), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(1), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(2), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(3), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(4), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(5), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(6), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(7), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(8), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(9), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(18), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Gold"));
    }

    @Test
    public void canFill_whenSmallChestSize_returnsTrueIf3MaterialTypesAnd4StacksInTotal() {
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
        when(myInventory.getColumns()).thenReturn(9);
        when(myInventory.getRows()).thenReturn(3);

        var result = _sut.canFill(myInventory, groups);

        assertTrue(result);
    }

    @Test
    public void canFill_whenSmallChestSize_returnsFalseIfMaterialTypeHas10Stacks() {
        var woodStacks = new ArrayList<ItemStack>();
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        var goldStacks = new ArrayList<ItemStack>();
        goldStacks.add(new FakeItemStack(64, "Gold"));
        var ironStacks = new ArrayList<ItemStack>();
        ironStacks.add(new FakeItemStack(30, "Iron"));

        var groups = new ArrayList<List<ItemStack>>();
        groups.add(woodStacks);
        groups.add(goldStacks);
        groups.add(ironStacks);

        var myInventory = mock(Inventory.class);
        when(myInventory.getColumns()).thenReturn(9);
        when(myInventory.getRows()).thenReturn(3);

        var result = _sut.canFill(myInventory, groups);

        assertFalse(result);
    }

    @Test
    public void canFill_whenSmallChestSize_returnsFalseIf4MaterialTypes() {
        var woodStacks = new ArrayList<ItemStack>();
        woodStacks.add(new FakeItemStack(3, "Wood"));
        var stoneStacks = new ArrayList<ItemStack>();
        stoneStacks.add(new FakeItemStack(64, "Stone"));
        var bronzeStacks = new ArrayList<ItemStack>();
        bronzeStacks.add(new FakeItemStack(64, "Bronze"));
        var stairsStacks = new ArrayList<ItemStack>();
        stairsStacks.add(new FakeItemStack(64, "Stairs"));

        var groups = new ArrayList<List<ItemStack>>();
        groups.add(woodStacks);
        groups.add(stoneStacks);
        groups.add(bronzeStacks);
        groups.add(stairsStacks);

        var myInventory = mock(Inventory.class);
        when(myInventory.getColumns()).thenReturn(9);
        when(myInventory.getRows()).thenReturn(3);

        var result = _sut.canFill(myInventory, groups);

        assertFalse(result);
    }
}
