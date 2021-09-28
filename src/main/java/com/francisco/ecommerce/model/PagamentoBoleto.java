package com.francisco.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pagamento_boleto")
public class PagamentoBoleto {

  @Id
  private Integer id;

  @Column(name = "pedido_id")
  private Integer pedidoId;

  @Enumerated(EnumType.STRING)
  private StatusPagamento status;

  @Column(name = "codigo_barras")
  private String codigoBarras;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PagamentoBoleto that = (PagamentoBoleto) o;

    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
