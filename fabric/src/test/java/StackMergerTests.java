import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

@ExtendWith(MockitoExtension.class)
public class StackMergerTests {
    @Test
    void demoTestMethod() {
        var itemStack = Mockito.mock(Inventory.class);
    }
}
