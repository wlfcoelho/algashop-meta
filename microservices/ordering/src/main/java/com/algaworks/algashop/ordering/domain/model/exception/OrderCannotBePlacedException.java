package com.algaworks.algashop.ordering.domain.model.exception;

import com.algaworks.algashop.ordering.domain.model.valueobject.id.OrderId;

import static com.algaworks.algashop.ordering.domain.model.exception.ErrorMessages.ERRROR_ORDER_STATUS_CANNOT_BE_PLACED_HAS_NO_ITEMS;

public class OrderCannotBePlacedException extends DomainException {


  private OrderCannotBePlacedException(String message) {
    super(message);
  }

  public static OrderCannotBePlacedException noItems(OrderId id) {
    return new OrderCannotBePlacedException(String.format(ERRROR_ORDER_STATUS_CANNOT_BE_PLACED_HAS_NO_ITEMS, id));
  }

  public static OrderCannotBePlacedException noShippingInfo(OrderId id) {
    return new OrderCannotBePlacedException(String.format(ErrorMessages.ERRROR_ORDER_STATUS_CANNOT_BE_PLACED_HAS_NO_SHIPPING_INFO, id));
  }

  public static OrderCannotBePlacedException noBillingInfo(OrderId id) {
    return new OrderCannotBePlacedException(String.format(ErrorMessages.ERRROR_ORDER_STATUS_CANNOT_BE_PLACED_HAS_NO_BILLING_INFO, id));
  }

  public static OrderCannotBePlacedException noPaymentMethod(OrderId id) {
    return new OrderCannotBePlacedException(String.format(ErrorMessages.ERRROR_ORDER_STATUS_CANNOT_BE_PLACED_HAS_PAYMENT_METHOD, id));
  }

}
