package com.francisco.ecommerce.mapeamentobasico;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EstrategiaChavePrimariaTest extends EntityManagerTest {

  @Test
  public void testarEstrategiaChave() {
    Categoria categoria = new Categoria();
    categoria.setNome("Natação");

    entityManager.getTransaction().begin();
    entityManager.persist(categoria);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
    Assertions.assertNotNull(categoriaVerificacao);
  }
}
