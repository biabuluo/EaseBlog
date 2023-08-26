package com.biabuluo.controller;

import com.biabuluo.domain.ResponseResult;
import com.biabuluo.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小宇
 * @date 2023-08-26:20:25
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    //获取所有友链信息
    @GetMapping("/getAllLinks")
    public ResponseResult getAllLinks(){
        return linkService.allLinks();
    }

}
