package com.francisco.ecommerce.operacoesemcascata;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Cliente;
import com.francisco.ecommerce.model.ItemPedido;
import com.francisco.ecommerce.model.ItemPedidoId;
import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.model.Produto;
import com.francisco.ecommerce.model.SexoCliente;
import com.francisco.ecommerce.model.StatusPedido;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CascadeTypePersistTest extends EntityManagerTest {

//  @Test
  public void persistirPedidoComItens() {
    Cliente cliente = entityManager.find(Cliente.class, 1);
    Produto produto = entityManager.find(Produto.class, 1);

    Pedido pedido = new Pedido();
    pedido.setDataCriacao(LocalDateTime.now());
    pedido.setCliente(cliente);
    pedido.setTotal(produto.getPreco());
    pedido.setStatus(StatusPedido.AGUARDANDO);

    ItemPedido itemPedido = new ItemPedido();
    itemPedido.setId(new ItemPedidoId());
    itemPedido.setPedido(pedido);
    itemPedido.setProduto(produto);
    itemPedido.setQuantidade(1);
    itemPedido.setPrecoProduto(produto.getPreco());

    pedido.setItens(List.of(itemPedido)); // CascadeType.PERSIST

    entityManager.getTransaction().begin();
    entityManager.persist(pedido);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Assertions.assertNotNull(pedido);
    Assertions.assertFalse(pedido.getItens().isEmpty());
  }

  @Test
  public void persistirItemPedidoComPedido() {
    Cliente cliente = entityManager.find(Cliente.class, 1);
    Produto produto = entityManager.find(Produto.class, 1);

    Pedido pedido = new Pedido();
    pedido.setDataCriacao(LocalDateTime.now());
    pedido.setCliente(cliente);
    pedido.setTotal(produto.getPreco());
    pedido.setStatus(StatusPedido.AGUARDANDO);

    ItemPedido itemPedido = new ItemPedido();
    itemPedido.setId(new ItemPedidoId());
    itemPedido.setPedido(pedido);// Não é necessário CascadeType.PERSIST porque possui @MapsId.
    itemPedido.setProduto(produto);
    itemPedido.setQuantidade(1);
    itemPedido.setPrecoProduto(produto.getPreco());

    entityManager.getTransaction().begin();
    entityManager.persist(itemPedido);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Assertions.assertNotNull(pedido);
  }

//  @Test
  public void persistirPedidoComCliente() {
    Cliente cliente = new Cliente();
    cliente.setDataNascimento(LocalDate.of(1980, 1, 1));
    cliente.setSexo(SexoCliente.MASCULINO);
    cliente.setNome("José Carlos");
    cliente.setCpf("01234567890");

    Pedido pedido = new Pedido();
    pedido.setDataCriacao(LocalDateTime.now());
    pedido.setCliente(cliente); // CascadeType.PERSIST
    pedido.setTotal(BigDecimal.ZERO);
    pedido.setStatus(StatusPedido.AGUARDANDO);

    entityManager.getTransaction().begin();
    entityManager.persist(pedido);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Assertions.assertNotNull(cliente);
  }
}
