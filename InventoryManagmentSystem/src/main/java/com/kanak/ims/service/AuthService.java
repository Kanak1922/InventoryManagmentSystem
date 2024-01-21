package com.kanak.ims.service;

import com.kanak.ims.dto.LoginDto;


public interface AuthService {
    String login(LoginDto loginDto);
}
