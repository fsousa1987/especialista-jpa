package com.francisco.ecommerce.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
@Entity
public class Cliente {

  @Id
  private Integer id;

  private String nome;

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
