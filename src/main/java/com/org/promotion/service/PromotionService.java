package com.org.promotion.service;

import com.org.promotion.Promotion;
import com.org.promotion.model.Cart;

import java.util.List;

public interface PromotionService {
	public Double getRawPrice(Cart cart);
	public Double getPromotedPrice(Cart cart, List<Promotion> promotions);
}
