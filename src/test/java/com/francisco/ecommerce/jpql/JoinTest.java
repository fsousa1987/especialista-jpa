package com.francisco.ecommerce.jpql;

import com.francisco.ecommerce.EntityManagerTest;
import java.util.List;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JoinTest extends EntityManagerTest {

  @Test
  public void fazerLeftJoin() {
    String jpql = "select p from Pedido p left join p.pagamento pag on pag.status = 'PROCESSANDO'";

    TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

    List<Object[]> lista = typedQuery.getResultList();
    Assertions.assertFalse(lista.isEmpty());
  }

  @Test
  public void fazerJoin() {
    String jpql = "select p from Pedido p join p.pagamento pag";

    TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

    List<Object[]> lista = typedQuery.getResultList();
    Assertions.assertFalse(lista.isEmpty());
  }
}
