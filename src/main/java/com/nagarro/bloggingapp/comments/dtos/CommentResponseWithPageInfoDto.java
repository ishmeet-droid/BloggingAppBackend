package com.nagarro.bloggingapp.comments.dtos;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentResponseWithPageInfoDto {

    List<CommentResponseDto> commentResponse;
    
    int pageNumber;

    int totalPages;

    long totalElements;

    int pageSize;

    boolean isFirst;

    boolean isLast;

}
