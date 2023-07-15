package com.nagarro.bloggingapp.posts;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nagarro.bloggingapp.Users.UserRepository;
import com.nagarro.bloggingapp.categories.CategoryRepository;
import com.nagarro.bloggingapp.common.ResourceNotFound;
import com.nagarro.bloggingapp.posts.dtos.CreatePost;
import com.nagarro.bloggingapp.posts.dtos.PostResponse;
import com.nagarro.bloggingapp.posts.dtos.PostWithPage;

@Service
public class PostServiceImpl implements PostService {

        private PostRepository postRepository;
        private ModelMapper modelMapper;
        private UserRepository userRepository;
        private CategoryRepository categoryRepository;

        PostServiceImpl(
                        PostRepository postRepository,
                        ModelMapper modelMapper,
                        UserRepository userRepository,
                        CategoryRepository categoryRepository) {
                this.postRepository = postRepository;
                this.modelMapper = modelMapper;
                this.userRepository = userRepository;
                this.categoryRepository = categoryRepository;
        }

        @Override
        public PostResponse createPost(CreatePost createPost, Long userId, Long categoryId) {
                PostEntity postEntity = modelMapper.map(createPost, PostEntity.class);
                postEntity.setImageURI("Deafault.png");
                postEntity.setDate(new Date(System.currentTimeMillis()));
                postEntity.setUser(userRepository.findById(userId).orElseThrow(
                                () -> new ResourceNotFound("User", "userId", userId)));
                postEntity.setCategory(categoryRepository.findById(categoryId).orElseThrow(
                                () -> new ResourceNotFound("Category", "categoryId", categoryId)));

                PostEntity savedPost = postRepository.save(postEntity);

                return modelMapper.map(savedPost, PostResponse.class);
        }

        @Override
        public PostResponse getPostById(Long id) {
                PostEntity postEntity = postRepository.findById(id).orElseThrow(
                                () -> new ResourceNotFound("Post", "PostId", id));

                PostEntity savedPost = postRepository.save(postEntity);

                return modelMapper.map(savedPost, PostResponse.class);
        }

        @Override
        public PostWithPage getAllPosts(
                        int page, int size, String sort, String direction) {

                Sort sortby = getSort(sort, direction);

                Pageable pageable = PageRequest.of(page, size, sortby);

                Page<PostEntity> postEntity = postRepository.findAll(pageable);

                List<PostEntity> posts = postEntity.getContent();

                // List<PostEntity> posts = postRepository.findAll();
                // List<PostResponse> postResponse = new ArrayList<>();

                // List<PostResponse> postResponse = posts.stream().forEach(post ->{
                // postResponse.add(modelMapper.map(post, PostResponse.class));
                // });

                List<PostResponse> postResponse = posts.stream()
                                .map(post -> modelMapper.map(post, PostResponse.class))
                                .collect(Collectors.toList());

                PostWithPage postWithPageInfo = getPostWithPageInfo(
                                postResponse, postEntity);

                return postWithPageInfo;
        }

        @Override
        public PostWithPage getPostByUser(
                        Long userId, int page, int size, String sort, String direction) {

                Sort sortby = getSort(sort, direction);

                Pageable pageable = PageRequest.of(page, size, sortby);

                Page<PostEntity> postEntity = postRepository
                                .findByUserId(userId, pageable);

                List<PostEntity> posts = postEntity.getContent();

                // List<PostEntity> postEntity = postRepository.findByUserId(userId);

                List<PostResponse> postResponse = posts.stream()
                                .map(post -> modelMapper.map(post, PostResponse.class))
                                .collect(Collectors.toList());

                PostWithPage postWithPageInfo = getPostWithPageInfo(
                                postResponse, postEntity);

                return postWithPageInfo;
        }

        @Override
        public PostWithPage getPostByCategory(
                        Long categoryId, int page, int size, String sort, String direction) {

                Sort sortby = getSort(sort, direction);

                Pageable pageable = PageRequest.of(page, size, sortby);

                Page<PostEntity> postEntity = postRepository
                                .findByCategoryId(categoryId, pageable);

                List<PostEntity> posts = postEntity.getContent();

                // List<PostEntity> postEntity = postRepository.findByCategoryId(categoryId);

                List<PostResponse> postResponse = posts.stream()
                                .map(post -> modelMapper.map(post, PostResponse.class))
                                .collect(Collectors.toList());

                PostWithPage postWithPageInfo = getPostWithPageInfo(
                                postResponse, postEntity);

                return postWithPageInfo;
        }

        @Override
        public PostWithPage getPostByUserAndCategory(
                        Long userId, Long categoryId, int page, int size, String sort, String direction) {

                Sort sortby = getSort(sort, direction);

                Pageable pageable = PageRequest.of(page, size, sortby);

                Page<PostEntity> postEntity = postRepository
                                .findByUserIdAndCategoryId(userId, categoryId, pageable);

                List<PostEntity> posts = postEntity.getContent();

                // List<PostEntity> postEntity = postRepository
                // .findByUserIdAndCategoryId(userId, categoryId);

                List<PostResponse> postResponse = posts.stream()
                                .map(post -> modelMapper.map(post, PostResponse.class))
                                .collect(Collectors.toList());

                PostWithPage postWithPageInfo = getPostWithPageInfo(
                                postResponse, postEntity);

                return postWithPageInfo;
        }

        @Override
        public PostResponse updatePost(CreatePost updatePost, Long id) {
                PostEntity postEntity = postRepository.findById(id).orElseThrow(
                                () -> new ResourceNotFound("Post", "PostId", id));

                postEntity.setTitle(updatePost.getTitle());
                postEntity.setContent(updatePost.getContent());

                PostEntity savedPost = postRepository.save(postEntity);

                return modelMapper.map(savedPost, PostResponse.class);
        }

        @Override
        public void deletePost(Long id) {
                PostEntity postEntity = postRepository.findById(id).orElseThrow(
                                () -> new ResourceNotFound("Post", "PostId", id));

                postRepository.delete(postEntity);
        }

        @Override
        public PostWithPage searchPost(String search, int page, int size, String sort, String direction) {
                Sort sortby = getSort(sort, direction);

                Pageable pageable = PageRequest.of(page, size, sortby);

                Page<PostEntity> postEntity = postRepository
                                .findByTitleContainingIgnoreCase(search, pageable);

                List<PostEntity> posts = postEntity.getContent();

                List<PostResponse> postResponse = posts.stream()
                                .map(post -> modelMapper.map(post, PostResponse.class))
                                .collect(Collectors.toList());

                PostWithPage postWithPageInfo = getPostWithPageInfo(
                                postResponse, postEntity);
                
                return postWithPageInfo;
        }

        private PostWithPage getPostWithPageInfo(
                        List<PostResponse> postResponse,
                        Page<PostEntity> postEntity) {
                PostWithPage postWithPageInfo = new PostWithPage();
                postWithPageInfo.setPostResponse(postResponse);
                postWithPageInfo.setTotalElements(postEntity.getTotalElements());
                postWithPageInfo.setTotalPages(postEntity.getTotalPages());
                postWithPageInfo.setPageNumber(postEntity.getNumber());
                postWithPageInfo.setPageSize(postEntity.getSize());
                postWithPageInfo.setFirst(postEntity.isFirst());
                postWithPageInfo.setLast(postEntity.isLast());

                return postWithPageInfo;
        }

        private Sort getSort(String sort, String direction) {
                Sort sortby = null;
                if (direction.equalsIgnoreCase("asc")) {
                        sortby = Sort.by(sort).ascending();
                } else {
                        sortby = Sort.by(sort).descending();
                }
                return sortby;
        }

}
