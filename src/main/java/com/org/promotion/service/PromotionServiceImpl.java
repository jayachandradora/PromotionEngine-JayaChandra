package com.org.promotion.service;

import com.org.promotion.Promotion;
import com.org.promotion.model.Cart;
import com.org.promotion.model.Product;
import com.org.promotion.util.PriceList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PromotionServiceImpl implements PromotionService {
    @Override
    public Double getRawPrice(Cart cart) {
        Double istPrice = 0.0;

        for (Map.Entry<Product, Integer> entry: cart.getContents().entrySet()) 
            istPrice = istPrice + entry.getValue() * PriceList.getPrice(entry.getKey().getName());
        
        return istPrice;
    }

    @Override
    public Double getPromotedPrice(Cart cart, List<Promotion> promotions) {
        Double rawPrice = getRawPrice(cart);
        return applyPromotions(cart, promotions, rawPrice);
    }

    private Double applyPromotions(Cart cart, List<Promotion> promotions, Double checkoutPrice) {
        Promotion selectedPromotion = null;
        double discountedPrice = 0.0;

        
        List<Promotion> availablePromotions = new ArrayList<>();
        for (Promotion promotion: promotions) //Get all available promotions.
            if (promotion.isAvailable(cart)) 
                availablePromotions.add(promotion);
            
        if (availablePromotions.isEmpty()) // No available promotion on the cart
            return checkoutPrice;

        // Find the promotion to apply on cart and the discounted price with it over the cart.
        for (Promotion promotion: availablePromotions) {
            double promotionDiscountedPrice = promotion.getDiscountedPrice();
            if (promotionDiscountedPrice > discountedPrice) {
                discountedPrice = promotionDiscountedPrice;
                selectedPromotion = promotion;
            }
        }

        Cart promotedCart = new Cart();
        if (selectedPromotion != null) 
            promotedCart = selectedPromotion.applyPromotion(cart);
        
        checkoutPrice = checkoutPrice - discountedPrice;
        return applyPromotions(promotedCart, availablePromotions, checkoutPrice);
    }
}
