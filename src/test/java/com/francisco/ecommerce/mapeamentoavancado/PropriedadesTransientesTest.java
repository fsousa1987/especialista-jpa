package com.francisco.ecommerce.mapeamentoavancado;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PropriedadesTransientesTest extends EntityManagerTest {

  @Test
  public void validarPrimeiroNome() {
    Cliente cliente = entityManager.find(Cliente.class, 1);

    Assertions.assertEquals("Fernando", cliente.getPrimeiroNome());
  }
}
