package com.francisco.ecommerce.jpql;

import com.francisco.ecommerce.EntityManagerTest;
import java.util.List;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PathExpressionTest extends EntityManagerTest {

  @Test
  public void usarPathExpressions() {
    String jpql = "select p.cliente.nome from Pedido p";

    TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

    List<Object[]> lista = typedQuery.getResultList();
    Assertions.assertFalse(lista.isEmpty());
  }
}
