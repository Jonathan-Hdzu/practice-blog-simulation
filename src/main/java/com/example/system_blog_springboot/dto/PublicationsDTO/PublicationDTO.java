package com.example.system_blog_springboot.dto.PublicationsDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicationDTO {
    private Long id;
    private String title;
    private String description;
    private String content;
}
