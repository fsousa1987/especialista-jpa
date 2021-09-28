package com.francisco.ecommerce.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    private Integer id;

    private String nome;

    private String descricao;

    private BigDecimal preco;
}
