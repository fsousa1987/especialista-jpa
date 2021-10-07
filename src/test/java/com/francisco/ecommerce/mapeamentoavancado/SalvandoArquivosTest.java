package com.francisco.ecommerce.mapeamentoavancado;

import com.francisco.ecommerce.EntityManagerTest;
import com.francisco.ecommerce.model.NotaFiscal;
import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.model.Produto;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("CommentedOutCode")
public class SalvandoArquivosTest extends EntityManagerTest {

  @Test
  public void salvarFotoProduto(){
    entityManager.getTransaction().begin();
    Produto produto = entityManager.find(Produto.class, 1);
    produto.setFoto(carregarFoto());
    entityManager.getTransaction().commit();

    entityManager.clear();

    Produto produtoVerificacao = entityManager.find(Produto.class, 1);
    Assertions.assertNotNull(produtoVerificacao.getFoto());
    Assertions.assertTrue(produtoVerificacao.getFoto().length > 0);
  }

  @Test
  public void salvarXmlNota() {
    Pedido pedido = entityManager.find(Pedido.class, 1);

    NotaFiscal notaFiscal = new NotaFiscal();
    notaFiscal.setPedido(pedido);
    notaFiscal.setDataEmissao(new Date());
    notaFiscal.setXml(carregarNotaFiscal());

    entityManager.getTransaction().begin();
    entityManager.persist(notaFiscal);
    entityManager.getTransaction().commit();

    entityManager.clear();

    NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
    Assertions.assertNotNull(notaFiscalVerificacao.getXml());
    Assertions.assertTrue(notaFiscalVerificacao.getXml().length > 0);

        /*
        try {
            OutputStream out = new FileOutputStream(
                    Files.createFile(Paths.get(
                            System.getProperty("user.home") + "/xml.xml")).toFile());
            out.write(notaFiscalVerificacao.getXml());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
         */
  }

  private static byte[] carregarFoto() {
    return carregarArquivo();
  }

  private static byte[] carregarNotaFiscal() {
    try {
      return Objects.requireNonNull(SalvandoArquivosTest.class.getResourceAsStream(
          "/nota-fiscal.xml")).readAllBytes();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static byte[] carregarArquivo() {
    try {
      return Objects.requireNonNull(
          SalvandoArquivosTest.class.getResourceAsStream("/kindle.jpg")).readAllBytes();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
