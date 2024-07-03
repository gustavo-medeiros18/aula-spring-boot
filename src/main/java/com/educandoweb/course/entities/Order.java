package com.educandoweb.course.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Instant moment;
  // TODO: orderStatus field

  /**
   * ManyToOne annotation establishes a many-to-one relationship between
   * Order and User entities. JoinColumn annotation specifies the foreign key
   * column that will be used to establish the relationship.
   */
  @ManyToOne
  @JoinColumn(name = "client_id")
  private User client;

  public Order() {

  }

  public Order(Long id, Instant moment) {
    this.id = id;
    this.moment = moment;
  }

  public Long getId() {
    return id;
  }

  public Instant getMoment() {
    return moment;
  }

  public User getClient() {
    return client;
  }

  public void setMoment(Instant moment) {
    this.moment = moment;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return Objects.equals(id, order.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
