package com.francisco.ecommerce.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JoinColumn(name = "pedido_id")
//  @JoinTable(
//      name = "pedido_nota_fiscal",
//      joinColumns = @JoinColumn(name = "nota_fiscal_id", unique = true),
//      inverseJoinColumns = @JoinColumn(name = "pedido_id", unique = true)
//  )
  private Pedido pedido;

  private String xml;

  @Column(name = "data_emissao")
  private Date dataEmissao;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    NotaFiscal that = (NotaFiscal) o;

    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
