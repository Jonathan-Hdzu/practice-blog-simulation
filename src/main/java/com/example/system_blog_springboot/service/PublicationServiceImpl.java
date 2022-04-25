package com.example.system_blog_springboot.service;

import com.example.system_blog_springboot.dto.PublicationsDTO.PublicationDTO;
import com.example.system_blog_springboot.dto.PublicationsDTO.PublicationResponse;
import com.example.system_blog_springboot.exceptions.ResourceNotFoundException;
import com.example.system_blog_springboot.modelEntities.Publications;
import com.example.system_blog_springboot.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService{

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public PublicationDTO createPublication(PublicationDTO publicationDTO) {
        Publications publication = mapEntity(publicationDTO);
        Publications newPublication = publicationRepository.save(publication);
        PublicationDTO publicationResponse = mapDTO(newPublication);

        return publicationResponse;
    }

    @Override
    public PublicationResponse getAllPublications(int pageNumber, int pageSize, String sortBy, String sortDir) {

        /*Documentación: https://www.baeldung.com/spring-data-jpa-pagination-sorting
        *                https://reflectoring.io/spring-boot-paging/  */
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable =  PageRequest.of(pageNumber, pageSize, sort);
        Page<Publications> publications = publicationRepository.findAll(pageable);
        List<Publications> listPublications = publications.getContent();
        /*La lista que estamos obteniendo de la base de datos la estamos añadiendo a un flujo stream la
         * cual estaremos mapeando, lo que indicamos que el objeto que estamos recibiendo por cada "publication"
         * la vamos a mapear a DTO y la mostraremos en una lista JSon */
        List<PublicationDTO> content = listPublications.stream().map(publication -> mapDTO(publication)).collect(Collectors.toList());
        PublicationResponse publicationResponse = new PublicationResponse();

        //Los objetos DTO mapeados se los pasa a la lista de contenido (content) en PublicationResponse

        publicationResponse.setContent(content);
        publicationResponse.setPageNo(publications.getNumber());
        publicationResponse.setPageSize(publications.getSize());
        publicationResponse.setTotalElements(publications.getTotalElements());
        publicationResponse.setTotalPages(publications.getTotalPages());
        publicationResponse.setLast(publications.isLast());

        return publicationResponse;
    }
    /*
     * Del repositorio voy a buscar por ID una publicación, si no la encuentra lanza
     * una clase que tiene como exception predeterminada NotFoundException y se le indica
     * el nombre del campo de la publicación, id y el valor ID
     * */
    @Override
    public PublicationDTO getPublicationsByID(long id) {
        Publications publications = publicationRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Publication","id",id));
        return mapDTO(publications);
    }

    @Override
    public PublicationDTO updatePublication(PublicationDTO publicationDTO, long id) {
        //Es lo mismo al anterior dado que tenemos que saber qué publicación actualizar
        Publications publications = publicationRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));

        //Establecemos lo que obtenemos del DTO
        publications.setTitle(publicationDTO.getTitle());
        publications.setDescription(publicationDTO.getDescription());
        publications.setContent(publicationDTO.getContent());

        //Acá solo se guarda en el objeto
        Publications updatePublication = publicationRepository.save(publications);

        return mapDTO(updatePublication);
    }

    @Override
    public void deletePublication(long id) {
        //Primeramente buscarla
        Publications publications = publicationRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
        publicationRepository.delete(publications);
    }

    //-----------------------Mapeando objetos------------------------------------
    //Convierte entidad a DTO
    private PublicationDTO mapDTO(Publications publications){
        PublicationDTO publicationDTO = new PublicationDTO();

        publicationDTO.setId(publications.getId());
        publicationDTO.setTitle(publications.getTitle());
        publicationDTO.setDescription(publications.getDescription());
        publicationDTO.setContent(publications.getContent());

        return publicationDTO;
    }

    //Convierte de DTO a entidad
    private Publications mapEntity(PublicationDTO publicationDTO){
        Publications publications = new Publications();

        publications.setTitle(publicationDTO.getTitle());
        publications.setDescription(publicationDTO.getDescription());
        publications.setContent(publicationDTO.getContent());

        return publications;
    }
}
