package com.educandoweb.course.entities.pk;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

/**
 * Embeddable annotation is used to specify a class whose
 * instances are stored as an intrinsic part of an owning
 * entity and share the identity of the entity.
 * <p>
 * In this example, the OrderItemPK class is an embeddable class
 * that represents the composite primary key of the OrderItem class, which is
 * a pivot table that represents the many-to-many relationship between
 * the Order and Product classes.
 * <p>
 * The usage of a composite primary key is necessary when
 * working with many-to-many relationship that has additional
 * attributes in the pivot table.
 */
@Embeddable
public class OrderItemPK implements Serializable {
  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  public Order getOrder() {
    return order;
  }

  public Product getProduct() {
    return product;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OrderItemPK that = (OrderItemPK) o;
    return Objects.equals(order, that.order) && Objects.equals(product, that.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(order, product);
  }
}
