package com.francisco.ecommerce.relacionamentos;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.PagamentoCartao;
import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.model.StatusPagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RelacionamentoOneToOneTest extends EntityManagerTest {

  @Test
  public void verificarRelacionamento() {
    Pedido pedido = entityManager.find(Pedido.class, 1);

    PagamentoCartao pagamentoCartao = new PagamentoCartao();
    pagamentoCartao.setNumero("1234");
    pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
    pagamentoCartao.setPedido(pedido);

    entityManager.getTransaction().begin();
    entityManager.persist(pagamentoCartao);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
    Assertions.assertNotNull(pedidoVerificacao.getPagamento());
  }
}
