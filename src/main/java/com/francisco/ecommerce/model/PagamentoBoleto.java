package com.francisco.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PagamentoBoleto {

  @Id
  private Integer id;

  private Integer pedidoId;

  private StatusPagamento status;

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
