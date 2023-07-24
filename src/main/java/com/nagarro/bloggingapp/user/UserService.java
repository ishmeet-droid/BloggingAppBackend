package com.nagarro.bloggingapp.user;

import java.util.List;

import com.nagarro.bloggingapp.user.dtos.UserRequestDto;
import com.nagarro.bloggingapp.user.dtos.UpdateAbtAndPassDto;
import com.nagarro.bloggingapp.user.dtos.UserResponseDto;




public interface UserService {
    
       public UserResponseDto createUser(UserRequestDto createUser);
       public UserResponseDto updateUser(UserRequestDto updateUser, Long id);
       public UserResponseDto updateUserPassAndAbout(UpdateAbtAndPassDto updateUser, Long id);
       public void deleteUser(Long id);
       public List<UserResponseDto> getUsers();
       public UserResponseDto getUserById(Long id);
    
}
