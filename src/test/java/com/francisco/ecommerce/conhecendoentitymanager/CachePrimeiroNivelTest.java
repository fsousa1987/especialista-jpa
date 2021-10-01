package com.francisco.ecommerce.conhecendoentitymanager;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Produto;
import org.junit.jupiter.api.Test;

@SuppressWarnings("CommentedOutCode")
public class CachePrimeiroNivelTest extends EntityManagerTest {

  @Test
  public void verificarCache() {
    Produto produto = entityManager.find(Produto.class, 1);
    System.out.println(produto.getNome());

    System.out.println("------------------------------");

//    entityManager.close();
//    entityManager = entityManagerFactory.createEntityManager();

    Produto produtoResgatado = entityManager.find(Produto.class, produto.getId());
    System.out.println(produtoResgatado.getNome());
  }
}
