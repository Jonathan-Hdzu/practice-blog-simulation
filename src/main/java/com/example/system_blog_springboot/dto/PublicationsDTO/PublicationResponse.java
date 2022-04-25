package com.example.system_blog_springboot.dto.PublicationsDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class PublicationResponse {

    private List<PublicationDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    public PublicationResponse() {
    }
}
