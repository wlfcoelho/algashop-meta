package com.algaworks.algashop.ordering.infrastructure.persistence.entity;

import com.algaworks.algashop.ordering.domain.model.utility.IdGenerator;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

public class OrderPersistenceEntityTestDataBuilder {

  private OrderPersistenceEntityTestDataBuilder(){
  }

  public static OrderPersistenceEntity.OrderPersistenceEntityBuilder existingOrder() {
    return OrderPersistenceEntity.builder()
            .id(IdGenerator.generateTimeBasedTSID().toLong())
            .customerId(IdGenerator.generateTimeBasedUUID())
            .totalItems(4)
            .totalAmount(new BigDecimal(1250))
            .status("DRAFT")
            .paymentMethod("CREDIT_CARD")
            .placedAt(OffsetDateTime.now())
            .items(Set.of(
                    existingItem().build(),
                    existingItemAlt().build()
            ));
  }

  public static OrderItemPersistenceEntity.OrderItemPersistenceEntityBuilder existingItem(){
    return OrderItemPersistenceEntity.builder()
            .id(IdGenerator.generateTimeBasedTSID().toLong())
            .productId(IdGenerator.generateTimeBasedUUID())
            .productName("NoteBook")
            .price(new BigDecimal(500))
            .quantity(2)
            .totalAmount(new BigDecimal(1000));
  }

  public static OrderItemPersistenceEntity.OrderItemPersistenceEntityBuilder existingItemAlt(){
    return OrderItemPersistenceEntity.builder()
            .id(IdGenerator.generateTimeBasedTSID().toLong())
            .productId(IdGenerator.generateTimeBasedUUID())
            .productName("MousePad")
            .price(new BigDecimal(250))
            .quantity(1)
            .totalAmount(new BigDecimal(250));
  }
}
