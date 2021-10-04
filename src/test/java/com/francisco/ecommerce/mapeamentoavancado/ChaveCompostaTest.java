package com.francisco.ecommerce.mapeamentoavancado;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Cliente;
import com.francisco.ecommerce.model.ItemPedido;
import com.francisco.ecommerce.model.ItemPedidoId;
import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.model.Produto;
import com.francisco.ecommerce.model.StatusPedido;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChaveCompostaTest extends EntityManagerTest {

  @Test
  public void salvarItem() {
    entityManager.getTransaction().begin();

    Cliente cliente = entityManager.find(Cliente.class, 1);
    Produto produto = entityManager.find(Produto.class, 1);

    Pedido pedido = new Pedido();
    pedido.setCliente(cliente);
    pedido.setDataCriacao(LocalDateTime.now());
    pedido.setStatus(StatusPedido.AGUARDANDO);
    pedido.setTotal(produto.getPreco());

    entityManager.persist(pedido);

    entityManager.flush();

    ItemPedido itemPedido = new ItemPedido();
//    itemPedido.setPedidoId(pedido.getId()); IdClass
//    itemPedido.setProdutoId(produto.getId()); // IdClass
    itemPedido.setId(new ItemPedidoId(pedido.getId(), produto.getId()));
    itemPedido.setPedido(pedido);
    itemPedido.setProduto(produto);
    itemPedido.setPrecoProduto(produto.getPreco());
    itemPedido.setQuantidade(1);

    entityManager.persist(itemPedido);

    entityManager.getTransaction().commit();

    entityManager.clear();

    Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
    Assertions.assertNotNull(pedidoVerificacao);
    Assertions.assertFalse(pedidoVerificacao.getItens().isEmpty());
  }

  @Test
  public void bucarItem() {
    ItemPedido itemPedido = entityManager.find(
        ItemPedido.class, new ItemPedidoId(1, 1));

    Assertions.assertNotNull(itemPedido);
  }
}
