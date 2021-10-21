package com.francisco.ecommerce.jpql;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.dto.ProdutoDto;
import com.francisco.ecommerce.model.Cliente;
import com.francisco.ecommerce.model.Pedido;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasicoJPQLTest extends EntityManagerTest {

  @Test
  public void projetarNoDto() {
    String jpql = "select new com.francisco.ecommerce.dto.ProdutoDto(id, nome) from Produto";

    TypedQuery<ProdutoDto> typedQuery = entityManager.createQuery(jpql, ProdutoDto.class);
    List<ProdutoDto> lista = typedQuery.getResultList();
    Assertions.assertFalse(lista.isEmpty());

    lista.forEach(
        produtoDto -> System.out.println(produtoDto.getId() + ", " + produtoDto.getNome()));
  }

  @Test
  public void projetarOResultado() {
    String jpql = "select id, nome from Produto";

    TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
    List<Object[]> lista = typedQuery.getResultList();

    Assertions.assertEquals(2, lista.get(0).length);

    lista.forEach(array -> System.out.println(array[0] + ", " + array[1]));
  }

  @Test
  public void selecionarUmAtributoParaRetorno() {
    String jpql = "select p.nome from Produto p";

    TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);
    List<String> lista = typedQuery.getResultList();
    Assertions.assertEquals("Câmera GoPro Hero 7", lista.get(0));

    String jpqlCliente = "select p.cliente from Pedido p";
    TypedQuery<Cliente> typedQueryCliente = entityManager.createQuery(jpqlCliente, Cliente.class);
    List<Cliente> listaClientes = typedQueryCliente.getResultList();
    Assertions.assertEquals(Cliente.class, listaClientes.get(0).getClass());
  }

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
