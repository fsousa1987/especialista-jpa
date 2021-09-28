package com.francisco.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria {

  @Id
  private Integer id;

  private String nome;

  @Column(name = "categoria_pai_id")
  private Integer categoriaPaiId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Categoria categoria = (Categoria) o;

    return id.equals(categoria.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}