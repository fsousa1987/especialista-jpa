package com.francisco.ecommerce.jpql;

import com.francisco.ecommerce.EntityManagerTest;
import java.util.List;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpressoesCondicionaisTest extends EntityManagerTest {

  @Test
  public void usarExpressaoCondicionalLike() {
    String jpql = "select c from Cliente c where c.nome like concat('%', :nome, '%')";

    TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
    typedQuery.setParameter("nome", "a");

    List<Object[]> lista = typedQuery.getResultList();
    Assertions.assertFalse(lista.isEmpty());
  }
}
