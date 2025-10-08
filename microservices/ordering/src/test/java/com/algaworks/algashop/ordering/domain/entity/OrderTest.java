package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.exception.OrderInvalidShippingDeliveryDateException;
import com.algaworks.algashop.ordering.domain.exception.OrderStatusCannotBeChangedException;
import com.algaworks.algashop.ordering.domain.exception.ProductOutOfStockException;
import com.algaworks.algashop.ordering.domain.valueobject.*;
import com.algaworks.algashop.ordering.domain.valueobject.id.CustomerId;
import com.algaworks.algashop.ordering.domain.valueobject.id.ProductId;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

class OrderTest {

  @Test
  public void shouldGenerate() {
    Order order = Order.draft(new CustomerId());
  }

  @Test
  public void shouldAddItem() {
    Order order = Order.draft(new CustomerId());
    Product product = ProductTestdataBuilder.aProduct().build();
    ProductId productId = product.id();

    order.addItem(
            product,
            new Quantity(1)
    );

    Assertions.assertThat(order.items().size()).isEqualTo(1);

    OrderItem orderItem = order.items().iterator().next();

    Assertions.assertWith(orderItem,
            (i) -> Assertions.assertThat(i.id()).isNotNull(),
            (i) -> Assertions.assertThat(i.productName()).isEqualTo(new ProductName("NoteBook X11")),
            (i) -> Assertions.assertThat(i.productId()).isEqualTo(productId),
            (i) -> Assertions.assertThat(i.price()).isEqualTo(new Money("3000")),
            (i) -> Assertions.assertThat(i.quantity()).isEqualTo(new Quantity(1))
    );
  }

  @Test
  public void shouldGenerateExceptionWhenTryToChangeItemSet() {
    Order order = Order.draft(new CustomerId());
    Product product = ProductTestdataBuilder.aProductAltMousePad().build();

    order.addItem(
            product,
            new Quantity(1)
    );

    Set<OrderItem> items = order.items();

    Assertions.assertThatExceptionOfType(UnsupportedOperationException.class)
            .isThrownBy(items::clear);
  }

  @Test
  public void shouldCalculateTotals() {
    Order order = Order.draft(new CustomerId());
    ProductId productId = new ProductId();

    order.addItem(
            ProductTestdataBuilder.aProductAltMousePad().build(),
            new Quantity(2)
    );

    order.addItem(
            ProductTestdataBuilder.aProduct().build(),
            new Quantity(1)
    );

    Assertions.assertThat(order.totalAmount()).isEqualTo(new Money("3200.00"));
    Assertions.assertThat(order.totalItems()).isEqualTo(new Quantity(3));
  }

  @Test
  public void givenDraftOrder_whenPlace_shouldChangeToPlaced() {
    Order order = OrderTestDataBuilder.anOrder().build();
    order.place();
    Assertions.assertThat(order.isPlaced()).isTrue();
  }

  @Test
  public void givenPlacedOrder_whenTryToPlace_shouldGenerateException() {
    Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.PLACED).build();
    Assertions.assertThatExceptionOfType(OrderStatusCannotBeChangedException.class)
            .isThrownBy(order::place);
  }

  @Test
  public void givenDraftOrder_whenChangeBillingInfo_shouldAllowChange() {
    Address address = Address.builder()
            .street("Bourbon Street")
            .number("1234")
            .neighborhood("North Ville")
            .complement("apt. 11")
            .city("Montfort")
            .state("South Carolina")
            .zipCode(new ZipCode("79911")).build();

    BillingInfo billingInfo = BillingInfo.builder()
            .address(address)
            .document(new Document("225-09-1992"))
            .phone(new Phone("123-111-9911"))
            .fullName(new FullName("John", "Doe"))
            .build();

    Order order = Order.draft(new CustomerId());
    order.changeBilling(billingInfo);

    BillingInfo expectedBillingInfo = BillingInfo.builder()
            .address(address)
            .document(new Document("225-09-1992"))
            .phone(new Phone("123-111-9911"))
            .fullName(new FullName("John", "Doe"))
            .build();

    Assertions.assertThat(order.billing()).isEqualTo(expectedBillingInfo);
  }

  @Test
  public void givenDraftOrder_whenChangeShipping_shouldAllowChange() {

    Shipping shipping = OrderTestDataBuilder.aShipping();

    Order order = Order.draft(new CustomerId());


    order.changeShipping(shipping);

    Assertions.assertWith(order,
            o -> Assertions.assertThat(o.shipping()).isEqualTo(shipping)
    );

  }

  @Test
  public void givenDraftOrderAndDeliveryDateInThePast_whenChangeShipping_shouldNotAllowChange() {
    LocalDate expectedDeliveryDate = LocalDate.now().minusDays(2);

    Shipping shipping = OrderTestDataBuilder.aShipping().toBuilder()
            .expectedDate(expectedDeliveryDate)
            .build();

    Order order = Order.draft(new CustomerId());

    Assertions.assertThatExceptionOfType(OrderInvalidShippingDeliveryDateException.class)
            .isThrownBy(()-> order.changeShipping(shipping));
  }

  @Test
  public void givenDraftOrder_whenChangeItem_shouldRecalculate() {
    Order order = Order.draft(new CustomerId());

    order.addItem(
            ProductTestdataBuilder.aProductAltMousePad().build(),
            new Quantity(3)
    );

    OrderItem orderItem = order.items().iterator().next();

    order.changeItemQuantity(orderItem.id(), new Quantity(5));

    Assertions.assertWith(order,
            (o) -> Assertions.assertThat(o.totalAmount()).isEqualTo(new Money("500.00")),
            (o) -> Assertions.assertThat(o.totalItems()).isEqualTo(new Quantity(5))
    );
  }

  @Test
  public void givenOutOfStockProduct_whenTryToAddToAnOrder_shouldNotAllow() {
    Order order = Order.draft(new CustomerId());

    ThrowableAssert.ThrowingCallable addItemTask = () -> order.addItem(
            ProductTestdataBuilder.aProductUnavailable().build(),
            new Quantity(1)
    );

    Assertions.assertThatExceptionOfType(ProductOutOfStockException.class).isThrownBy(addItemTask);
  }
}