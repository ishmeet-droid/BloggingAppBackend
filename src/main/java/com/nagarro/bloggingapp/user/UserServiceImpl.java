package com.nagarro.bloggingapp.user;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarro.bloggingapp.common.ResourceNotFound;
import com.nagarro.bloggingapp.role.RoleEntity;
import com.nagarro.bloggingapp.role.RoleRepository;
import com.nagarro.bloggingapp.user.dtos.UserRequestDto;
import com.nagarro.bloggingapp.user.dtos.UpdateAbtAndPassDto;
import com.nagarro.bloggingapp.user.dtos.UserResponseDto;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private BCryptPasswordEncoder passwordEncoder;
    
    @Value("${normal.role.id}")
    private String normalRoleId;

    private RoleRepository roleRepository;


    UserServiceImpl(UserRepository userRepository,
                     RoleRepository roleRepository,
                     ModelMapper modelMapper,
                     BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto createUser) {

        UserEntity userEntity = modelMapper.map(createUser, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(createUser.getPassword()));
        
        RoleEntity role = roleRepository.findById(normalRoleId).get();

        userEntity.getRoles().add(role);

       
        UserEntity savedUser = userRepository.save(userEntity);

        return modelMapper.map(savedUser, UserResponseDto.class);
    }
    

    @Override
    public UserResponseDto updateUser(UserRequestDto updateUser, Long id) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFound("Update-User", "UserId", id)
        );
        
        userEntity.setAbout(updateUser.getAbout());
        userEntity.setEmail(updateUser.getEmail());
        userEntity.setName(updateUser.getName());
        userEntity.setPassword(passwordEncoder.encode(updateUser.getPassword()));

        UserEntity savedUser = userRepository.save(userEntity);
        
        return modelMapper.map(savedUser,UserResponseDto.class);
    }

    @Override
    public UserResponseDto updateUserPassAndAbout(UpdateAbtAndPassDto updateUserAbtAndPass, Long id) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Update-User-Pass&Abt", "userId", id));

        // TODO: Check if the user is null or not and check whether password is updated
        // or not. If password is not updated, then set the password to the previous
        // password.
        // If password is updated, then set the password to the new password.

        userEntity.setAbout(updateUserAbtAndPass.getAbout());
        userEntity.setPassword(passwordEncoder.encode(updateUserAbtAndPass.getPassword()));

        UserEntity savedUser = userRepository.save(userEntity);
        return modelMapper.map(savedUser, UserResponseDto.class);

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Delete-User", "UserId", id));
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDto> getUsers() {

        List<UserEntity> users = userRepository.findAll();

        List<UserResponseDto> responseUsers = new ArrayList<>();
        for (UserEntity user : users) {
            responseUsers.add(modelMapper.map(user, UserResponseDto.class));
        }
        return responseUsers;
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Get-User-By-Id", "userId", id));
        return modelMapper.map(userEntity, UserResponseDto.class);
    }

    // For updating single field of the user-entity i.e. password we can use the
    // following method
    public String updatePassword(String password, Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Update-User-Password", "userId", id));
        userEntity.setPassword(passwordEncoder.encode(password));
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
