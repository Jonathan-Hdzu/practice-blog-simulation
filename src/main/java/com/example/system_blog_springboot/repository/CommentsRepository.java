package com.example.system_blog_springboot.repository;

import com.example.system_blog_springboot.modelEntities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    public List<Comments> findByPublicationsId(long publicationId);

}
