package com.francisco.ecommerce.operacoesemcascata;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Cliente;
import com.francisco.ecommerce.model.ItemPedido;
import com.francisco.ecommerce.model.ItemPedidoId;
import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.model.Produto;
import com.francisco.ecommerce.model.StatusPedido;
import java.util.List;
import org.junit.jupiter.api.Assertions;

public class CascadeTypeMergeTest extends EntityManagerTest {

//  @Test
  public void atualizarPedidoComItens() {
    Cliente cliente = entityManager.find(Cliente.class, 1);
    Produto produto = entityManager.find(Produto.class, 1);

    Pedido pedido = new Pedido();
    pedido.setId(1);
    pedido.setCliente(cliente);
    pedido.setStatus(StatusPedido.AGUARDANDO);

    ItemPedido itemPedido = new ItemPedido();
    itemPedido.setId(new ItemPedidoId());
    itemPedido.getId().setPedidoId(pedido.getId());
    itemPedido.getId().setProdutoId(produto.getId());
    itemPedido.setPedido(pedido);
    itemPedido.setProduto(produto);
    itemPedido.setQuantidade(3);
    itemPedido.setPrecoProduto(produto.getPreco());

    pedido.setItens(List.of(itemPedido)); // CascadeType.MERGE

    entityManager.getTransaction().begin();
    entityManager.merge(pedido);
    entityManager.getTransaction().commit();

    entityManager.clear();

    ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
    Assertions.assertEquals(3, (int) itemPedidoVerificacao.getQuantidade());
  }

//  @Test
  public void atualizarItemPedidoComPedido() {
    Cliente cliente = entityManager.find(Cliente.class, 1);
    Produto produto = entityManager.find(Produto.class, 1);

    Pedido pedido = new Pedido();
    pedido.setId(1);
    pedido.setCliente(cliente);
    pedido.setStatus(StatusPedido.PAGO);

    ItemPedido itemPedido = new ItemPedido();
    itemPedido.setId(new ItemPedidoId());
    itemPedido.getId().setPedidoId(pedido.getId());
    itemPedido.getId().setProdutoId(produto.getId());
    itemPedido.setPedido(pedido); // CascadeType.MERGE
    itemPedido.setProduto(produto);
    itemPedido.setQuantidade(5);
    itemPedido.setPrecoProduto(produto.getPreco());

    pedido.setItens(List.of(itemPedido));

    entityManager.getTransaction().begin();
    entityManager.merge(itemPedido);
    entityManager.getTransaction().commit();

    entityManager.clear();

    ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
    Assertions.assertEquals(StatusPedido.PAGO, itemPedidoVerificacao.getPedido().getStatus());
  }
}
