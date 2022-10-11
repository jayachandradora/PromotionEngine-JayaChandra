package com.org.promotion;

import com.org.promotion.model.Cart;

/**
 * Promotion contract
 */
public interface Promotion {
    Cart applyPromotion(Cart cart);
    Boolean isAvailable(Cart cart);
    Double getDiscountedPrice();
}
