package com.francisco.ecommerce.util;

import java.util.HashMap;
import java.util.Map;

public class ExecutarDDL {

  public static void main(String[] args) {
    Map<String, String> propriedades = new HashMap<>();

    propriedades.put("javax.persistence.schema-generation.database.action",
        "drop-and-create");

    ExportarDDL.instrucoesComumEntreExportarExecutar(propriedades);
  }
}
