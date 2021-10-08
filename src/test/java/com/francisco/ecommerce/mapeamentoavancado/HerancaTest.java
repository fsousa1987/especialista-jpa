package com.francisco.ecommerce.mapeamentoavancado;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HerancaTest extends EntityManagerTest {

  @Test
  public void salvarCliente() {
    Cliente cliente = new Cliente();
    cliente.setNome("Fernanda Morais");

    entityManager.getTransaction().begin();
    entityManager.persist(cliente);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
    Assertions.assertNotNull(clienteVerificacao.getId());
  }
}
