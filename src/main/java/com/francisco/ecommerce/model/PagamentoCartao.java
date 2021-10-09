package com.francisco.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@DiscriminatorValue("cartao")
@Entity
@Table(name = "pagamento_cartao")
public class PagamentoCartao extends Pagamento {

  @Column(name = "numero_cartao")
  private String numeroCartao;
}
