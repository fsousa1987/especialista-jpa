package com.francisco.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PagamentoCartao extends Pagamento {

  @Column(name = "numero_cartao")
  private String numeroCartao;
}
