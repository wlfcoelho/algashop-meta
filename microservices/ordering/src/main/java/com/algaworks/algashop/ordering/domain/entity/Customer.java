package com.algaworks.algashop.ordering.domain.entity;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;


public class Customer {

  private UUID id;
  private String fullName;
  private LocalDate birthDate;
  private String email;
  private String phone;
  private String document;
  private Boolean promotionNotificationsAloowed;
  private Boolean archived;
  private OffsetDateTime registeredAt;
  private OffsetDateTime archivedAt;
  private Integer loyaltyPoints;


  public Customer(UUID id, String fullName, LocalDate birthDate, String email, String phone, String document,
                  OffsetDateTime registeredAt, Boolean promotionNotificationsAloowed) {
    this.id = id;
    this.fullName = fullName;
    this.birthDate = birthDate;
    this.email = email;
    this.phone = phone;
    this.document = document;
    this.registeredAt = registeredAt;
    this.promotionNotificationsAloowed = promotionNotificationsAloowed;
  }

  public Customer(UUID id, String fullName, LocalDate birthDate, String email, String phone, String document,
                  Boolean promotionNotificationsAloowed, Boolean archived, OffsetDateTime registeredAt,
                  OffsetDateTime archivedAt, Integer loyaltyPoints) {
    this.id = id;
    this.fullName = fullName;
    this.birthDate = birthDate;
    this.email = email;
    this.phone = phone;
    this.document = document;
    this.promotionNotificationsAloowed = promotionNotificationsAloowed;
    this.archived = archived;
    this.registeredAt = registeredAt;
    this.archivedAt = archivedAt;
    this.loyaltyPoints = loyaltyPoints;
  }

  public String fullName() {
    return fullName;
  }

  public UUID id() {
    return id;
  }

  public LocalDate birthDate() {
    return birthDate;
  }

  public String email() {
    return email;
  }

  public String phone() {
    return phone;
  }

  public String document() {
    return document;
  }

  public Boolean IsPromotionNotificationsAloowed() {
    return promotionNotificationsAloowed;
  }

  public Boolean isArchived() {
    return archived;
  }

  public OffsetDateTime registeredAt() {
    return registeredAt;
  }

  public OffsetDateTime archivedAt() {
    return archivedAt;
  }

  public Integer loyaltyPoints() {
    return loyaltyPoints;
  }

  public void addLoyaltyPoints(Integer points) {
  }

  public void archive() {
  }

  public void enablePromotionNotifications() {
    this.setPromotionNotificationsAloowed(true);
  }

  public void disablePromotionNotifications() {
    this.setPromotionNotificationsAloowed(false);
  }

  public void changeName(String fullName) {
    this.setFullName(fullName);
  }

  public void changeEmail(String email) {
    this.setEmail(email);
  }

  public void changePhone(String phone) {
    this.setPhone(phone);
  }

  private void setId(UUID id) {
    this.id = id;
  }

  private void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  private void setFullName(String fullName) {
    this.fullName = fullName;
  }

  private void setEmail(String email) {
    this.email = email;
  }

  private void setPhone(String phone) {
    this.phone = phone;
  }

  private void setDocument(String document) {
    this.document = document;
  }

  private void setPromotionNotificationsAloowed(Boolean promotionNotificationsAloowed) {
    this.promotionNotificationsAloowed = promotionNotificationsAloowed;
  }

  private void setArchived(Boolean archived) {
    this.archived = archived;
  }

  private void setRegisteredAt(OffsetDateTime registeredAt) {
    this.registeredAt = registeredAt;
  }

  private void setArchivedAt(OffsetDateTime archivedAt) {
    this.archivedAt = archivedAt;
  }

  private void setLoyaltyPoints(Integer loyaltyPoints) {
    this.loyaltyPoints = loyaltyPoints;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer = (Customer) o;
    return Objects.equals(id, customer.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
