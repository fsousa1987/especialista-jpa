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
@Table(name = "pagamento_cartao")
public class PagamentoCartao {

  @Id
  private Integer id;

  @Column(name = "pedido_id")
  private Integer pedidoId;

  private StatusPagamento status;

  private String numero;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PagamentoCartao that = (PagamentoCartao) o;

    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
