package com.francisco.ecommerce.relacionamentos;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Cliente;
import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.model.StatusPedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RelacionamentoOneToManyTest extends EntityManagerTest {

  @Test
  public void verificarRelacionamento() {
    Cliente cliente = entityManager.find(Cliente.class, 1);

    Pedido pedido = new Pedido();
    pedido.setStatus(StatusPedido.AGUARDANDO);
    pedido.setDataPedido(LocalDateTime.now());
    pedido.setTotal(BigDecimal.TEN);

    pedido.setCliente(cliente);

    entityManager.getTransaction().begin();
    entityManager.persist(pedido);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
    Assertions.assertFalse(clienteVerificacao.getPedidos().isEmpty());
  }
}
