package com.bk.authservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 14/02/22
 */
public interface AuthenticationController {
    void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception; //TODO throws AuthenticationException

}
