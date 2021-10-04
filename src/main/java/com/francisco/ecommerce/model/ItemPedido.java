package com.francisco.ecommerce.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@IdClass(ItemPedidoId.class)
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

  @Id
  @Column(name = "pedido_id")
  private Integer pedidoId;

  @Id
  @Column(name = "produto_id")
  private Integer produtoId;

  @ManyToOne(optional = false)
  @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
  private Pedido pedido;

  @ManyToOne(optional = false)
  @JoinColumn(name = "produto_id", insertable = false, updatable = false)
  private Produto produto;

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
