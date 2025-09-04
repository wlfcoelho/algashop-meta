package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.exception.CustomerArchivedException;
import com.algaworks.algashop.ordering.domain.valueobject.*;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_NULL;


public class Customer {

  private CustomerId id;
  private Fullname fullName;
  private BirthDate birthDate;
  private Email email;
  private Phone phone;
  private Document document;
  private Boolean promotionNotificationsAloowed;
  private Boolean archived;
  private OffsetDateTime registeredAt;
  private OffsetDateTime archivedAt;
  private LoyaltyPoints loyaltyPoints;


  public Customer(CustomerId id, Fullname fullName, BirthDate birthDate, Email email, Phone phone, Document document,
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
    this.setLoyaltyPoints(LoyaltyPoints.ZERO);
  }

  public Customer(CustomerId id, Fullname fullName, BirthDate birthDate, Email email, Phone phone,
                  Document document, Boolean promotionNotificationsAloowed, Boolean archived,
                  OffsetDateTime registeredAt, OffsetDateTime archivedAt, LoyaltyPoints loyaltyPoints) {
    this.setId(id);
    this.setFullName(fullName);
    this.setBirthDate(birthDate);
    this.setEmail(email);
    this.setPhone(phone);
    this.setDocument(document);
    this.setPromotionNotificationsAloowed(promotionNotificationsAloowed);
    this.setArchived(archived);
    this.setRegisteredAt(registeredAt);
    this.setArchivedAt(archivedAt);
    this.setLoyaltyPoints(loyaltyPoints);
  }

  public Fullname fullName() {
    return fullName;
  }

  public CustomerId id() {
    return id;
  }

  public BirthDate birthDate() {
    return birthDate;
  }

  public Email email() {
    return email;
  }

  public Phone phone() {
    return phone;
  }

  public Document document() {
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

  public LoyaltyPoints loyaltyPoints() {
    return loyaltyPoints;
  }

  public void addLoyaltyPoints(Integer points) {
  }

  public void archive() {
    verifyIfChangeable();
    this.setArchived(true);
    this.setArchivedAt(OffsetDateTime.now());
    this.setFullName(new Fullname("Anonymous", "Anonymous"));
    this.setPhone(new Phone("000-000-0000"));
    this.setDocument(new Document("000-00-0000"));
    this.setEmail(new Email(UUID.randomUUID() + "@anonymous.com"));
    this.setBirthDate(null);
    this.setPromotionNotificationsAloowed(false);
  }

  public void addLoyaltyPoints(LoyaltyPoints loyaltyPointsAdded) {
    verifyIfChangeable();
    this.setLoyaltyPoints(this.loyaltyPoints().add(loyaltyPointsAdded));
  }

  public void enablePromotionNotifications() {
    verifyIfChangeable();
    this.setPromotionNotificationsAloowed(true);
  }

  public void disablePromotionNotifications() {
    verifyIfChangeable();
    this.setPromotionNotificationsAloowed(false);
  }

  public void changeName(Fullname fullName) {
    verifyIfChangeable();
    this.setFullName(fullName);
  }

  public void changeEmail(Email email) {
    verifyIfChangeable();
    this.setEmail(email);
  }

  public void changePhone(Phone phone) {
    verifyIfChangeable();
    this.setPhone(phone);
  }

  private void setId(CustomerId id) {
    Objects.requireNonNull(id);
    this.id = id;
  }

  private void setBirthDate(BirthDate birthDate) {
    if (birthDate == null){
      this.birthDate = null;
      return;
    }
    this.birthDate = birthDate;
  }

  private void setFullName(Fullname fullName) {
    Objects.requireNonNull(fullName, VALIDATION_ERROR_FULLNAME_IS_NULL);
    this.fullName = fullName;
  }

  private void setEmail(Email email) {
    this.email = email;
  }

  private void setPhone(Phone phone) {
    this.phone = phone;
  }

  private void setDocument(Document document) {
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

  private void setLoyaltyPoints(LoyaltyPoints loyaltyPoints) {
    Objects.requireNonNull(loyaltyPoints);
    this.loyaltyPoints = loyaltyPoints;
  }

  private void verifyIfChangeable() {
    if (this.isArchived()) {
      throw new CustomerArchivedException();
    }
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
