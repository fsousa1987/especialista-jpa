package com.francisco.ecommerce.conhecendoentitymanager;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Produto;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class ContextoDePersistenciaTest extends EntityManagerTest {

  @Test
  public void usarContextoPersistencia() {
    entityManager.getTransaction().begin();

    Produto produto = entityManager.find(Produto.class, 1);
    produto.setPreco(new BigDecimal("100.0"));

    Produto produto2 = new Produto();
    produto2.setNome("Caneca para café");
    produto2.setPreco(new BigDecimal("10.0"));
    produto2.setDescricao("Boa caneca para café");
    entityManager.persist(produto2);

    Produto produto3 = new Produto();
    produto3.setNome("Caneca para chá");
    produto3.setPreco(new BigDecimal("10.0"));
    produto3.setDescricao("Boa caneca para chá");
    produto3 = entityManager.merge(produto3);

    entityManager.flush();

    produto3.setDescricao("Alterar descrição");

    entityManager.getTransaction().commit();
  }
}
