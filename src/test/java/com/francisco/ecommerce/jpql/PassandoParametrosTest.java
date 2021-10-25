package com.francisco.ecommerce.jpql;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.NotaFiscal;
import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.model.StatusPagamento;
import java.util.Date;
import java.util.List;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PassandoParametrosTest extends EntityManagerTest {

  @Test
  public void passarParametroDate() {
    String jpql = "select nf from NotaFiscal nf where nf.dataEmissao <= ?1";

    TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(jpql, NotaFiscal.class);
    typedQuery.setParameter(1, new Date(), TemporalType.TIMESTAMP);

    List<NotaFiscal> lista = typedQuery.getResultList();
    Assertions.assertEquals(1, lista.size());
  }

  @Test
  public void passarParametro() {
    String jpql = "select p from Pedido p join p.pagamento pag " +
        " where p.id = :pedidoId and pag.status = ?1";

    TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
    typedQuery.setParameter("pedidoId", 2);
    typedQuery.setParameter(1, StatusPagamento.PROCESSANDO);

    List<Pedido> lista = typedQuery.getResultList();
    Assertions.assertEquals(1, lista.size());
  }
}
