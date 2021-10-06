package com.francisco.ecommerce.mapeamentoavancado;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Atributo;
import com.francisco.ecommerce.model.Produto;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ElementCollectionTest extends EntityManagerTest {

  @Test
  public void aplicarTags() {
    entityManager.getTransaction().begin();

    Produto produto = entityManager.find(Produto.class, 1);
    produto.setTags(Arrays.asList("ebook", "livro-digital"));

    entityManager.getTransaction().commit();

    entityManager.clear();

    Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
    Assertions.assertFalse(produtoVerificacao.getTags().isEmpty());
  }

  @Test
  public void aplicarAtributos() {
    entityManager.getTransaction().begin();

    Produto produto = entityManager.find(Produto.class, 1);
    produto.setAtributos(List.of(new Atributo("tela", "320x600")));

    entityManager.getTransaction().commit();

    entityManager.clear();

    Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
    Assertions.assertFalse(produtoVerificacao.getAtributos().isEmpty());
  }
}
