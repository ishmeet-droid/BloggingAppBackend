package com.nagarro.bloggingapp.comments;

import java.util.List;

import com.nagarro.bloggingapp.comments.dtos.CommentRequestDto;
import com.nagarro.bloggingapp.comments.dtos.CommentResponseDto;
import com.nagarro.bloggingapp.comments.dtos.CommentResponseWithPageInfoDto;

public interface CommentService {

   CommentResponseDto addComment(CommentRequestDto commentRequest, 
           Long userId, Long postId);

    CommentResponseWithPageInfoDto getComments(int page, int size, Long postId);

    void deleteComment(Long commentId, Long postId);
  
 
    
}
