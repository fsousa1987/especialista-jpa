package com.francisco.ecommerce.conhecendoentitymanager;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.model.StatusPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("CommentedOutCode")
public class FlushTest extends EntityManagerTest {

  public void chamarFlush() {
    try {
      entityManager.getTransaction().begin();

      Pedido pedido = entityManager.find(Pedido.class, 1);
      pedido.setStatus(StatusPedido.PAGO);

      entityManager.flush();

      if (pedido.getPagamento() == null) {
        throw new RuntimeException("Pedido ainda não foi pago");
      }

//      Uma consulta obriga o JPA a sincronizar o que ele tem na memória
//      Pedido pedidoPago = entityManager
//          .createQuery("select p from Pedido p where p.id = 1", Pedido.class)
//          .getSingleResult();
//      Assertions.assertEquals(pedido.getStatus(), pedidoPago.getStatus());

      entityManager.getTransaction().commit();
    }
    catch (Exception e) {
      entityManager.getTransaction().rollback();
      throw e;
    }
  }

  @Test
  public void chamarFlushTest() {
    Exception thrown = Assertions.assertThrows(Exception.class,
        this::chamarFlush, "Pedido ainda não foi pago");

    Assertions.assertTrue(thrown.getMessage().contains("Pedido"));
  }
}
