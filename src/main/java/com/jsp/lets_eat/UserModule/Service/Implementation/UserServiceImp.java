package com.jsp.lets_eat.UserModule.Service.Implementation;

import com.jsp.lets_eat.UserModule.Dto.LoginRequest;
import com.jsp.lets_eat.UserModule.Dto.USerResponse;
import com.jsp.lets_eat.UserModule.Dto.UserRequest;
import com.jsp.lets_eat.UserModule.Entity.User;
import com.jsp.lets_eat.CommonModule.Exception.UserException;
import com.jsp.lets_eat.UserModule.Service.UserService;
import com.jsp.lets_eat.UserModule.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import static com.jsp.lets_eat.UserModule.Entity.Role.USER;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

   private final UserRepository userRepository;

   private final PasswordEncoder passwordEncoder;

    @Override
    public USerResponse register(UserRequest userRequest) {
        if(!userRepository.existsByEmail(userRequest.getEmail())){
            User user=new User(userRequest);
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setRole(USER);
            user.setActive(true);
            User savedUser = userRepository.save(user);
            return new USerResponse(savedUser);
        }else {
            User u=userRepository.findByEmail(userRequest.getEmail()).get();
            if(u.getActive()){
                throw new UserException("User with email: " + userRequest.getEmail() + " already exists");
            }else {
                u.setActive(true);
                u.setId(u.getId());
                u.setName(userRequest.getName());
                u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
                u.setPhone(userRequest.getPhone());
                return new USerResponse(userRepository.save(u));
            }
        }
    }

    @Override
    public USerResponse login(LoginRequest login) {
        User user=userRepository.findByEmail(login.getEmail()).orElseThrow(() -> new UserException("User not found with email: " + login.getEmail()));
        if(user.getActive()){
            if(user.getPassword().equals(login.getPassword())){
                return new USerResponse(user);
            }else {
                throw new UserException("Incorrect password for email: " + login.getEmail());
            }
        }else {
            throw new UserException("User with email: " + login.getEmail() + " is inactive");
        }
    }

    @Override
    public USerResponse profile(Integer id) {
        User user=userRepository.findById(id).orElseThrow(() -> new UserException("User not found with id: " + id));
        if(user.getActive()){
            return new USerResponse(user);
        }else {
            throw new UserException("User with id: " + id + " is inactive");
        }
    }
git brancj
    public void delete(Integer id){
        User user=userRepository.findById(id).orElseThrow(() -> new UserException("User not found with id: " + id));
        user.setActive(false);
        userRepository.save(user);
    }

    public USerResponse updatePassword(String email,String oldPassword,String newPassword){

       User user=userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found with email: " + email));

       if(user.getActive()){
           if(user.getPassword().equals(oldPassword)){
               user.setPassword(newPassword);
               User updatedUser=userRepository.save(user);
               return new USerResponse(updatedUser);
           }else {
               throw new UserException("Incorrect old password for email: " + email);
           }

       }else {
           throw new UserException("User with email: " + email + " is inactive");
       }
    }
}
