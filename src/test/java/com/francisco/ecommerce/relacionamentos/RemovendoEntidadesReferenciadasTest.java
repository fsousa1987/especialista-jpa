package com.francisco.ecommerce.relacionamentos;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RemovendoEntidadesReferenciadasTest extends EntityManagerTest {

  @Test
  public void removerEntidadeRelacionada() {
    Pedido pedido = entityManager.find(Pedido.class, 1);

    Assertions.assertFalse(pedido.getItens().isEmpty());

    entityManager.getTransaction().begin();
    pedido.getItens().forEach(item -> entityManager.remove(item));
    entityManager.remove(pedido);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
    Assertions.assertNull(pedidoVerificacao);
  }
}
