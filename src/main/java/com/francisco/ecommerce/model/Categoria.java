package com.francisco.ecommerce.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "categoria",
    uniqueConstraints = { @UniqueConstraint(name = "unq_nome", columnNames = { "nome" }) }
)
public class Categoria extends EntidadeBaseInteger {

  @Column(length = 100, nullable = false)
  private String nome;

  @ManyToOne
  @JoinColumn(
      name = "categoria_pai_id",
      foreignKey = @ForeignKey(name = "fk_categoria_categoria_pai")
  )
  private Categoria categoriaPai;

  @OneToMany(mappedBy = "categoriaPai")
  private List<Categoria> categorias;

  @ManyToMany(mappedBy = "categorias")
  private List<Produto> produtos;
}
