package com.francisco.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pedido {

  @Id
  private Integer id;

  private LocalDateTime dataPedido;

  private LocalDateTime dataConclusao;

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
