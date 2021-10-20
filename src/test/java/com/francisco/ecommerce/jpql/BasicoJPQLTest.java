package com.francisco.ecommerce.jpql;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Pedido;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasicoJPQLTest extends EntityManagerTest {

  @SuppressWarnings("CommentedOutCode")
  @Test
  public void buscarPorIdentificador() {
    // entityManager.find(Pedido.class, 1)

    TypedQuery<Pedido> typedQuery =
        entityManager.createQuery("select p from Pedido p where p.id = 1", Pedido.class);

    Pedido pedido = typedQuery.getSingleResult();
    Assertions.assertNotNull(pedido);

    // List<Pedido> lista = typedQuery.getResultList();
    // Assertions.assertFalse(lista.isEmpty());
  }

  @SuppressWarnings("CommentedOutCode")
  @Test
  public void mostrarDiferencaQueries() {
    String jpql = "select p from Pedido p where p.id = 1";

    TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
    Pedido pedido1 = typedQuery.getSingleResult();
    Assertions.assertNotNull(pedido1);

    Query query = entityManager.createQuery(jpql);
    Pedido pedido2 = (Pedido) query.getSingleResult();
    Assertions.assertNotNull(pedido2);

    // List<Pedido> lista = query.getResultList();
    // Assertions.assertFalse(lista.isEmpty());
  }
}
