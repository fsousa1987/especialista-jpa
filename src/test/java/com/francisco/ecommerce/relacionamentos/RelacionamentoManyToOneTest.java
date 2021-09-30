package com.francisco.ecommerce.relacionamentos;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Cliente;
import com.francisco.ecommerce.model.ItemPedido;
import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.model.Produto;
import com.francisco.ecommerce.model.StatusPedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RelacionamentoManyToOneTest extends EntityManagerTest {

  @Test
  public void verificarRelacionamento() {
    Cliente cliente = entityManager.find(Cliente.class, 1);

    Pedido pedido = new Pedido();
    pedido.setStatus(StatusPedido.AGUARDANDO);
    pedido.setDataPedido(LocalDateTime.now());
    pedido.setTotal(BigDecimal.TEN);

    pedido.setCliente(cliente);

    entityManager.getTransaction().begin();
    entityManager.persist(pedido);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
    Assertions.assertNotNull(pedidoVerificacao.getCliente());
  }

  @Test
  public void verificarRelacionamentoItemPedido() {
    Cliente cliente = entityManager.find(Cliente.class, 1);
    Produto produto = entityManager.find(Produto.class, 1);

    Pedido pedido = new Pedido();
    pedido.setStatus(StatusPedido.AGUARDANDO);
    pedido.setDataPedido(LocalDateTime.now());
    pedido.setTotal(BigDecimal.TEN);
    pedido.setCliente(cliente);

    ItemPedido itemPedido = new ItemPedido();
    itemPedido.setPrecoProduto(produto.getPreco());
    itemPedido.setQuantidade(1);
    itemPedido.setPedido(pedido);
    itemPedido.setProduto(produto);

    entityManager.getTransaction().begin();
    entityManager.persist(pedido);
    entityManager.persist(itemPedido);
    entityManager.getTransaction().commit();

    entityManager.clear();

    ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
    Assertions.assertNotNull(itemPedidoVerificacao.getPedido());
    Assertions.assertNotNull(itemPedidoVerificacao.getProduto());
  }
}