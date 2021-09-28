package com.francisco.ecommerce.iniciandocomjpa;

import com.francisco.ecommerce.model.Produto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConsultandoRegistrosTest {

  private static EntityManagerFactory entityManagerFactory;

  private EntityManager entityManager;

  @BeforeAll
  public static void setUpBeforeClass() {
    entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
  }

  @AfterAll
  public static void tearDownAfterClass() {
    entityManagerFactory.close();
  }

  @BeforeEach
  public void setUp() {
    entityManager = entityManagerFactory.createEntityManager();
  }

  @AfterEach
  public void tearDown() {
    entityManager.close();
  }

  @Test
  public void buscarPorIdentificador() {
    Produto produto = entityManager.find(Produto.class, 1);
//    Produto produto = entityManager.getReference(Produto.class, 1);

    Assertions.assertNotNull(produto);
    Assertions.assertEquals("Kindle", produto.getNome());
  }

  @Test
  public void atualizandoAReferencia() {
    Produto produto = entityManager.find(Produto.class, 1);
    produto.setNome("Microfone Samson");

    entityManager.refresh(produto);

    Assertions.assertEquals("Kindle", produto.getNome());
  }
}
