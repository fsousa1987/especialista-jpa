package com.francisco.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pedido")
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  @OneToMany(mappedBy = "pedido")
  private List<ItemPedido> itens;

  @Column(name = "data_pedido")
  private LocalDateTime dataPedido;

  @Column(name = "data_conclusao")
  private LocalDateTime dataConclusao;

  @OneToOne(mappedBy = "pedido")
  private NotaFiscal notaFiscal;

  private BigDecimal total;

  @Enumerated(EnumType.STRING)
  private StatusPedido status;

  @OneToOne(mappedBy = "pedido")
  private PagamentoCartao pagamento;

  @Embedded
  private EnderecoEntregaPedido enderecoEntrega;

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
