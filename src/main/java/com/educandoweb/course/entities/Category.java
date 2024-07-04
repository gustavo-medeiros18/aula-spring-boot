package com.educandoweb.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  /**
   * Here, the ManyToMany annotation is used just to inform that
   * the "categories" attribute from the other involved entity is
   * the owner of the relationship.
   */
  @JsonIgnore
  @ManyToMany(mappedBy = "categories")
  private Set<Product> products = new HashSet<>();

  public Category() {

  }

  public Category(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Set<Product> getProducts() {
    return products;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Category category = (Category) o;
    return Objects.equals(id, category.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
