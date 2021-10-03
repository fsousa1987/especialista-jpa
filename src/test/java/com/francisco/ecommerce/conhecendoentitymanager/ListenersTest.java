package com.francisco.ecommerce.conhecendoentitymanager;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Cliente;
import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.model.Produto;
import com.francisco.ecommerce.model.StatusPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class ListenersTest extends EntityManagerTest {

  @Test
  public void carregarEntidades() {
    Produto produto = entityManager.find(Produto.class, 1);
    Pedido pedido = entityManager.find(Pedido.class, 1);
  }

  @Test
  public void acionarCallbacks() {
    Cliente cliente = entityManager.find(Cliente.class, 1);

    Pedido pedido = new Pedido();

    pedido.setCliente(cliente);
    pedido.setStatus(StatusPedido.AGUARDANDO);

    entityManager.getTransaction().begin();

    entityManager.persist(pedido);
    entityManager.flush();

    pedido.setStatus(StatusPedido.PAGO);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
    Assertions.assertNotNull(pedidoVerificacao.getDataCriacao());
    Assertions.assertNotNull(pedidoVerificacao.getDataUltimaAtualizacao());
  }
}