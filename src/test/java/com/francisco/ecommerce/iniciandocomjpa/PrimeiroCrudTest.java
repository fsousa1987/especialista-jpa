package com.francisco.ecommerce.iniciandocomjpa;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

  @Test
  public void inserirRegistro() {
    Cliente cliente = new Cliente();

//    cliente.setId(3);
    cliente.setNome("José Lucas");

    entityManager.getTransaction().begin();
    entityManager.persist(cliente);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
    Assertions.assertNotNull(clienteVerificacao);
  }

  @Test
  public void busarPorIdentificador() {
    Cliente cliente = entityManager.find(Cliente.class, 1);

    Assertions.assertNotNull(cliente);
  }

  @Test
  public void atualizarRegistro() {
    Cliente cliente = new Cliente();

//    cliente.setId(1);
    cliente.setNome("Fernando Medeiros Silva");

    entityManager.getTransaction().begin();
    Cliente clienteSalvo = entityManager.merge(cliente);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Cliente clienteVerificacao = entityManager.find(Cliente.class, clienteSalvo.getId());
    Assertions.assertEquals("Fernando Medeiros Silva", clienteVerificacao.getNome());
  }

  @Test
  public void removerRegistro() {
    Cliente cliente = entityManager.find(Cliente.class, 2);

    entityManager.getTransaction().begin();
    entityManager.remove(cliente);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
    Assertions.assertNull(clienteVerificacao);
  }
}
