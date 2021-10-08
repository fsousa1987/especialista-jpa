package com.francisco.ecommerce.model;

import com.francisco.ecommerce.listener.GenericoListener;
import com.francisco.ecommerce.listener.GerarNotaFiscalListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EntityListeners({ GerarNotaFiscalListener.class, GenericoListener.class})
@Entity
@Table(name = "pedido")
public class Pedido extends EntidadeBaseInteger {

  @ManyToOne(optional = false)
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  @OneToMany(mappedBy = "pedido")
  private List<ItemPedido> itens;

  @Column(name = "data_criacao", updatable = false)
  private LocalDateTime dataCriacao;

  @Column(name = "data_ultima_atualizacao", insertable = false)
  private LocalDateTime dataUltimaAtualizacao;

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

  public boolean isPago() {
    return StatusPedido.PAGO.equals(status);
  }

//  @PrePersist
//  @PreUpdate
  public void calcularTotal() {
    if (itens != null) {
      total = itens.stream()
          .map(ItemPedido::getPrecoProduto)
          .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
  }

  @PrePersist
  public void aoPersistir() {
    dataCriacao = LocalDateTime.now();
    calcularTotal();
  }

  @PreUpdate
  public void aoAtualizar() {
    dataUltimaAtualizacao = LocalDateTime.now();
    calcularTotal();
  }

  @PostPersist
  public void aposPersistir() {
    System.out.println("Após persistir Pedido.");
  }

  @PostUpdate
  public void aposAtualizar() {
    System.out.println("Após atualizar Pedido.");
  }

  @PreRemove
  public void aoRemover() {
    System.out.println("Antes de remover Pedido.");
  }

  @PostRemove
  public void aposRemover() {
    System.out.println("Após remover Pedido.");
  }

  @PostLoad
  public void aoCarregar() {
    System.out.println("Após carregar o Pedido.");
  }
}
