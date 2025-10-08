package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.valueobject.*;
import com.algaworks.algashop.ordering.domain.valueobject.id.CustomerId;

import java.time.LocalDate;

public class OrderTestDataBuilder {

  private CustomerId customerId = new CustomerId();

  private PaymentMethod paymentMethod = PaymentMethod.GATEWAY_BALANCE;


  private Shipping shipping = aShipping();
  private BillingInfo billingInfo = aBillingInfo();

  private boolean withItems = true;

  private OrderStatus status = OrderStatus.DRAFT;

  private OrderTestDataBuilder() {

  }

  public static OrderTestDataBuilder anOrder() {
    return new OrderTestDataBuilder();
  }

  public Order build() {
    Order order = Order.draft(customerId);
    order.changeShipping(shipping);
    order.changeBilling(billingInfo);
    order.changePaymentMethod(paymentMethod);

    if (withItems) {
      order.addItem(ProductTestdataBuilder.aProduct().build(), new Quantity(2));

      order.addItem(ProductTestdataBuilder.aProductAltRamMemory().build(), new Quantity(1));
    }

    switch (this.status) {
      case DRAFT -> {
      }
      case PLACED -> {
        order.place();
      }
      case PAID -> {
        order.place();
        order.markAsPaid();
      }
      case READY -> {
      }
      case CANCELED -> {
      }
    }

    return order;
  }

  public static BillingInfo aBillingInfo() {
    return BillingInfo.builder()
            .address(anAddress())
            .document(new Document("225-09-1992"))
            .phone(new Phone("123-111-9911"))
            .fullName(new FullName("John", "Doe")).build();
  }

  public static Shipping aShipping() {
    return Shipping.builder()
            .address(anAddress())
            .cost(new Money("10.00"))
            .expectedDate(LocalDate.now().plusWeeks(1))
            .recipient(Recipient.builder()
                    .fullName(new FullName("John", "Doe"))
                    .document(new Document("225-09-1992"))
                    .phone(new Phone("123-111-9911")).build())
            .build();
  }

  public static Address anAddress() {
    return Address.builder()
            .street("Bourbon Street")
            .number("1234")
            .neighborhood("North Ville")
            .complement("apt. 11")
            .city("Montfort")
            .state("South Carolina")
            .zipCode(new ZipCode("79911")).build();
  }

  public static Shipping aShippingAlt() {
    return Shipping.builder()
            .address(anAddressAlt())
            .cost(new Money("20.00"))
            .expectedDate(LocalDate.now().plusWeeks(2))
            .recipient(Recipient.builder()
                    .fullName(new FullName("Jane", "Smith"))
                    .document(new Document("987-65-4320"))
                    .phone(new Phone("321-222-9922")).build())
            .build();
  }

  public static Address anAddressAlt() {
    return Address.builder()
            .street("5th Avenue")
            .number("5000")
            .neighborhood("Central Park")
            .complement("apt. 101")
            .city("New York")
            .state("NY")
            .zipCode(new ZipCode("10001")).build();
  }

  public OrderTestDataBuilder customerId(CustomerId customerId) {
    this.customerId = customerId;
    return this;
  }

  public OrderTestDataBuilder paymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
    return this;
  }

  public OrderTestDataBuilder shippingInfo(Shipping shipping) {
    this.shipping = shipping;
    return this;
  }

  public OrderTestDataBuilder billingInfo(BillingInfo billingInfo) {
    this.billingInfo = billingInfo;
    return this;
  }

  public OrderTestDataBuilder withItems(boolean withItems) {
    this.withItems = withItems;
    return this;
  }

  public OrderTestDataBuilder status(OrderStatus status) {
    this.status = status;
    return this;
  }
}
