package com.algaworks.algashop.ordering.domain.valueobjects;

import com.algaworks.algashop.ordering.domain.valueobject.Money;
import com.algaworks.algashop.ordering.domain.valueobject.Quantity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {

  @Test
  void deveCriarMoneyComValorValido() {
    Money money = new Money("100.00");
    assertEquals(new Money("100.00"), money);
  }

  @Test
  void deveCriarMoneyComValorNegativo() {
    assertThrows(IllegalArgumentException.class, () -> new Money("-100.00"));
  }

  @Test
  void deveMultiplicarMoneyPorQuantidadeValida() {
    Money money = new Money("10.00");
    Quantity quantity = new Quantity(3);
    Money resultado = money.multiply(quantity);
    assertEquals(new Money("30.00"), resultado);
  }

  @Test
  void deveLancarExcecaoAoMultiplicarPorQuantidadeInvalida() {
    Money money = new Money("10.00");
    Quantity quantidadeZero = new Quantity(0);
    assertThrows(IllegalArgumentException.class, () -> money.multiply(quantidadeZero));
  }

  @Test
  void deveSomarDoisValoresMoney() {
    Money m1 = new Money("10.00");
    Money m2 = new Money("5.50");
    Money resultado = m1.add(m2);
    assertEquals(new Money("15.50"), resultado);
  }

  @Test
  void deveRetornarToStringCorreto() {
    Money m = new Money("12.34");
    assertEquals("12.34", m.toString());
  }

  @Test
  void deveCompararValoresMoney() {
    Money m1 = new Money("10.00");
    Money m2 = new Money("20.00");
    assertEquals(-1, m1.compareTo(m2));
    assertEquals(1, m2.compareTo(m1));
    assertEquals(0, m1.compareTo(new Money("10.00")));
  }

  @Test
  void deveDividirValoresMoney() {
    Money m1 = new Money("10.00");
    Money m2 = new Money("2.00");
    Money resultado = m1.divide(m2);
    assertEquals(new Money("5.00"), resultado);
  }

}
