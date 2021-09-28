package com.francisco.ecommerce.iniciandocomjpa;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Produto;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OperacoesComTransacaoTest extends EntityManagerTest {

  @Test
  public void inserirOPrimeiroObjeto() {
    Produto produto = new Produto();

    produto.setId(2);
    produto.setNome("Câmera Canon");
    produto.setDescricao("A melhor definição para suas fotos.");
    produto.setPreco(new BigDecimal(5000));

    entityManager.getTransaction().begin();
    entityManager.persist(produto);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
    Assertions.assertNotNull(produtoVerificacao);
  }

  @SuppressWarnings("CommentedOutCode")
  @Test
  public void abrirEFecharATransacao() {
    entityManager.getTransaction().begin();

//    entityManager.persist(produto);
//    entityManager.merge(produto);
//    entityManager.remove(produto);

    entityManager.getTransaction().commit();
  }
}
