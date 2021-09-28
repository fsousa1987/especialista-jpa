package com.francisco.ecommerce.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ItemPedido {

  @Id
  private Integer id;

  private Integer pedidoId;

  private Integer produtoId;

  private BigDecimal precoProduto;

  private Integer quantidade;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ItemPedido that = (ItemPedido) o;

    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
