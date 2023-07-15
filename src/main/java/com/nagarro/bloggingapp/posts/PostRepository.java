package com.nagarro.bloggingapp.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

       Page<PostEntity>findByUserId(Long userId, Pageable pageable);

       Page<PostEntity> findByCategoryId(Long categoryId, Pageable pageable);

       Page<PostEntity> findByUserIdAndCategoryId(Long userId, Long categoryId, Pageable pageable);
       
       Page<PostEntity> findByTitleContainingIgnoreCase(String search, Pageable pageable);
}
