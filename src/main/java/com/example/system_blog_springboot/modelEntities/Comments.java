package com.example.system_blog_springboot.modelEntities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Getter@Setter
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publications_id",nullable = false)
    private Publications publications;

}
