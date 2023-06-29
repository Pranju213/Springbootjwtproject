package com.springbootjwt.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springbootjwt.exception.*;
import com.springbootjwt.dto.LoginDTO;
import com.springbootjwt.dto.UserDTO;
import com.springbootjwt.model.Role;
import com.springbootjwt.model.User;
import com.springbootjwt.repository.RoleRepository;
import com.springbootjwt.repository.UserRepository;
import com.springbootjwt.service.UserService;
import com.springbootjwt.util.JwtUtil;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void registerUser(UserDTO userDTO) {
        // Add Validation To Restrict Roles If Needed.
        // Add Validations For Unique Email
        Optional<Role> optionalRole = 
        	roleRepository.findByName(userDTO.getRoleName());
        
      if(optionalRole.isEmpty())
      {
         throw new BadRequestException("Select a valid role.");
      }
       
      User user=User.builder()
      				.name(userDTO.getName())
        			.address(userDTO.getAddress())
                	.emailAddress(userDTO.getEmailAddress())
                    .role(optionalRole.get())
                    .password(passwordEncoder.encode
                    		(userDTO.getPassword()))
                	.build();
        userRepository.save(user);
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Optional<User> userOptional=
        	userRepository.findByEmailAddress
            	(loginDTO.getEmailAddress());
        
        if(userOptional.isEmpty())
        {
            throw new BadRequestException("User Not Found.");
        }
        if(passwordEncoder.matches
        	(loginDTO.getPassword(),
            userOptional.get().getPassword())
          )
        {
            return jwtUtil.generateAccessToken
            		(userOptional.get());
        }
        else
        {
           throw new BadRequestException("Invalid UserName Or Password");
        }
    }
}
