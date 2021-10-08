package com.francisco.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PagamentoBoleto extends Pagamento {

  @Column(name = "codigo_barras")
  private String codigoBarras;
}
