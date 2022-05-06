package com.example.system_blog_springboot.service;

import com.example.system_blog_springboot.dto.PublicationsDTO.CommentsDTO;
import com.example.system_blog_springboot.exceptions.ResourceNotFoundException;
import com.example.system_blog_springboot.modelEntities.Comments;
import com.example.system_blog_springboot.modelEntities.Publications;
import com.example.system_blog_springboot.repository.CommentsRepository;
import com.example.system_blog_springboot.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService{

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public CommentsDTO createComment(long publicationId, CommentsDTO commentsDTO) {
        Comments comments = mapEntity(commentsDTO);
        Publications publications = publicationRepository
                .findById(publicationId).orElseThrow(() -> new ResourceNotFoundException("Publication", "id", publicationId));
        publicationRepository.delete(publications);

        comments.setPublications(publications);
        Comments newComment = commentsRepository.save(comments);
        return mapDto(newComment);
    }

    private CommentsDTO mapDto(Comments comments){
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setId(comments.getId());
        commentsDTO.setName(comments.getName());
        commentsDTO.setEmail(comments.getEmail());
        commentsDTO.setContentBody(comments.getContent());

        return commentsDTO;
    }

    private Comments mapEntity(CommentsDTO commentsDTO){
        Comments comments = new Comments();
        comments.setId(commentsDTO.getId());
        comments.setName(commentsDTO.getName());
        comments.setEmail(commentsDTO.getEmail());
        comments.setContent(commentsDTO.getContentBody());

        return comments;
    }

    @Override
    public List<CommentsDTO> getCommentsByPublicationId(long publicationId) {
        List<Comments> comments = commentsRepository.findByPublicationsId(publicationId);
        return comments.stream().map(comment -> mapDto(comment)).collect(Collectors.toList());
    }


}
