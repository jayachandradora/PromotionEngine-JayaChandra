package com.org.promotion.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.org.promotion.BundlePromotion;
import com.org.promotion.Promotion;
import com.org.promotion.OneProductGroupPromotion;

public class PromotionUtil {
    public static List<Promotion> setupPromotions() {
        List<Promotion> promotions = new ArrayList<>();
        BundlePromotion bundlePromotion = new BundlePromotion(Arrays.asList("C", "D"), 30.0);
        OneProductGroupPromotion oneProductGroupPromotionA = new OneProductGroupPromotion("A", 3, 130.0);
        OneProductGroupPromotion oneProductGroupPromotionB = new OneProductGroupPromotion("B",2, 45.0);
        promotions.add(bundlePromotion);
        promotions.add(oneProductGroupPromotionA);
        promotions.add(oneProductGroupPromotionB);
        return promotions;
    }
}

/**
 
 Method: POST 
 URI: www.pricingengine.com/api/apply/promotion/bundleproduct
 Request Payload
 {
 	items : ["C", "D"],
 	promotedPrice: 30.0
 }
 
 Response Payload:
 {
 	items : ["C", "D"],
 	promotedPrice: 30.0,
 	message: "Promotion applied successfully for bundle product"
 }
 
 Method: POST 
 URI: www.pricingengine.com/api/apply/promotion/singleproductgroup
 Request Payload
 {
 	"appliedItem" : "A",
 	"quota" : 3,
 	"promotedPrice": 30.0
 }
 
 Response Payload:
 {
 	"appliedItem" : "A",
 	"quota" : 3,
 	"promotedPrice": 30.0,
 	message: "Promotion applied successfully for single product group"
 }
 
 Method: POST 
 URI: www.pricingengine.com/api/apply/promotion/singleproductgroup
 Request: Payload
 {
 	"appliedItem" : "B",
 	"quota" : 2,
 	"promotedPrice": 45.0
 }
 
 Response Payload:
 {
 	"appliedItem" : "B",
 	"quota" : 2,
 	"promotedPrice": 45.0
 	message: "Promotion applied successfully for single product group"
 }
 
 **/
