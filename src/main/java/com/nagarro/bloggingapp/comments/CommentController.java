package com.nagarro.bloggingapp.comments;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {
    
    @GetMapping("/all")
    public List<String> getAllComments(){
        List<String> comments = List.of("Comment 1", "Comment 2", "Comment 3");
        return comments;
    }
}
