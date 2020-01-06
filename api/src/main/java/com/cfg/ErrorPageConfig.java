package com.cfg;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @description: 错误页面跳转配置
 * @author: endure
 * @create: 2020-01-03 14:42
 **/
@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage err404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
        ErrorPage err500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
        ErrorPage err400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400");
        registry.addErrorPages(err400,err404,err500);
    }
}
