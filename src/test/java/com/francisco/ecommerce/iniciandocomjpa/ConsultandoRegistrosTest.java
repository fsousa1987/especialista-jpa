package com.francisco.ecommerce.iniciandocomjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

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
}
