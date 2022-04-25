package com.example.system_blog_springboot.controller;

import com.example.system_blog_springboot.dto.PublicationsDTO.PublicationDTO;
import com.example.system_blog_springboot.dto.PublicationsDTO.PublicationResponse;
import com.example.system_blog_springboot.modelEntities.Publications;
import com.example.system_blog_springboot.utilities.AppConstants;
import com.example.system_blog_springboot.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
/*
 * Def 1: @RequestMapping es una anotación por excelencia en el marco de Spring que nos
 * permite mapear solicitudes HTTP con métodos que deseamos ejecutar.
 *
 * Def 2: Anotación que se encarga de relacionar un método con una petición http. El uso
 * de anotaciones no implica que unicamente tengamos un único controlador sino que nos permite agrupar
 * un conjunto de urls que esten asociadas a nivel de negocio en un controlador especifico.
 * */
@RequestMapping("/api/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @GetMapping
    public PublicationResponse listPublications(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.NO_PAGE_DEFAULT , required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.SIZE_PAGE_DEFAULT, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_DEFAULT, required = false)String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_DEFAULT, required = false)String sortDir) {
        return publicationService.getAllPublications(pageNumber, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> getPublicationsByID(@PathVariable(name = "id")long id){
        return ResponseEntity.ok(publicationService.getPublicationsByID(id));
    }

    @PostMapping
    //El cuerpo de la petición contendrá un JSon con la publicación
    public ResponseEntity<PublicationDTO> savePublication(@RequestBody PublicationDTO publicationDTO){
        return new ResponseEntity<PublicationDTO>(publicationService.createPublication(publicationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationDTO> updatePublication(@RequestBody PublicationDTO publicationDTO, @PathVariable(name = "id")long id){
        //Los nuevos datos/cuerpo del ID de la publicación que vamos a actualizar
        PublicationDTO publicationResponse = publicationService.updatePublication(publicationDTO, id);
        return new ResponseEntity<>(publicationResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublication(@PathVariable(name = "id")long id){
        publicationService.deletePublication(id);
        return new ResponseEntity<>("Publicación eliminada con éxito",HttpStatus.OK);
    }
}
