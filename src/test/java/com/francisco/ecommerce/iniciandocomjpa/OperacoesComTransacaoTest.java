package com.francisco.ecommerce.iniciandocomjpa;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Produto;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OperacoesComTransacaoTest extends EntityManagerTest {

  @Test
  public void atualizarObjeto() {
    Produto produto = new Produto();

    produto.setId(1);
    produto.setNome("Kindle Paperwhite");
    produto.setDescricao("Conheça o novo Kindle.");
    produto.setPreco(new BigDecimal(599));

    entityManager.getTransaction().begin();
    entityManager.merge(produto);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
    Assertions.assertNotNull(produtoVerificacao);
    Assertions.assertEquals("Kindle Paperwhite", produtoVerificacao.getNome());
  }

  @Test
  public void removerObjeto() {
    Produto produto = entityManager.find(Produto.class, 3);

    entityManager.getTransaction().begin();
    entityManager.remove(produto);
    entityManager.getTransaction().commit();

//    entityManager.clear(); Não é necessário na asserção para operação de remoção.

    Produto produtoVerificacao = entityManager.find(Produto.class, 3);
    Assertions.assertNull(produtoVerificacao);
  }

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
