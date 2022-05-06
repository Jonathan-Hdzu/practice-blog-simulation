package com.example.system_blog_springboot.controller;

import com.example.system_blog_springboot.dto.PublicationsDTO.CommentsDTO;
import com.example.system_blog_springboot.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentsService commentsService;

    @PostMapping("/publications/{publicationId}/comments")
    public ResponseEntity<CommentsDTO> saveComment(@PathVariable(value = "publicationId")long publicationId,
                                                   @RequestBody CommentsDTO commentsDTO){
        return new ResponseEntity<>(commentsService.createComment(publicationId, commentsDTO), HttpStatus.CREATED);

    }

    @GetMapping("/publications/{publicationId}/comments")
    public List<CommentsDTO> listCommentsByPublicationId
}
