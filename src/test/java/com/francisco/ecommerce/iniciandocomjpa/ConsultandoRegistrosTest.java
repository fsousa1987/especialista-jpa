package com.francisco.ecommerce.iniciandocomjpa;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConsultandoRegistrosTest extends EntityManagerTest {

  @Test
  public void buscarPorIdentificador() {
    Produto produto = entityManager.find(Produto.class, 1);
//    Produto produto = entityManager.getReference(Produto.class, 1);

    Assertions.assertNotNull(produto);
    Assertions.assertEquals("Kindle", produto.getNome());
  }

  @Test
  public void atualizandoAReferencia() {
    Produto produto = entityManager.find(Produto.class, 1);
    produto.setNome("Microfone Samson");

    entityManager.refresh(produto);

    Assertions.assertEquals("Kindle", produto.getNome());
  }
}
