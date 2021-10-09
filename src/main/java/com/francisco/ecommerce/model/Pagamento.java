package com.francisco.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@DiscriminatorColumn(name = "tipo_pagamento", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
//@Table(name = "pagamento")
public abstract class Pagamento extends EntidadeBaseInteger {

  @MapsId
  @OneToOne(optional = false)
  @JoinColumn(name = "pedido_id")
  private Pedido pedido;

  @Enumerated(EnumType.STRING)
  private StatusPagamento status;
}
