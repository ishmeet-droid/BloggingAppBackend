package com.nagarro.bloggingapp.posts;

import com.nagarro.bloggingapp.posts.dtos.CreatePost;
import com.nagarro.bloggingapp.posts.dtos.PostResponse;
import com.nagarro.bloggingapp.posts.dtos.PostWithPage;

public interface PostService {

    PostResponse createPost(CreatePost createPost, Long userId, Long categoryId);

    PostResponse getPostById(Long id);

    PostWithPage getAllPosts(
            int page, int size, String sort, String direction);

    PostWithPage getPostByUser(
            Long userId, int page, int size, String sort, String direction);

    PostWithPage getPostByCategory(
            Long categoryId, int page, int size, String sort, String direction);

    PostWithPage getPostByUserAndCategory(
            Long userId, Long categoryId, int page, int size, String sort, String direction);

    PostResponse updatePost(CreatePost updatePost, Long id);

    void deletePost(Long id);

    PostWithPage searchPost(String search, int page, int size, String sort, String direction);



}
