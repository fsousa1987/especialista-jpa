package com.francisco.ecommerce.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoId implements Serializable {

  private Integer pedidoId;

  private Integer produtoId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ItemPedidoId that = (ItemPedidoId) o;

    if (!pedidoId.equals(that.pedidoId)) {
      return false;
    }
    return produtoId.equals(that.produtoId);
  }

  @Override
  public int hashCode() {
    int result = pedidoId.hashCode();
    result = 31 * result + produtoId.hashCode();
    return result;
  }
}
