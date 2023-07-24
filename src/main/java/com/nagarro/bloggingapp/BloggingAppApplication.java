package com.nagarro.bloggingapp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// import com.nagarro.bloggingapp.common.security.CustomUserDetailService;
import com.nagarro.bloggingapp.role.RoleEntity;
import com.nagarro.bloggingapp.role.RoleRepository;
import com.nagarro.bloggingapp.user.UserRepository;



@SpringBootApplication
public class BloggingAppApplication implements CommandLineRunner{

	private RoleRepository roleRepository;

	// private UserRepository userRepository;
	
	@Value("${normal.role.id}")
	private String roleNormalId;

	@Value("${admin.role.id}")
	private String roleAdminId;

	public BloggingAppApplication(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BloggingAppApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		
		try{
	           RoleEntity admin = RoleEntity.builder().roleId(roleAdminId).roleName("Role_Admin").build();
		       
			   RoleEntity user = RoleEntity.builder().roleId(roleNormalId).roleName("Role_User").build();
		       
			   roleRepository.save(admin);
			   roleRepository.save(user);

			} 
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Bean
	ModelMapper modelMapper() {
		
		return new ModelMapper();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
 
	// @Bean
	// CustomUserDetailService customUserDetailService() {
	// 	return new CustomUserDetailService(userRepository);
	// }

	
}
