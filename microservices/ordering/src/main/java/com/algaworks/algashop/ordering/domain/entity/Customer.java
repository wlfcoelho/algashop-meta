package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.validator.FieldValidations;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.*;


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
                  Boolean promotionNotificationsAloowed, OffsetDateTime registeredAt) {
    this.setId(id);
    this.setFullName(fullName);
    this.setBirthDate(birthDate);
    this.setEmail(email);
    this.setPhone(phone);
    this.setDocument(document);
    this.setRegisteredAt(registeredAt);
    this.setPromotionNotificationsAloowed(promotionNotificationsAloowed);
    this.setArchived(false);
    this.setLoyaltyPoints(0);
  }

  public Customer(UUID id, String fullName, LocalDate birthDate, String email, String phone, String document,
                  Boolean promotionNotificationsAloowed, Boolean archived,
                  OffsetDateTime registeredAt, Integer loyaltyPoints) {
    this.setId(id);
    this.setFullName(fullName);
    this.setBirthDate(birthDate);
    this.setEmail(email);
    this.setPhone(phone);
    this.setDocument(document);
    this.setRegisteredAt(registeredAt);
    this.setPromotionNotificationsAloowed(promotionNotificationsAloowed);
    this.setArchived(archived);
    this.setLoyaltyPoints(loyaltyPoints);
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
    Objects.requireNonNull(id);
    this.id = id;
  }

  private void setBirthDate(LocalDate birthDate) {
    if (birthDate == null){
      this.birthDate = null;
      return;
    }
    if(birthDate.isAfter(LocalDate.now())){
      throw new IllegalArgumentException(VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST);

    }
    this.birthDate = birthDate;
  }

  private void setFullName(String fullName) {
    Objects.requireNonNull(fullName, VALIDATION_ERROR_FULLNAME_IS_NULL);
    if(fullName.isBlank()){
      throw new IllegalArgumentException(VALIDATION_ERROR_FULLNAME_IS_BLANK);
    }
    this.fullName = fullName;
  }

  private void setEmail(String email) {
    FieldValidations.requiresValidEmail(email, VALIDATION_ERROR_EMAIL_IS_INVALID);
    this.email = email;
  }

  private void setPhone(String phone) {
    Objects.requireNonNull(phone);
    this.phone = phone;
  }

  private void setDocument(String document) {
    Objects.requireNonNull(document);
    this.document = document;
  }

  private void setPromotionNotificationsAloowed(Boolean promotionNotificationsAloowed) {
    Objects.requireNonNull(promotionNotificationsAloowed);
    this.promotionNotificationsAloowed = promotionNotificationsAloowed;
  }

  private void setArchived(Boolean archived) {
    Objects.requireNonNull(archived);
    this.archived = archived;
  }

  private void setRegisteredAt(OffsetDateTime registeredAt) {
    Objects.requireNonNull(registeredAt);
    this.registeredAt = registeredAt;
  }

  private void setArchivedAt(OffsetDateTime archivedAt) {
    Objects.requireNonNull(archivedAt);
    this.archivedAt = archivedAt;
  }

  private void setLoyaltyPoints(Integer loyaltyPoints) {
    Objects.requireNonNull(loyaltyPoints);
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
