package one.spectra;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.junit.Test;
import org.mockito.Mockito;


public class AppTest {

    @Test
    public void Test() {
        var itemStack = Mockito.mock(ItemStack.class);
        var inventory = Mockito.mock(Inventory.class);

        when(inventory.getSize()).thenReturn(5);

        assertEquals(5, inventory.getSize());        
    }
}
