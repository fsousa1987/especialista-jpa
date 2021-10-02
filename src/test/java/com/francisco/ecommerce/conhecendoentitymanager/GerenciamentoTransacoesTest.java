package com.francisco.ecommerce.conhecendoentitymanager;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.model.StatusPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GerenciamentoTransacoesTest extends EntityManagerTest {

  public void abrirFecharCancelarTransacao() {
    try {
      entityManager.getTransaction().begin();
      metodoDeNegocio();
      entityManager.getTransaction().commit();
    }
    catch (Exception e) {
      entityManager.getTransaction().rollback();
      throw e;
    }
  }

  private void metodoDeNegocio() {
    Pedido pedido = entityManager.find(Pedido.class, 1);
    pedido.setStatus(StatusPedido.PAGO);

    if (pedido.getPagamento() == null) {
      throw new RuntimeException("Pedido ainda não foi pago!");
    }
  }

  @Test
  public void abrirFecharCancelarTransacaoTest() {
    Exception thrown = Assertions.assertThrows(Exception.class,
        this::abrirFecharCancelarTransacao, "Pedido ainda não foi pago!");

    Assertions.assertTrue(thrown.getMessage().contains("Pedido"));
  }
}
