package com.francisco.ecommerce.relacionamentos;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Categoria;
import com.francisco.ecommerce.model.Produto;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RelacionamentoManyToManyTest extends EntityManagerTest {

  @Test
  public void verificarRelacionamento() {
    Produto produto = entityManager.find(Produto.class, 1);
    Categoria categoria = entityManager.find(Categoria.class, 1);

    entityManager.getTransaction().begin();
//    categoria.setProdutos(Arrays.asList(produto));
    produto.setCategorias(List.of(categoria));
    entityManager.getTransaction().commit();

    entityManager.clear();

    Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
    Assertions.assertFalse(categoriaVerificacao.getProdutos().isEmpty());
  }
}
