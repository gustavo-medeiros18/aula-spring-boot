package com.educandoweb.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity annotation indicates that this class is a JPA entity.
 * Table annotation specifies the name for the table in the database.
 * Id annotation specifies the primary key of an entity.
 * GeneratedValue(strategy = GenerationType.IDENTITY) automatically
 * generates the id.
 */

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private String phone;
  private String password;

  /**
   * OneToMany annotation establishes a one-to-many relationship between
   * User and Order entities. mappedBy attribute indicates the foreign class
   * attribute that owns the relationship.
   * <p>
   * JsonIgnore annotation prevents infinite loop when serializing objects.
   * In other words, it makes a lazy loading.
   */
  @JsonIgnore
  @OneToMany(mappedBy = "client")
  private List<Order> orders = new ArrayList<>();

  public User() {

  }

  public User(Long id, String name, String email, String phone, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getPassword() {
    return password;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
