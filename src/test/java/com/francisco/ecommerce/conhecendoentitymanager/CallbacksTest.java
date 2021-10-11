package com.francisco.ecommerce.conhecendoentitymanager;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Cliente;
import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.model.StatusPedido;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CallbacksTest extends EntityManagerTest {

  @Test
  public void acionarCallbacks() {
    Cliente cliente = entityManager.find(Cliente.class, 1);

    Pedido pedido = new Pedido();

    pedido.setCliente(cliente);
    pedido.setStatus(StatusPedido.AGUARDANDO);
    pedido.setTotal(new BigDecimal(1225));

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
