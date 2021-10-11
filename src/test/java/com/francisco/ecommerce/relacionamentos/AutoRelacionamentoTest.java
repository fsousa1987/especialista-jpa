package com.francisco.ecommerce.relacionamentos;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutoRelacionamentoTest extends EntityManagerTest {

  @Test
  public void verificarRelacionamento() {
    Categoria categoriaPai = new Categoria();
    categoriaPai.setNome("Futebol");

    Categoria categoria = new Categoria();
    categoria.setNome("uniformes");
    categoria.setCategoriaPai(categoriaPai);


    entityManager.getTransaction().begin();
    entityManager.persist(categoriaPai);
    entityManager.persist(categoria);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
    Assertions.assertNotNull(categoriaVerificacao.getCategoriaPai());

    Categoria categoriaPaiVerificacao = entityManager.find(Categoria.class, categoriaPai.getId());
    Assertions.assertFalse(categoriaPaiVerificacao.getCategorias().isEmpty());
  }
}
