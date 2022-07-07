package cn.muratjan.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MRT
 * @date 2022/7/5 17:13
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
