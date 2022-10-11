import com.org.promotion.Promotion;
import com.org.promotion.model.Cart;
import com.org.promotion.model.Product;
import com.org.promotion.service.PromotionService;
import com.org.promotion.service.PromotionServiceImpl;
import com.org.promotion.util.PromotionUtil;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionServiceImplTest {
    private static PromotionService promotionService;
    private static List<Promotion> promotions;
    private static Cart cart;
    private static Product productA;
    private static Product productB;
    private static Product productC;
    private static Product productD;

    @BeforeAll
    public static void setup() {
        promotionService = new PromotionServiceImpl();
        promotions = PromotionUtil.setupPromotions();
        cart = new Cart();
        productA = new Product("A");
        productB = new Product("B");
        productC = new Product("C");
        productD = new Product("D");
    }

    @AfterAll
    public static void teardown() {
        cart.getContents().clear();
    }

    @Test
    public void testBundlePromotionAppliedOnCart() {
        Map<Product, Integer> testProductAndQty = new HashMap<>();
        testProductAndQty.put(productA, 3);
        testProductAndQty.put(productB, 5);
        testProductAndQty.put(productC, 1);
        testProductAndQty.put(productD, 1);
        cart.setContents(testProductAndQty);
        Double checkoutPrice = promotionService.getPromotedPrice(cart, promotions);
        assertEquals(280.0, checkoutPrice);
    }

    @Test
    public void testCartWithNoAvailablePromotion() {
        Map<Product, Integer> testProductAndQty = new HashMap<>();
        testProductAndQty.put(productA, 1);
        testProductAndQty.put(productB, 1);
        testProductAndQty.put(productC, 1);
        cart.setContents(testProductAndQty);
        Double checkoutPrice = promotionService.getPromotedPrice(cart, promotions);
        assertEquals(100.0, checkoutPrice);
    }

    @Test
    public void testSingleProductGroupingPromotionAppliedOnCart() {
        Map<Product, Integer> testContents = new HashMap<>();
        testContents.put(productA, 5);
        testContents.put(productB, 5);
        testContents.put(productC, 1);
        cart.setContents(testContents);
        Double checkoutPrice = promotionService.getPromotedPrice(cart, promotions);
        assertEquals(370.0, checkoutPrice);
    }

    @Test
    public void testBundlePromotionAppliedOnCartTwoTimes() {
        Map<Product, Integer> testContents = new HashMap<>();
        testContents.put(productC, 2);
        testContents.put(productD, 2);
        cart.setContents(testContents);
        Double checkoutPrice = promotionService.getPromotedPrice(cart, promotions);
        assertEquals(60.0, checkoutPrice);
    }

    @Test
    public void testSingleProductGroupingPromotionAppliedOnCartTwoTimes() {
        Map<Product, Integer> testContents = new HashMap<>();
        testContents.put(productA, 6);
        cart.setContents(testContents);
        Double checkoutPrice = promotionService.getPromotedPrice(cart, promotions);
        assertEquals(260.0, checkoutPrice);
    }

    @Test
    public void testSingleProductGroupingPromotionAppliedOnCartTwoTimesAndOneWithoutPromotion() {
        Map<Product, Integer> testContents = new HashMap<>();
        testContents.put(productA, 7);
        cart.setContents(testContents);
        Double checkoutPrice = promotionService.getPromotedPrice(cart, promotions);
        assertEquals(310.0, checkoutPrice);
    }
}
