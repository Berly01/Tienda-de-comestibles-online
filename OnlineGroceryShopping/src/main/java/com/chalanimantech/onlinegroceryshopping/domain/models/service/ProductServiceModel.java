package com.chalanimantech.onlinegroceryshopping.domain.models.service;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.PRODUCT_NAME_MIN_LENGTH;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.PRODUCT_DESCRIPTION_MAX_LENGTH;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.PRODUCT_NAME_MAX_LENGTH;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ValidationErrorMessages.PRODUCT_NAME_EMPTY_FIELD_ERROR_MSG;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ValidationErrorMessages.PRODUCT_NAME_LENGTH;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ValidationErrorMessages.PRODUCT_DESCRIPTION_EMPTY_FIELD_ERROR_MSG;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ValidationErrorMessages.PRODUCT_DESCRIPTION_MAX_LENGTH_ERROR_MSG;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ValidationErrorMessages.PRODUCT_PRICE_EMPTY_FIELD_ERROR_MSG;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ValidationErrorMessages.PRODUCT_IMAGE_EMPTY_FIELD_ERROR_MSG;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ValidationErrorMessages.PRODUCT_CATEGORIES_EMPTY_FIELD_ERROR_MSG;

public class ProductServiceModel extends BaseServiceModel {

    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private List<CategoryServiceModel> categories;
    private BigDecimal discountedPrice;
    private boolean isDeleted;

    public ProductServiceModel() {
    	super();
    }

    @NotNull(message = PRODUCT_NAME_EMPTY_FIELD_ERROR_MSG)
    @Size(min = PRODUCT_NAME_MIN_LENGTH, max = PRODUCT_NAME_MAX_LENGTH, message = PRODUCT_NAME_LENGTH)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = PRODUCT_DESCRIPTION_EMPTY_FIELD_ERROR_MSG)
    @NotEmpty(message = PRODUCT_DESCRIPTION_EMPTY_FIELD_ERROR_MSG)
    @Length(max = PRODUCT_DESCRIPTION_MAX_LENGTH, message = PRODUCT_DESCRIPTION_MAX_LENGTH_ERROR_MSG)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = PRODUCT_PRICE_EMPTY_FIELD_ERROR_MSG)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull(message = PRODUCT_IMAGE_EMPTY_FIELD_ERROR_MSG)
    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotNull
    @NotEmpty(message = PRODUCT_CATEGORIES_EMPTY_FIELD_ERROR_MSG)
    public List<CategoryServiceModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryServiceModel> categories) {
        this.categories = categories;
    }

    public BigDecimal getDiscountedPrice() {
        return this.discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
