package com.jsp.lets_eat.UserModule.Service;

import com.jsp.lets_eat.UserModule.Dto.LoginRequest;
import com.jsp.lets_eat.UserModule.Dto.USerResponse;
import com.jsp.lets_eat.UserModule.Dto.UserRequest;

public interface UserService {

    USerResponse register(UserRequest user);
    USerResponse login(LoginRequest login);
    USerResponse profile(Integer id);
    void delete(Integer id);
    USerResponse updatePassword(String email,String oldPassword,String newPassword);
}
