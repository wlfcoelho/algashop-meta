package com.algaworks.algashop.ordering.domain.exception;

import com.algaworks.algashop.ordering.domain.valueobject.id.OrderId;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.ERRROR_ORDER_STATUS_CANNOT_BE_PLACED_HAS_NO_ITEMS;

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

  public static OrderCannotBePlacedException invalidShippingCost(OrderId id) {
    return new OrderCannotBePlacedException(String.format(ErrorMessages.ERRROR_ORDER_STATUS_CANNOT_BE_PLACED_INVALID_SHIPPING_COST, id));
  }

  public static OrderCannotBePlacedException invalidDeliveryDate(OrderId id) {
    return new OrderCannotBePlacedException(String.format(ErrorMessages.ERRROR_ORDER_STATUS_CANNOT_BE_PLACED_INVALID_DELIVERY_DATE, id));
  }

  public static OrderCannotBePlacedException noPaymentMethod(OrderId id) {
    return new OrderCannotBePlacedException(String.format(ErrorMessages.ERRROR_ORDER_STATUS_CANNOT_BE_PLACED_HAS_PAYMENT_METHOD, id));
  }

}
