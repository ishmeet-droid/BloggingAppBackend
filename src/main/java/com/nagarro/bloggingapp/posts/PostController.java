package com.nagarro.bloggingapp.posts;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.bloggingapp.common.ApiResponse;
import com.nagarro.bloggingapp.posts.dtos.PostRequestDto;
import com.nagarro.bloggingapp.posts.dtos.PostResponseDto;
import com.nagarro.bloggingapp.posts.dtos.PostWithPageDto;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("")
public class PostController {

    private PostServiceImpl postService;

    @Value("${project.image}")
    private String path;

    PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping("/Users/{userId}/Category/{categoryId}/Posts")
    public ResponseEntity<PostResponseDto> createPost(
            @RequestBody PostRequestDto createPost,
            @PathVariable Long userId,
            @PathVariable Long categoryId) {
        PostResponseDto postResponse = postService.createPost(createPost, userId, categoryId);

        return new ResponseEntity<PostResponseDto>(postResponse, HttpStatus.CREATED);
    }

    @GetMapping("/Posts/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        PostResponseDto postResponse = postService.getPostById(id);

        return new ResponseEntity<PostResponseDto>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/Posts")
    public ResponseEntity<PostWithPageDto> getAllPosts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        PostWithPageDto postResponse = postService
                .getAllPosts(page, size, sort, direction);

        // Check List is empty or not
        if (postResponse.toString().isEmpty()) {
            return new ResponseEntity<PostWithPageDto>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<PostWithPageDto>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/Users/{userId}/Posts")
    public ResponseEntity<PostWithPageDto> getPostsByUser(@PathVariable Long userId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        PostWithPageDto postResponse = postService
                .getPostByUser(userId, page, size, sort, direction);

        // Check List is empty or not
        if (postResponse.toString().isEmpty()) {
            return new ResponseEntity<PostWithPageDto>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<PostWithPageDto>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/Category/{categoryId}/Posts")
    public ResponseEntity<PostWithPageDto> getPostByCategory(
            @PathVariable Long categoryId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        PostWithPageDto postResponse = postService
                .getPostByCategory(categoryId, page, size, sort, direction);

        // Check Response is empty or not
        if (postResponse.toString().isEmpty()) {
            return new ResponseEntity<PostWithPageDto>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<PostWithPageDto>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/Users/{userId}/Category/{categoryId}/Posts")
    public ResponseEntity<PostWithPageDto> getPostByUserAndCategory(
            @PathVariable Long userId,
            @PathVariable Long categoryId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        PostWithPageDto postResponse = postService
                .getPostByUserAndCategory(userId, categoryId, page, size, sort, direction);

        // Check Response is empty or not
        if (postResponse.toString().isEmpty()) {
            return new ResponseEntity<PostWithPageDto>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<PostWithPageDto>(postResponse, HttpStatus.OK);
    }

    @PutMapping("/Users/{userId}/Category/{categoryId}/Posts/{id}")
    public ResponseEntity<PostResponseDto> updatePost(
            @RequestBody PostRequestDto updatePost,
            @PathVariable Long userId,
            @PathVariable Long categoryId,
            @PathVariable Long id) {

        PostResponseDto postResponse = postService.updatePost(updatePost, id);

        return new ResponseEntity<PostResponseDto>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/Users/{userId}/Category/{categoryId}/Posts/{id}")
    public ResponseEntity<ApiResponse> deletePost(
            @PathVariable Long userId,
            @PathVariable Long categoryId,
            @PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(
                "Post Deleted Successfully",
                true), HttpStatus.OK);
    }

    /*
     * Later on userid can be used to search post after validation of user
     * 
     * @GetMapping("/Users/{userId}/Posts/Searchfor")
     */
    @GetMapping("Posts/SearchInTitle")
    public ResponseEntity<PostWithPageDto> searchPost(
            @RequestParam(value = "searchFor", defaultValue = "", required = false) String searchFor,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "sort", defaultValue = "id", required = false) String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        PostWithPageDto postResponse = postService
                .searchPost(searchFor, page, size, sort, direction);

        // Check Response is empty or not
        if (postResponse.toString().isEmpty()) {
            return new ResponseEntity<PostWithPageDto>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<PostWithPageDto>(postResponse, HttpStatus.OK);

    }

    // Image Upload
    @PostMapping("/Posts/{id}/Image")
    public ResponseEntity<PostResponseDto> uploadImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable Long id) throws Exception {
        String imageName = null;

        PostResponseDto postResponse = postService.getPostById(id);

        imageName = postService.uploadImage(path, image);

        PostRequestDto createPost = new PostRequestDto();

        createPost.setTitle(postResponse.getTitle());
        createPost.setContent(postResponse.getContent());
        createPost.setImageURI(imageName);

        postResponse = postService.updatePost(createPost, id);

        return new ResponseEntity<PostResponseDto>(postResponse, HttpStatus.OK);
    }

    // Code to display Image
    @GetMapping("/Posts/{id}/Image")
    public ResponseEntity<HttpServletResponse > getImage(@PathVariable Long id,
        HttpServletResponse response) throws Exception {

        PostResponseDto postResponse = postService.getPostById(id);
        String imageName = postResponse.getImageURI();
        
        InputStream resource = postService.getImage(path, imageName);

        StreamUtils.copy(resource, response.getOutputStream());
        
       
        return new ResponseEntity<HttpServletResponse>(response, HttpStatus.OK);
    }

    
}
