package com.francisco.ecommerce.mapeamentoavancado;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Cliente;
import com.francisco.ecommerce.model.SexoCliente;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SecondaryTableTest extends EntityManagerTest {

  @Test
  public void salvarCliente() {
    Cliente cliente = new Cliente();
    cliente.setNome("Carlos Finotti");
    cliente.setSexo(SexoCliente.MASCULINO);
    cliente.setDataNascimento(LocalDate.of(1990, 1, 1));

    entityManager.getTransaction().begin();
    entityManager.persist(cliente);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
    Assertions.assertNotNull(clienteVerificacao.getSexo());
  }
}
