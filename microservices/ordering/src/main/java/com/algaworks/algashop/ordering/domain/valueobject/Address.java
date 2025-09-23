package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.domain.validator.FieldValidations;
import lombok.Builder;

import java.util.Objects;

public record Address(
        String street,
        String complement,
        String neighborhood,
        String number,
        String city,
        String state,
        ZipCode zipCode) {

  @Builder(toBuilder = true)
  public Address{
    FieldValidations.requeresNonBlank(street);
    FieldValidations.requeresNonBlank(neighborhood);
    FieldValidations.requeresNonBlank(number);
    FieldValidations.requeresNonBlank(city);
    FieldValidations.requeresNonBlank(state);
    Objects.requireNonNull(zipCode);
  }
}
