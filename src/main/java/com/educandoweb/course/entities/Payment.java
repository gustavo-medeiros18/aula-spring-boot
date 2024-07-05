package com.educandoweb.course.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Instant moment;

  /**
   * OneToOne annotation establishes a one-to-one relationship between
   * Payment and Order entities. MapsId annotation tells JPA that the id
   * attribute of Payment is the same as the id attribute of Order.
   * <p>
   * Remember that Payment entity is the weak entity in the relationship.
   * The Payment entity depends on the Order entity to exist.
   */
  @OneToOne()
  @MapsId
  private Order order;

  public Payment() {

  }

  public Payment(Long id, Instant moment, Order order) {
    this.id = id;
    this.moment = moment;
    this.order = order;
  }

  public Long getId() {
    return id;
  }

  public Instant getMoment() {
    return moment;
  }

  public Order getOrder() {
    return order;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setMoment(Instant moment) {
    this.moment = moment;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Payment payment = (Payment) o;
    return Objects.equals(id, payment.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
