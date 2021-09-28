package com.francisco.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pedido")
public class Pedido {

  @Id
  private Integer id;

  @Column(name = "data_pedido")
  private LocalDateTime dataPedido;

  @Column(name = "data_conclusao")
  private LocalDateTime dataConclusao;

  @Column(name = "nota_fiscal_id")
  private Integer notaFiscalId;

  private BigDecimal total;

  private StatusPedido status;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Pedido pedido = (Pedido) o;

    return id.equals(pedido.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
