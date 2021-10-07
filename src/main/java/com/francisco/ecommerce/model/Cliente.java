package com.francisco.ecommerce.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
@SecondaryTable(
    name = "cliente_detalhe",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id")
)
@Entity
@Table(name = "cliente")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nome;

  @ElementCollection
  @CollectionTable(
      name = "cliente_contato",
      joinColumns = @JoinColumn(name = "cliente_id")
  )
  @MapKeyColumn(name = "tipo")
  @Column(name = "descricao")
  private Map<String, String> contatos;

  @Transient
  private String primeiroNome;

  @Column(table = "cliente_detalhe")
  @Enumerated(EnumType.STRING)
  private SexoCliente sexo;

  @Column(name = "data_nascimento", table = "cliente_detalhe")
  private LocalDate dataNascimento;

  @OneToMany(mappedBy = "cliente")
  private List<Pedido> pedidos;

  @PostLoad
  public void configurarPrimeiroNome() {
    if (nome != null && !nome.isBlank()) {
      int index = nome.indexOf(" ");
      if (index > -1) {
        primeiroNome = nome.substring(0, index);
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Cliente cliente = (Cliente) o;
    return Objects.equals(id, cliente.id);
  }

  @Override
  public int hashCode() {
    return 0;
  }
}
