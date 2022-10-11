package com.org.promotion.configs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "products", "quota", "promoted_price"})
public class PromotionProperties {
    @JsonProperty("name")
    private String name;
    @JsonProperty("products")
    private List<String> products;
    @JsonProperty("quota")
    private Integer quota;
    @JsonProperty("promoted_price")
    private Double promoted_price;

    public String getName() {
        return name;
    }

    public List<String> getProducts() {
        return products;
    }

    public Integer getQuota() {
        return quota;
    }

    public Double getPromoted_price() {
        return promoted_price;
    }
}
