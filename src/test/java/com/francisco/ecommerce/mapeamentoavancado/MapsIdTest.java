package com.francisco.ecommerce.mapeamentoavancado;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Cliente;
import com.francisco.ecommerce.model.ItemPedido;
import com.francisco.ecommerce.model.ItemPedidoId;
import com.francisco.ecommerce.model.NotaFiscal;
import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.model.Produto;
import com.francisco.ecommerce.model.StatusPedido;
import java.time.LocalDateTime;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapsIdTest extends EntityManagerTest {

  @Test
  public void inserirPagamento() {
    Pedido pedido = entityManager.find(Pedido.class, 1);

    NotaFiscal notaFiscal = new NotaFiscal();
    notaFiscal.setPedido(pedido);
    notaFiscal.setDataEmissao(new Date());
    notaFiscal.setXml("<xml/>".getBytes());

    entityManager.getTransaction().begin();
    entityManager.persist(notaFiscal);
    entityManager.getTransaction().commit();

    entityManager.clear();

    NotaFiscal notaFiscalVarificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
    Assertions.assertNotNull(notaFiscalVarificacao);
    Assertions.assertEquals(pedido.getId(), notaFiscalVarificacao.getId());
  }

  @Test
  public void inserirItemPedido() {
    Cliente cliente = entityManager.find(Cliente.class, 1);
    Produto produto = entityManager.find(Produto.class, 1);

    Pedido pedido = new Pedido();
    pedido.setCliente(cliente);
    pedido.setDataCriacao(LocalDateTime.now());
    pedido.setStatus(StatusPedido.AGUARDANDO);
    pedido.setTotal(produto.getPreco());

    ItemPedido itemPedido = new ItemPedido();
    itemPedido.setId(new ItemPedidoId());
    itemPedido.setPedido(pedido);
    itemPedido.setProduto(produto);
    itemPedido.setPrecoProduto(produto.getPreco());
    itemPedido.setQuantidade(1);

    entityManager.getTransaction().begin();
    entityManager.persist(pedido);
    entityManager.persist(itemPedido);
    entityManager.getTransaction().commit();

    entityManager.clear();

    ItemPedido itemPedidoVerificacao = entityManager.find(
        ItemPedido.class, new ItemPedidoId(pedido.getId(), produto.getId()));
    Assertions.assertNotNull(itemPedidoVerificacao);
  }
}
