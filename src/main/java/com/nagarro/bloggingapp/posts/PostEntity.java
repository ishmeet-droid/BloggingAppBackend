package com.nagarro.bloggingapp.posts;


import java.util.Date;

import com.nagarro.bloggingapp.Users.UserEntity;
import com.nagarro.bloggingapp.categories.CategoryEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "posts")
public class PostEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "date", nullable = false, length = 100)
    private Date date;
    
    @Lob
    @Column(name = "image_uri", nullable = true, columnDefinition = "BLOB")
    private String imageURI;

    @ManyToOne
    @JoinColumn(name = "category_id") 
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
