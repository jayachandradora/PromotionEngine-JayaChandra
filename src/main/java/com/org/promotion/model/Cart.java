package com.org.promotion.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> contents;

    public Cart() {
    }

    public Cart(Map<Product, Integer> contents) {
        this.contents = contents;
    }

    public Map<Product, Integer> getContents() {
        return contents;
    }

    public void setContents(Map<Product, Integer> contents) {
        this.contents = contents;
    }

    public Integer getQuantity(String itemName){
        for (Map.Entry<Product, Integer> kv: this.contents.entrySet())
            if (kv.getKey().getName().equalsIgnoreCase(itemName))
                return kv.getValue();

        return 0;
    }

    public Map<Product, Integer> removeItem(String itemToRemove) {
        Product productToRemove = null;
        Map<Product, Integer> temp = new HashMap<>();
        temp.putAll(this.contents);

        for (Map.Entry<Product, Integer> kv: temp.entrySet())
            if (kv.getKey().getName().equalsIgnoreCase(itemToRemove)) 
                productToRemove = kv.getKey();

        if (itemToRemove != null)
            temp.remove(productToRemove);

        return temp;
    }

    public Map<Product, Integer> adjustInventory(String itemName, Integer quantity) {
        Product productOut = null;
        Map<Product, Integer> temp = new HashMap<>();
        temp.putAll(this.contents);

        for (Map.Entry<Product, Integer> kv: temp.entrySet()) 
            if (kv.getKey().getName().equalsIgnoreCase(itemName)) 
                productOut = kv.getKey();

        if (null != productOut) 
            temp.put(productOut, quantity);
        
        return temp;
    }

    /**
     * Returns product entry from the cart by product name, if it does exist
     * @param item
     * @return
     */
    public Product getEntryByItemName(String item) {
        for (Map.Entry<Product, Integer> kv: contents.entrySet()) 
            if (kv.getKey().getName().equalsIgnoreCase(item))
                return kv.getKey();
        
        return null;
    }
}
