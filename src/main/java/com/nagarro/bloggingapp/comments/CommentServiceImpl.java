package com.nagarro.bloggingapp.comments;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nagarro.bloggingapp.comments.dtos.CommentRequestDto;
import com.nagarro.bloggingapp.comments.dtos.CommentResponseDto;
import com.nagarro.bloggingapp.comments.dtos.CommentResponseWithPageInfoDto;
import com.nagarro.bloggingapp.common.ResourceNotFound;
import com.nagarro.bloggingapp.posts.PostRepository;
import com.nagarro.bloggingapp.posts.dtos.PostResponseDto;
import com.nagarro.bloggingapp.user.UserRepository;
import com.nagarro.bloggingapp.user.dtos.UserResponseDto;

@Service
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;
    ModelMapper modelMapper;
    PostRepository postRepository;
    UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
            ModelMapper modelMapper,
            PostRepository postRepository,
            UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentResponseDto addComment(CommentRequestDto commentRequest,
            Long userId, Long postId) {

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment(commentRequest.getComment());
        commentEntity.setCreatedAt(new Date(System.currentTimeMillis()));
        commentEntity.setPost(postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post", "postid", postId)));
        commentEntity.setUser(userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFound("User", "userid", userId)));

        CommentEntity savedComment = commentRepository.save(commentEntity);
        // CommentResponseDto response = new CommentResponseDto();
        // response.setComment(savedComment.getComment());
        // response.setCreatedAt(savedComment.getCreatedAt());
        // response.setPost(modelMapper.map(savedComment.getPost(), PostResponseDto.class));
        // response.setUser(modelMapper.map(savedComment.getUser(), UserResponseDto.class));
         CommentResponseDto response = modelMapper.map(savedComment, CommentResponseDto.class);
        return response;
    }

    @Override
    public CommentResponseWithPageInfoDto getComments(int page, int size, Long postId) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CommentEntity> commentEntities = commentRepository
                .findByPostId(postId, pageable);

        List<CommentEntity> comments = commentEntities.getContent();

        CommentResponseWithPageInfoDto response = this.getCommentwithPageInfo(comments, commentEntities);

        return response;
    }

    @Override
    public void deleteComment(Long commentId, Long postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteComment'");
    }

    private CommentResponseWithPageInfoDto getCommentwithPageInfo(List<CommentEntity> comments,
            Page<CommentEntity> commentEntities) {
        List<CommentResponseDto> commentResponse = comments.stream()
                .map(comment -> modelMapper.map(comment, CommentResponseDto.class))
                .collect(Collectors.toList());

        CommentResponseWithPageInfoDto response = new CommentResponseWithPageInfoDto();
        response.setCommentResponse(commentResponse);
        response.setPageNumber(commentEntities.getNumber());
        response.setTotalPages(commentEntities.getTotalPages());
        response.setTotalElements(commentEntities.getTotalElements());
        response.setPageSize(commentEntities.getSize());
        response.setFirst(commentEntities.isFirst());
        response.setLast(commentEntities.isLast());
        return response;
    }
}
