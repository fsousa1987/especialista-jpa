package com.francisco.ecommerce.jpql;

import com.francisco.ecommerce.EntityManagerTest;
import java.util.List;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FuncoesStringsTest extends EntityManagerTest {

  @Test
  public void aplicarFuncao() {
    // concat, length, locate, substring, lower, upper, trim

    String jpql = "select c.nome, length(c.nome) from Categoria c " +
        " where substring(c.nome, 1, 1) = 'N'";

    TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

    List<Object[]> lista = typedQuery.getResultList();
    Assertions.assertFalse(lista.isEmpty());

    lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));
  }
}
