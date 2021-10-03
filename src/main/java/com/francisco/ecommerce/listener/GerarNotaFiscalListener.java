package com.francisco.ecommerce.listener;

import com.francisco.ecommerce.model.Pedido;
import com.francisco.ecommerce.service.NotaFiscalService;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class GerarNotaFiscalListener {

  private NotaFiscalService notaFiscalService = new NotaFiscalService();

  @PrePersist
  @PreUpdate
  public void gerar(Pedido pedido) {
    if (pedido.isPago() && pedido.getNotaFiscal() == null) {
      notaFiscalService.gerar(pedido);
    }
  }
}
