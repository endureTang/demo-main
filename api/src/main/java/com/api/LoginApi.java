package com.api;

import com.feign.MongodbService;
import com.feign.MqOrderService;
import com.model.exception.CommonException;
import com.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/** 
* @Description: 用户接口api
* @Author: endure
* @Date: 2019/12/31 
*/
@RestController
public class LoginApi {
    private static Logger logger = LoggerFactory.getLogger(LoginApi.class);
    /**
     * 注入用户service
     */
    @Resource
    private UserService userService;
    /**
     * 注入MongoDB 网关feign
     */
    @Resource
    private MongodbService mongodbService;

    @Resource
    private MqOrderService mqOrderService;

    /** 
    * @Description: 获取数据
    * @Param:  
    * @return:  
    * @Author: endure
    * @Date: 2019/12/31 
    */
    @RequestMapping(value = "login")
    public String login(@RequestParam(required = false) Map<String,String> condition){
        try {
            String name = condition.get("name");
            String password = condition.get("password");
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, password);
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            return "login success";
        } catch ( UnknownAccountException e ) {
            logger.error("登录出现错误：{}","用户未注册"+e.getMessage());
            return "用户未注册";
        } catch ( IncorrectCredentialsException e ) {
            logger.error("登录出现错误：{}","密码错误"+e.getMessage());
            return "密码错误";
        } catch ( LockedAccountException e ) {
            logger.error("登录出现错误：{}","该账户不可用"+e.getMessage());
            return "账户不可用";
        } catch ( ExcessiveAttemptsException e ) {
            logger.error("登录出现错误：{}","尝试次数超限"+e.getMessage());
            return "超过尝试次数";
        } catch(ExpiredCredentialsException e) {
            logger.error("登录出现错误：{}","验证码过期"+e.getMessage());
            return "验证码过期";
        } catch (DisabledAccountException e) {
            logger.error("登录出现错误：{}",e.getMessage());
            return "登录出现错误";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return "没有权限";
        } catch (CommonException e) {
            return e.getMessage();
        }catch (Exception e){
            logger.error("系统错误"+e);
            return null;
        }
    }

    /**
     * @Description: 获取数据
     * @Param:
     * @return:
     * @Author: endure
     * @Date: 2019/12/31
     */
    @RequestMapping
    public String index(){
        logger.info("index方法");
        return "首页页面";
    }


}
