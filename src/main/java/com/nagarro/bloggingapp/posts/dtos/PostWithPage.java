package com.nagarro.bloggingapp.posts.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PostWithPage {

    List<PostResponse> postResponse;

    int pageNumber;

    int totalPages;

    long totalElements;

    int pageSize;

    boolean isFirst;

    boolean isLast;

    


    
}
