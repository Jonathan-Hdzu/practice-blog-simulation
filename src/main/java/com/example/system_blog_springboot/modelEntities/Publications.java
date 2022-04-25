package com.example.system_blog_springboot.modelEntities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publications", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
@Getter@Setter
public class Publications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "content", nullable = false)
    private String content;

    /*<orphanRemoval> es un atributo específico del ORM que marca la entidad secundaria a
    eliminar cuando ya no se haga referencia a ella desde la entidad principal.

    >>Fuentes:
    https://www.nestoralmeida.com/cascade-en-jpa-hibernate/#:~:text=orphanRemoval%20es%20un%20atributo%20espec%C3%ADfico,ella%20desde%20la%20entidad%20principal.
    https://stackoverflow.com/questions/11938253/jpa-joincolumn-vs-mappedby
    https://www.arquitecturajava.com/jpa-orphan-removal-y-como-usarlo/
    https://www.arquitecturajava.com/jpa-onetomany/

    >>Información sobre el ORM:
    https://es.wikipedia.org/wiki/Asignaci%C3%B3n_objeto-relacional
    */

    /*
    * Este parámetro hace referencia a que la relación ya fue construida
    * por la otra clase “Comments” a traves  de su variable “publications” .*/
    @OneToMany(mappedBy = "publications",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private Set<Comments> comments = new HashSet<>();

}
