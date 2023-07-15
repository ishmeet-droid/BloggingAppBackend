package com.nagarro.bloggingapp.posts;

import java.util.Date;
import java.util.List;

import javax.xml.stream.events.Comment;

import com.nagarro.bloggingapp.categories.CategoryEntity;
import com.nagarro.bloggingapp.comments.CommentEntity;
import com.nagarro.bloggingapp.user.UserEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    /*
     * For using Blob features we need to update image return type to byte[] and
     * add @lob notation
     */
    @Column(name = "image_uri", nullable = true)
    private String imageURI;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comment;
}
