package com.francisco.ecommerce.jpql;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Pedido;
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
}
