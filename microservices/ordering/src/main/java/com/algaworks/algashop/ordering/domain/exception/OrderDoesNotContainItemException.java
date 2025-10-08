package com.algaworks.algashop.ordering.domain.exception;

import com.algaworks.algashop.ordering.domain.valueobject.id.OrderId;
import com.algaworks.algashop.ordering.domain.valueobject.id.OrderItemId;

public class OrderDoesNotContainItemException extends DomainException{
  public OrderDoesNotContainItemException(OrderId id, OrderItemId orderItemId) {

    super(String.format(ErrorMessages.ERROR_ORDER_DOES_NOT_CONTAIN_ITEM, id, orderItemId));
  }
}
