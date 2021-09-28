package com.francisco.ecommerce.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

  @Id
  private Integer id;

  @Column(name = "pedido_id")
  private Integer pedidoId;

  @Column(name = "produto_id")
  private Integer produtoId;

  @Column(name = "preco_produto")
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