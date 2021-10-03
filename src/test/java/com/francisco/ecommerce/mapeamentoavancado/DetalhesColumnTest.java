package com.francisco.ecommerce.mapeamentoavancado;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Produto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DetalhesColumnTest extends EntityManagerTest {

  @Test
  public void impedirInsercaoDaColunaAtualizacao() {
    Produto produto = new Produto();
    produto.setNome("Teclado para smartphone");
    produto.setDescricao("O mais confortável");
    produto.setPreco(BigDecimal.ONE);
    produto.setDataCriacao(LocalDateTime.now());
    produto.setDataUltimaAtualizacao(LocalDateTime.now());

    entityManager.getTransaction().begin();
    entityManager.persist(produto);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
    Assertions.assertNotNull(produtoVerificacao.getDataCriacao());
    Assertions.assertNull(produtoVerificacao.getDataUltimaAtualizacao());
  }

  @Test
  public void impedirAtualizacaoDaColunaCriacao() {
    entityManager.getTransaction().begin();

    Produto produto = entityManager.find(Produto.class, 1);
    produto.setPreco(BigDecimal.TEN);
    produto.setDataCriacao(LocalDateTime.now());
    produto.setDataUltimaAtualizacao(LocalDateTime.now());

    entityManager.getTransaction().commit();

    entityManager.clear();

    Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
    Assertions.assertNotEquals(produto.getDataCriacao().truncatedTo(ChronoUnit.SECONDS),
        produtoVerificacao.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));
    Assertions.assertEquals(produto.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS),
        produtoVerificacao.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS));
  }
}
