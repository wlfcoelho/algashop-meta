package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.exception.OrderStatusCannotBeChangedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderMarkAsReadyTest {


  @Test
  void givenDraftOrder_whenMarkAsReady_shouldThrowExceptionAndNotChangeState() {
    Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.DRAFT).build();

    Assertions.assertThatExceptionOfType(OrderStatusCannotBeChangedException.class)
            .isThrownBy(order::markAsReady);

    Assertions.assertWith(order,
            (o) -> Assertions.assertThat(o.status()).isEqualTo(OrderStatus.DRAFT),
            (o) -> Assertions.assertThat(o.readyAt()).isNull()
    );
  }

  @Test
  void givenPlacedOrder_whenMarkAsReady_shouldThrowExceptionAndNotChangeState() {
    Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.PLACED).build();

    Assertions.assertThatExceptionOfType(OrderStatusCannotBeChangedException.class)
            .isThrownBy(order::markAsReady);

    Assertions.assertWith(order,
            (o) -> Assertions.assertThat(o.status()).isEqualTo(OrderStatus.PLACED),
            (o) -> Assertions.assertThat(o.readyAt()).isNull()
    );
  }
}
