package com.codigo.reactive.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("usuarios")
@Getter
@Setter
public class Usuarios {
   @Id
   private Long id;
   private String name;
   private String email;
   private String state;
}
