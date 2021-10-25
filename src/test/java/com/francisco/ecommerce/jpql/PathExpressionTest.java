package com.francisco.ecommerce.jpql;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Pedido;
import java.util.List;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PathExpressionTest extends EntityManagerTest {

  @SuppressWarnings("CommentedOutCode")
  @Test
  public void buscarPedidosComProdutoEspecifico() {
    String jpql = "select p from Pedido p join p.itens i where i.id.produtoId = 1";
//        String jpql = "select p from Pedido p join p.itens i where i.produto.id = 1";
//        String jpql = "select p from Pedido p join p.itens i join i.produto pro where pro.id = 1";

    TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

    List<Pedido> lista = typedQuery.getResultList();
    Assertions.assertEquals(2, lista.size());
  }

  @Test
  public void usarPathExpressions() {
    String jpql = "select p.cliente.nome from Pedido p";

    TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

    List<Object[]> lista = typedQuery.getResultList();
    Assertions.assertFalse(lista.isEmpty());
  }
}
