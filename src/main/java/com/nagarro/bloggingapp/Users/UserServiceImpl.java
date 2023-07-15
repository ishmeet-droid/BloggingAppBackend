package com.nagarro.bloggingapp.Users;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.nagarro.bloggingapp.Users.dtos.CreateUser;
import com.nagarro.bloggingapp.Users.dtos.UserResponse;
import com.nagarro.bloggingapp.Users.dtos.UpdateAbtAndPass;
import com.nagarro.bloggingapp.common.ResourceNotFound;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponse createUser(CreateUser createUser) {

        UserEntity userEntity = modelMapper.map(createUser, UserEntity.class);
        UserEntity savedUser = userRepository.save(userEntity);

        return modelMapper.map(savedUser, UserResponse.class);
    }
    

    @Override
    public UserResponse updateUser(CreateUser updateUser, Long id) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFound("Update-User", "UserId", id)
        );
        
        userEntity.setAbout(updateUser.getAbout());
        userEntity.setEmail(updateUser.getEmail());
        userEntity.setName(updateUser.getName());
        userEntity.setPassword(updateUser.getPassword());

        UserEntity savedUser = userRepository.save(userEntity);
        
        return modelMapper.map(savedUser,UserResponse.class);
    }

    @Override
    public UserResponse updateUserPassAndAbout(UpdateAbtAndPass updateUserAbtAndPass, Long id) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Update-User-Pass&Abt", "userId", id));

        // TODO: Check if the user is null or not and check whether password is updated
        // or not. If password is not updated, then set the password to the previous
        // password.
        // If password is updated, then set the password to the new password.

        userEntity.setAbout(updateUserAbtAndPass.getAbout());
        userEntity.setPassword(updateUserAbtAndPass.getPassword());

        UserEntity savedUser = userRepository.save(userEntity);
        return modelMapper.map(savedUser, UserResponse.class);

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Delete-User", "UserId", id));
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> getUsers() {

        List<UserEntity> users = userRepository.findAll();

        List<UserResponse> responseUsers = new ArrayList<>();
        for (UserEntity user : users) {
            responseUsers.add(modelMapper.map(user, UserResponse.class));
        }
        return responseUsers;
    }

    @Override
    public UserResponse getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Get-User-By-Id", "userId", id));
        return modelMapper.map(userEntity, UserResponse.class);
    }

    // For updating single field of the user-entity i.e. password we can use the
    // following method
    public String updatePassword(String password, Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Update-User-Password", "userId", id));
        userEntity.setPassword(password);
        userRepository.save(userEntity);
        return "Password updated successfully";
    }

    // public UserEntity userDtoToEntity(CreateUser createUser) {

    // UserEntity userEntity = new UserEntity();
    // userEntity.setName(createUser.getName());
    // userEntity.setEmail(createUser.getEmail());
    // userEntity.setPassword(createUser.getPassword());
    // userEntity.setAbout(createUser.getAbout());
    // return userEntity;
    // }

    // public ResponseUser userEntityToDto(UserEntity userEntity) {
    // ResponseUser responseDto = new ResponseUser();
    // responseDto.setName(userEntity.getName());
    // responseDto.setEmail(userEntity.getEmail());
    // responseDto.setAbout(userEntity.getAbout());
    // return responseDto;
    // }

    /*
     * Instead of writing the above two methods, we can use ModelMapper to map the
     * fields of the two classes.
     */
}
