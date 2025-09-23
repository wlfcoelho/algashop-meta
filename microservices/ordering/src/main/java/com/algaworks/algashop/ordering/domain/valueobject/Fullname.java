package com.algaworks.algashop.ordering.domain.valueobject;

import java.util.Objects;

public record Fullname(String firstName, String lastName) {

  public Fullname(String firstName, String lastName){
    Objects.requireNonNull(firstName);
    Objects.requireNonNull(lastName);

    if(firstName.isBlank()){
      throw new IllegalArgumentException();
    }

    if(lastName.isBlank()){
      throw new IllegalArgumentException();
    }

    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return  firstName + "" + lastName;
  }
}
