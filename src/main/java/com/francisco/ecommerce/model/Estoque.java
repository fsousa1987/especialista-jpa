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
@Table(name = "estoque")
public class Estoque {

  @Id
  private Integer id;

  @Column(name = "produto_id")
  private Integer produtoId;

  private Integer quantidade;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Estoque estoque = (Estoque) o;

    return id.equals(estoque.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
