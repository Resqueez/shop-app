package com.github.dawidsznajder.shop_app.product;

import java.util.Optional;

public class ProductPatchRequest {
    private Optional<String> name = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<Double> price = Optional.empty();

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(Optional<String> description) {
        this.description = description;
    }

    public Optional<Double> getPrice() {
        return price;
    }

    public void setPrice(Optional<Double> price) {
        this.price = price;
    }
}
