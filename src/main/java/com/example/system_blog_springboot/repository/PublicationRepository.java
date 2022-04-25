package com.example.system_blog_springboot.repository;

import com.example.system_blog_springboot.modelEntities.Publications;
import org.springframework.data.jpa.repository.JpaRepository;

/*
* Jpa también extiende de PagingAndSorting Repository, por ende no habría problema al implementar paginación
* */
public interface PublicationRepository extends JpaRepository<Publications, Long> {
}
