package one.spectra.better_chests.inventory.fillers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.inOrder;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
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
public class ColumnFillerTests {

    private ColumnFiller _sut;

    @BeforeEach
    public void setUp() {
        _sut = new ColumnFiller(mock(Logger.class));
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
        when(myInventory.getRows()).thenReturn(3);

        _sut.fill(myInventory, groups);

        verify(myInventory).putInSlot(eq(0), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(9), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(1), argThat(x -> x.getAmount() == 30 && x.getMaterialKey() == "Iron"));
    }

    @Test
    public void fill_putsStacksInOrderOfLargeToSmall() {
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

        _sut.fill(myInventory, groups);

        verify(myInventory).putInSlot(eq(0), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(9), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Wood"));
        verify(myInventory).putInSlot(eq(1), argThat(x -> x.getAmount() == 30 && x.getMaterialKey() == "Gold"));
        verify(myInventory).putInSlot(eq(2), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Iron"));
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
    public void canFill_whenSmallChestSize_returnsTrueIfEnoughColumnsForOverflowMaterialTypeHas4Stacks() {
        var woodStacks = new ArrayList<ItemStack>();
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        var ironStacks = new ArrayList<ItemStack>();
        ironStacks.add(new FakeItemStack(30, "Iron"));
        var goldStacks = new ArrayList<ItemStack>();
        goldStacks.add(new FakeItemStack(64, "Gold"));
        
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
    public void canFill_whenSmallChestSize_returnsFalseIf10MaterialTypes() {
        var woodStacks = new ArrayList<ItemStack>();
        woodStacks.add(new FakeItemStack(3, "Wood"));
        var stoneStacks = new ArrayList<ItemStack>();
        stoneStacks.add(new FakeItemStack(64, "Stone"));
        var bronzeStacks = new ArrayList<ItemStack>();
        bronzeStacks.add(new FakeItemStack(64, "Bronze"));
        var stairsStacks = new ArrayList<ItemStack>();
        stairsStacks.add(new FakeItemStack(64, "Stairs"));
        var ironStacks = new ArrayList<ItemStack>();
        ironStacks.add(new FakeItemStack(30, "Iron"));
        var goldStacks = new ArrayList<ItemStack>();
        goldStacks.add(new FakeItemStack(64, "Gold"));
        var dirtStacks = new ArrayList<ItemStack>();
        dirtStacks.add(new FakeItemStack(64, "Dirt"));
        var grassStacks = new ArrayList<ItemStack>();
        grassStacks.add(new FakeItemStack(64, "Grass"));
        var obsidianStacks = new ArrayList<ItemStack>();
        obsidianStacks.add(new FakeItemStack(64, "Obsidian"));
        var emeraldStacks = new ArrayList<ItemStack>();
        emeraldStacks.add(new FakeItemStack(64, "Emerald"));
        
        var groups = new ArrayList<List<ItemStack>>();
        groups.add(woodStacks);
        groups.add(goldStacks);
        groups.add(ironStacks);
        groups.add(stoneStacks);
        groups.add(bronzeStacks);
        groups.add(stairsStacks);
        groups.add(dirtStacks);
        groups.add(grassStacks);
        groups.add(obsidianStacks);
        groups.add(emeraldStacks);

        var myInventory = mock(Inventory.class);
        when(myInventory.getColumns()).thenReturn(9);
        when(myInventory.getRows()).thenReturn(3);

        var result = _sut.canFill(myInventory, groups);

        assertFalse(result);
    }

    @Test
    public void canFill_HasTenStacksOfOneItem_AndTwoStacksOfTwoOtherItems_InventoryOfNineColumns_ShouldReturnTrue() {
        var woodStacks = new ArrayList<ItemStack>();
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        var stoneStacks = new ArrayList<ItemStack>();
        stoneStacks.add(new FakeItemStack(64, "Stone"));
        var bronzeStacks = new ArrayList<ItemStack>();
        bronzeStacks.add(new FakeItemStack(64, "Bronze"));
        
        var groups = new ArrayList<List<ItemStack>>();
        groups.add(woodStacks);
        groups.add(stoneStacks);
        groups.add(bronzeStacks);

        var myInventory = mock(Inventory.class);
        when(myInventory.getColumns()).thenReturn(9);
        when(myInventory.getRows()).thenReturn(3);

        var result = _sut.canFill(myInventory, groups);

        assertTrue(result);
    }

    @Test
    public void fill_HasTenStacksOfOneItem_AndTwoStacksOfTwoOtherItems_InventoryOfNineColumnsAndThreeRows_ShouldFillFourColumnsOfFirstItem() {
        var woodStacks = new ArrayList<ItemStack>();
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        var stoneStacks = new ArrayList<ItemStack>();
        stoneStacks.add(new FakeItemStack(64, "Stone"));
        var bronzeStacks = new ArrayList<ItemStack>();
        bronzeStacks.add(new FakeItemStack(64, "Bronze"));
        
        var groups = new ArrayList<List<ItemStack>>();
        groups.add(woodStacks);
        groups.add(stoneStacks);
        groups.add(bronzeStacks);

        var myInventory = mock(Inventory.class);
        when(myInventory.getColumns()).thenReturn(9);
        when(myInventory.getRows()).thenReturn(3);

        _sut.fill(myInventory, groups);

        InOrder inOrder = inOrder(myInventory);

        inOrder.verify(myInventory).putInSlot(eq(0), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        inOrder.verify(myInventory).putInSlot(eq(9), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        inOrder.verify(myInventory).putInSlot(eq(18), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        inOrder.verify(myInventory).putInSlot(eq(1), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        inOrder.verify(myInventory).putInSlot(eq(10), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        inOrder.verify(myInventory).putInSlot(eq(19), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        inOrder.verify(myInventory).putInSlot(eq(2), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        inOrder.verify(myInventory).putInSlot(eq(11), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        inOrder.verify(myInventory).putInSlot(eq(20), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        inOrder.verify(myInventory).putInSlot(eq(3), argThat(x -> x.getAmount() == 3 && x.getMaterialKey() == "Wood"));
        inOrder.verify(myInventory).putInSlot(eq(4), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Stone"));
        inOrder.verify(myInventory).putInSlot(eq(5), argThat(x -> x.getAmount() == 64 && x.getMaterialKey() == "Bronze"));
    }
}
