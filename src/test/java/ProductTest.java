import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.org.promotion.model.Product;

public class ProductTest {
    @Test
    public void testProductCreation() {
        Product prod = new Product("A", 50);
        Assertions.assertEquals(50, prod.getPrice());
        Assertions.assertEquals("A", prod.getName());
        Assertions.assertNotNull(prod, "Unable to create a product instance.");
    }
}
