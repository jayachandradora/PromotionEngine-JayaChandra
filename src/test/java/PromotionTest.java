import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.org.promotion.BundlePromotion;
import com.org.promotion.Promotion;
import com.org.promotion.OneProductGroupPromotion;
import com.org.promotion.model.Cart;
import com.org.promotion.model.Product;
import com.org.promotion.service.PromotionService;
import com.org.promotion.service.PromotionServiceImpl;
import com.org.promotion.util.PromotionUtil;

public class PromotionTest {
	private static PromotionService promotionService;
	private static List<Promotion> promotions;
	private static Cart cart;

	@BeforeAll
	public static void setup() {
		promotionService = new PromotionServiceImpl();
		promotions = PromotionUtil.setupPromotions();
		cart = new Cart();
	}

	@AfterAll
	public static void teardown() {
		cart.getContents().clear();
	}

	@Test
	public void testIsAvailableMethodOnPromotionsTrue() {
		Promotion promotion = new OneProductGroupPromotion("A", 2, 90.0);
		Map<Product, Integer> contents = new HashMap<>();
		contents.put(new Product("A"), 3);
		cart.setContents(contents);
		assertTrue(promotion.isAvailable(cart), "This cart should contain items for this promotion.");
	}

	@Test
	public void testIsAvailableMethodOnPromotionsFalse() {
		Promotion promotion = new OneProductGroupPromotion("A", 2, 90.0);
		Map<Product, Integer> contents = new HashMap<>();
		contents.put(new Product("B"), 3);
		cart.setContents(contents);
		assertFalse(promotion.isAvailable(cart), "This cart should not contain items for this promotion.");
	}

	@Test
	public void testIsAvailableMethodOnBundledPromotionsTrue() {
		Promotion promotion = new BundlePromotion(Arrays.asList("A", "B"), 30.0);
		Map<Product, Integer> contents = new HashMap<>();
		contents.put(new Product("A"), 3);
		contents.put(new Product("B"), 1);
		cart.setContents(contents);
		assertTrue(promotion.isAvailable(cart), "This cart should contain items for this promotion.");
	}

	@Test
	public void testIsAvailableMethodOnBundledPromotionsFalse() {
		Promotion promotion = new BundlePromotion(Arrays.asList("A", "B"), 30.0);
		Map<Product, Integer> contents = new HashMap<>();
		contents.put(new Product("A"), 3);
		contents.put(new Product("C"), 1);
		cart.setContents(contents);
		assertFalse(promotion.isAvailable(cart), "This cart should not contain items for this promotion.");
	}
}
