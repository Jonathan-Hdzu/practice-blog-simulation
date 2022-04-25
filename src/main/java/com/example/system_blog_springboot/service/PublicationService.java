package com.example.system_blog_springboot.service;

import com.example.system_blog_springboot.dto.PublicationsDTO.PublicationDTO;
import com.example.system_blog_springboot.dto.PublicationsDTO.PublicationResponse;


public interface PublicationService {
    PublicationDTO createPublication(PublicationDTO publicationDTO);
    public PublicationResponse getAllPublications(int pageNumber, int pageSize, String sortBy, String sortDir);
    public PublicationDTO getPublicationsByID(long id);
    public PublicationDTO updatePublication(PublicationDTO publicationDTO, long id);
    public void deletePublication(long id);

}
