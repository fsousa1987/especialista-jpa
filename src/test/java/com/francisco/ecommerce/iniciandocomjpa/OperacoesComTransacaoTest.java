package com.francisco.ecommerce.iniciandocomjpa;

import com.francisco.ecommerce.EntityManagerTest;
import org.junit.jupiter.api.Test;

public class OperacoesComTransacaoTest extends EntityManagerTest {

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
