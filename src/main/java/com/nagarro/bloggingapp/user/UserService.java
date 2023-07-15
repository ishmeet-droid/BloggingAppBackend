package com.nagarro.bloggingapp.user;

import java.util.List;

import com.nagarro.bloggingapp.user.dtos.CreateUser;
import com.nagarro.bloggingapp.user.dtos.UpdateAbtAndPass;
import com.nagarro.bloggingapp.user.dtos.UserResponse;




public interface UserService {
    
       public UserResponse createUser(CreateUser createUser);
       public UserResponse updateUser(CreateUser updateUser, Long id);
       public UserResponse updateUserPassAndAbout(UpdateAbtAndPass updateUser, Long id);
       public void deleteUser(Long id);
       public List<UserResponse> getUsers();
       public UserResponse getUserById(Long id);
    
}
