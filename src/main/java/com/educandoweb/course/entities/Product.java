package com.educandoweb.course.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private Double price;
  private String imgUrl;

  /**
   * ManyToMany annotation is used to create a many-to-many
   * relationship between two entities.
   * <p>
   * JoinTable annotation is used to define the pivot table.
   * It needs to define the name of the pivot table and the
   * foreign keys that will be used to join the two tables.
   * <p>
   * In the other entity, the ManyToMany annotation needs to
   * define the mappedBy attribute to indicate the attribute
   * from this entity that is the owner of the relationship.
   */
  @ManyToMany
  @JoinTable(
      name = "tb_product_category",
      joinColumns = @JoinColumn(name = "product_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private Set<Category> categories = new HashSet<>();

  public Product() {

  }

  public Product(
      Long id,
      String name,
      String description,
      Double price,
      String imgUrl
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.imgUrl = imgUrl;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Double getPrice() {
    return price;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public Set<Category> getCategories() {
    return categories;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(id, product.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
