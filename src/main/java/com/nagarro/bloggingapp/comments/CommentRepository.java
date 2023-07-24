package com.nagarro.bloggingapp.comments;




import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
    
    List<CommentEntity> findByPostId(Long postId);
    Page<CommentEntity> findByPostId(Long postId,Pageable pageable);
   
}
