package com.nagarro.bloggingapp.posts;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.nagarro.bloggingapp.posts.dtos.PostRequestDto;
import com.nagarro.bloggingapp.posts.dtos.PostResponseDto;
import com.nagarro.bloggingapp.posts.dtos.PostWithPageDto;

public interface PostService {

    PostResponseDto createPost(PostRequestDto createPost, Long userId, Long categoryId);

    PostResponseDto getPostById(Long id);

    PostWithPageDto getAllPosts(
            int page, int size, String sort, String direction);

    PostWithPageDto getPostByUser(
            Long userId, int page, int size, String sort, String direction);

    PostWithPageDto getPostByCategory(
            Long categoryId, int page, int size, String sort, String direction);

    PostWithPageDto getPostByUserAndCategory(
            Long userId, Long categoryId, int page, int size, String sort, String direction);

    PostResponseDto updatePost(PostRequestDto updatePost, Long id);

    void deletePost(Long id);

    PostWithPageDto searchPost(String search, int page, int size, String sort, String direction);

    String uploadImage(String path, MultipartFile file) throws Exception;
    
    InputStream getImage(String path, String fileName) throws Exception;
}
