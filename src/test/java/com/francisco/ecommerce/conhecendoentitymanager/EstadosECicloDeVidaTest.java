package com.francisco.ecommerce.conhecendoentitymanager;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Categoria;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class EstadosECicloDeVidaTest extends EntityManagerTest {

  @Test
  public void analisarEstados() {
    Categoria categoriaNovo = new Categoria();
    categoriaNovo.setNome("Eletrônicos");

    Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo);

    Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1);

    entityManager.remove(categoriaGerenciada);
    entityManager.persist(categoriaGerenciada);

    entityManager.detach(categoriaGerenciada);
  }
}
