package com.algaworks.algashop.ordering.domain.valueobject;

import lombok.Builder;
import org.apache.commons.beanutils.BaseDynaBeanMapDecorator;

import java.util.Objects;

@Builder
public record BillingInfo(Fullname fullname, Document document, Phone phone, Address address) {

  public BillingInfo {
    Objects.requireNonNull(fullname);
    Objects.requireNonNull(document);
    Objects.requireNonNull(phone);
    Objects.requireNonNull(address);
  }
}
