package com.nagarro.bloggingapp.Users;

import java.util.List;

import com.nagarro.bloggingapp.Users.dtos.CreateUser;
import com.nagarro.bloggingapp.Users.dtos.UserResponse;
import com.nagarro.bloggingapp.Users.dtos.UpdateAbtAndPass;




public interface UserService {
    
       public UserResponse createUser(CreateUser createUser);
       public UserResponse updateUser(CreateUser updateUser, Long id);
       public UserResponse updateUserPassAndAbout(UpdateAbtAndPass updateUser, Long id);
       public void deleteUser(Long id);
       public List<UserResponse> getUsers();
       public UserResponse getUserById(Long id);
    
}
