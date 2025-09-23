package com.algaworks.algashop.ordering.domain.valueobject;

import lombok.Builder;

import java.util.Objects;

@Builder
public record ShippingInfo (Fullname fullname, Document document, Phone phone, Address address){

  public ShippingInfo{
    Objects.requireNonNull(fullname);
    Objects.requireNonNull(document);
    Objects.requireNonNull(phone);
    Objects.requireNonNull(address);

  }
}
