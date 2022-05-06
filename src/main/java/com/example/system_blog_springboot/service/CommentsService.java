package com.example.system_blog_springboot.service;

import com.example.system_blog_springboot.dto.PublicationsDTO.CommentsDTO;

import java.util.List;

public interface CommentsService {

    public CommentsDTO createComment(long publicationId, CommentsDTO commentsDTO);
    public List<CommentsDTO> getCommentsByPublicationId(long publicationId);
}
