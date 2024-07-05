package com.educandoweb.course.entities;

import com.educandoweb.course.entities.enums.OrderStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Instant moment;
  /**
   * OrderStatus is an enum type in the Order class. But, in the database, the
   * OrderStatus is an integer. To store the OrderStatus in the database, we need
   * to convert the OrderStatus to its corresponding integer code.
   */
  private Integer orderStatus;
  /**
   * ManyToOne annotation establishes a many-to-one relationship between
   * Order and User entities. JoinColumn annotation specifies the foreign key
   * column that will be used to establish the relationship.
   */
  @ManyToOne
  @JoinColumn(name = "client_id")
  private User client;

  @OneToMany(mappedBy = "id.order")
  private Set<OrderItem> items = new HashSet<>();

  /**
   * OneToOne annotation establishes a one-to-one relationship between
   * Order and Payment entities. mappedBy attribute tells JPA that the
   * order attribute in the Payment entity is the owner of the relationship.
   * <p>
   * Remember that Payment entity is the weak entity in the relationship.
   * The Payment entity depends on the Order entity to exist. So, the
   * cascade attribute is set to CascadeType.ALL to ensure that the Payment
   * entity is deleted when the Order entity is deleted.
   */
  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
  private Payment payment;

  public Order() {

  }

  public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
    this.id = id;
    this.moment = moment;
    this.orderStatus = orderStatus.getCode();
    this.client = client;
  }

  public Long getId() {
    return id;
  }

  public Instant getMoment() {
    return moment;
  }

  public OrderStatus getOrderStatus() {
    return OrderStatus.valueOf(orderStatus);
  }

  public User getClient() {
    return client;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public void setMoment(Instant moment) {
    this.moment = moment;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    if (orderStatus != null)
      this.orderStatus = orderStatus.getCode();
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getTotal() {
    double sum = 0.0;

    for (OrderItem x : items)
      sum += x.getSubTotal();

    return sum;
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
