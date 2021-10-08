package com.francisco.ecommerce.mapeamentoavancado;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Cliente;
import com.francisco.ecommerce.model.Pagamento;
import com.francisco.ecommerce.model.PagamentoCartao;
import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.model.StatusPagamento;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HerancaTest extends EntityManagerTest {

  @Test
  public void salvarCliente() {
    Cliente cliente = new Cliente();
    cliente.setNome("Fernanda Morais");

    entityManager.getTransaction().begin();
    entityManager.persist(cliente);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
    Assertions.assertNotNull(clienteVerificacao.getId());
  }

  @Test
  public void buscarPagamentos() {
    List<Pagamento> pagamentos = entityManager
        .createQuery("select p from Pagamento p", Pagamento.class)
        .getResultList();

    Assertions.assertFalse(pagamentos.isEmpty());
  }

  @Test
  public void incluirPagamentoPedido() {
    Pedido pedido = entityManager.find(Pedido.class, 1);

    PagamentoCartao pagamentoCartao = new PagamentoCartao();
    pagamentoCartao.setPedido(pedido);
    pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
    pagamentoCartao.setNumeroCartao("123");

    entityManager.getTransaction().begin();
    entityManager.persist(pagamentoCartao);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
    Assertions.assertNotNull(pedidoVerificacao.getPagamento());
  }
}
