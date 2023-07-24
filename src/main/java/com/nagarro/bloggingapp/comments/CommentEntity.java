package com.nagarro.bloggingapp.comments;

import java.util.Date;

import com.nagarro.bloggingapp.posts.PostEntity;
import com.nagarro.bloggingapp.user.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;
    
    @Column(nullable = false)
    private Date createdAt;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private PostEntity post;
}
