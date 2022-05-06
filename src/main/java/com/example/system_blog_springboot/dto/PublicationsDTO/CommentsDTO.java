package com.example.system_blog_springboot.dto.PublicationsDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentsDTO {

    private long id;
    private String name;
    private String email;
    private String contentBody;
}
