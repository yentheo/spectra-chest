package one.spectra.better_chests.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import one.spectra.better_chests.FakeItemStack;
import one.spectra.better_chests.abstractions.ItemStack;

@ExtendWith(MockitoExtension.class)
public class SpreaderTests {

    private Spreader _sut;

    @BeforeEach
    public void setUp() {
        _sut = new Spreader();
    }

    @Test
    public void shouldSpread_whenEnoughItemsInStack() {
        var woodStacks = new ArrayList<ItemStack>();
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(64, "Wood"));
        var ironStacks = new ArrayList<ItemStack>();
        ironStacks.add(new FakeItemStack(30, "Iron"));

        var groups = new ArrayList<List<ItemStack>>();
        groups.add(woodStacks);
        groups.add(ironStacks);

        var spreadGroups = _sut.spread(groups, 6);

        var woodGroup = spreadGroups.get(0);
        assertEquals(6, woodGroup.size());
        assertEquals(62, woodGroup.get(0).getAmount());
        assertEquals(1, woodGroup.get(1).getAmount());
        assertEquals(1, woodGroup.get(2).getAmount());
        assertEquals(1, woodGroup.get(3).getAmount());
        assertEquals(1, woodGroup.get(4).getAmount());
        assertEquals(1, woodGroup.get(5).getAmount());

        var ironGroup = spreadGroups.get(1);
        assertEquals(6, ironGroup.size());
        assertEquals(25, ironGroup.get(0).getAmount());
        assertEquals(1, ironGroup.get(1).getAmount());
        assertEquals(1, ironGroup.get(2).getAmount());
        assertEquals(1, ironGroup.get(3).getAmount());
        assertEquals(1, ironGroup.get(4).getAmount());
        assertEquals(1, ironGroup.get(5).getAmount());
    }

    @Test
    public void shouldSpread_untilNoItemsLeft() {
        var woodStacks = new ArrayList<ItemStack>();
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));

        var groups = new ArrayList<List<ItemStack>>();
        groups.add(woodStacks);

        var spreadGroups = _sut.spread(groups, 7);

        var woodGroup = spreadGroups.get(0);
        assertEquals(6, woodGroup.size());
        assertEquals(1, woodGroup.get(0).getAmount());
        assertEquals(1, woodGroup.get(1).getAmount());
        assertEquals(1, woodGroup.get(2).getAmount());
        assertEquals(1, woodGroup.get(3).getAmount());
        assertEquals(1, woodGroup.get(4).getAmount());
        assertEquals(1, woodGroup.get(5).getAmount());
    }

    @Test
    public void shouldNotSpread_whenLineAlreadyFull() {
        var woodStacks = new ArrayList<ItemStack>();
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));
        woodStacks.add(new FakeItemStack(3, "Wood"));

        var groups = new ArrayList<List<ItemStack>>();
        groups.add(woodStacks);

        var spreadGroups = _sut.spread(groups, 3);

        var woodGroup = spreadGroups.get(0);
        assertEquals(3, woodGroup.size());
        assertEquals(3, woodGroup.get(0).getAmount());
        assertEquals(3, woodGroup.get(1).getAmount());
        assertEquals(3, woodGroup.get(2).getAmount());
    }

}
