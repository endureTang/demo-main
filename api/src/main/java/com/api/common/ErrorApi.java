package com.api.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 错误信息返回接口
 * @author: endure
 * @create: 2020-01-03 14:47
 **/
@RestController
@RequestMapping(value = "error")
public class ErrorApi {

    @GetMapping(value = "404")
    public String error404(){
        return "没有找到页面";
    }

    @GetMapping(value = "400")
    public String error400(){
        return "参数错误";
    }

    @GetMapping(value = "500")
    public String error500(){
        return "服务器暂停使用";
    }
}
