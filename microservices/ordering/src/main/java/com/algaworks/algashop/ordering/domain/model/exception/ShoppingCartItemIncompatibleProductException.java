package com.algaworks.algashop.ordering.domain.model.exception;

import com.algaworks.algashop.ordering.domain.model.valueobject.id.ShoppingCartItemId;
import com.algaworks.algashop.ordering.domain.model.valueobject.id.ProductId;

public class ShoppingCartItemIncompatibleProductException extends DomainException{
  public ShoppingCartItemIncompatibleProductException(ShoppingCartItemId id, ProductId productId) {
    super(String.format(ErrorMessages.ERROR_SHOPPING_CART_ITEM_IMCOMPATIBLE_PRODUCT, id, productId));
  }
}
