package com.algaworks.algashop.ordering.infrastructure.persistence.provider;

import com.algaworks.algashop.ordering.domain.model.entity.Order;
import com.algaworks.algashop.ordering.domain.model.repository.Orders;
import com.algaworks.algashop.ordering.domain.model.valueobject.id.OrderId;
import com.algaworks.algashop.ordering.infrastructure.persistence.assembler.OrderPersistenceEntityAssembler;
import com.algaworks.algashop.ordering.infrastructure.persistence.disassembler.OrderPersistenceEntityDisassembler;
import com.algaworks.algashop.ordering.infrastructure.persistence.entity.OrderPersistenceEntity;
import com.algaworks.algashop.ordering.infrastructure.persistence.repository.OrderPersistenceEntityRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrdersPersistenceProvider implements Orders {

  private final OrderPersistenceEntityRepository orderRepository;
  private final OrderPersistenceEntityAssembler orderAssembler;
  private final OrderPersistenceEntityDisassembler orderDisassembler;

  private final EntityManager entityManager;

  @Override
  public Optional<Order> ofId(OrderId orderId) {
    Optional<OrderPersistenceEntity> possibleEntity = orderRepository.findById(orderId.value().toLong());

    return possibleEntity.map(orderDisassembler::toDomainEntity);
  }

  @Override
  public boolean exists(OrderId orderId) {
    return false;
  }

  @Override
  public void add(Order aggregateRoot) {
    long orderId = aggregateRoot.id().value().toLong();

    orderRepository.findById(orderId)
            .ifPresentOrElse(
                    (persistenceEntity) -> {
                      update(aggregateRoot, persistenceEntity);
                    },
                    ()-> {
                      insert(aggregateRoot);
                    }
            );
  }

  private void update(Order aggregateRoot, OrderPersistenceEntity persistenceEntity) {
    persistenceEntity = orderAssembler.merge(persistenceEntity, aggregateRoot);
    entityManager.detach(persistenceEntity);
    persistenceEntity = orderRepository.saveAndFlush(persistenceEntity);
    updateVersion(aggregateRoot, persistenceEntity);
  }

  private void insert(Order aggregateRoot) {
    OrderPersistenceEntity persistenceEntity = orderAssembler.fromDomain(aggregateRoot);
    orderRepository.saveAndFlush(persistenceEntity);
    updateVersion(aggregateRoot, persistenceEntity);
  }

  @SneakyThrows
  private void updateVersion(Order aggregateRoot, OrderPersistenceEntity persistenceEntity) {
    Field version = aggregateRoot.getClass().getDeclaredField("version");
    version.setAccessible(true);
    ReflectionUtils.setField(version, aggregateRoot, persistenceEntity.getVersion());
    version.setAccessible(false);

  }

  @Override
  public int count() {
    return 0;
  }
}
