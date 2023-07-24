package com.nagarro.bloggingapp.comments;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.bloggingapp.comments.dtos.CommentRequestDto;
import com.nagarro.bloggingapp.comments.dtos.CommentResponseDto;
import com.nagarro.bloggingapp.comments.dtos.CommentResponseWithPageInfoDto;

@RestController
@RequestMapping("/")
public class CommentController {

    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/user/{userId}/post/{postId}/comments")
    public ResponseEntity<CommentResponseDto> addComment(
            @RequestBody CommentRequestDto commentRequestDto,
            @PathVariable Long userId,
            @PathVariable Long postId) {

        CommentResponseDto commentResponseDto = commentService
                .addComment(commentRequestDto, userId, postId);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/Post/{postId}/comments")
    public ResponseEntity<CommentResponseWithPageInfoDto> getAllComments(@PathVariable Long postId,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size) {

        CommentResponseWithPageInfoDto comments = commentService
                .getComments(page, size, postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
