package com.algaworks.algashop.ordering.domain.model.exception;

import com.algaworks.algashop.ordering.domain.model.valueobject.id.ShoppingCartId;
import com.algaworks.algashop.ordering.domain.model.valueobject.id.ProductId;

public class ShoppingCartDoesNotContainProductException extends DomainException{
  public ShoppingCartDoesNotContainProductException(ShoppingCartId shoppingCartId, ProductId productId) {
    super(String.format(ErrorMessages.ERROR_SHOPPING_CART_DOES_NOT_CONTAIN_PRODUCT, shoppingCartId, productId));
  }
}
