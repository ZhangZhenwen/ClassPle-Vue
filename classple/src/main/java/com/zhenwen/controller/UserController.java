package com.zhenwen.controller;

import com.zhenwen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenwen
 * @date 2020/11/10
 */
@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;


}
