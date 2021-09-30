package com.francisco.ecommerce.relacionamentos;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Pedido;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class OptionalTest extends EntityManagerTest {

  @Test
  public void verificarComportamento() {
    Pedido pedido = entityManager.find(Pedido.class, 1);

  }
}
