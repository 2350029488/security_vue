package com.security.controller;


import com.alibaba.fastjson.JSON;
import com.security.entity.User;
import com.security.service.IUserService;
import com.security.utils.JwtUtil;
import com.security.utils.LoginUser;
import com.security.utils.Message;
import com.security.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黄隆
 * @since 2022-08-12
 */

@RestController
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/user/login")
    public Message login(@RequestBody User user){//前端一般是json格式传递，使用请求体接收
    /** 通过authenticationManager.authenticate()进行认证，
     * 其底层会调用UserDetailsService接口的loadUserByUsername方法，通过传入的user.getUserName(),user.getPassword()
     *与UserDetails接口的  String getPassword() String getUsername(); 方法其实现类LoginUser的返回值进行对比
     * 将UserDetails接口实现类进行封装到 Authentication接口中
     *  */
    UsernamePasswordAuthenticationToken authenticationToken=
            new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        /** 如果为null 则上一步认证不成功*/
        if (Objects.isNull(authenticate)){
            throw  new RuntimeException("登录失败");
        }
        /**认证成功则获取其封装的LoginUser 注意这个LoginUser的getUser就是登录用户的完整信息 不懂的看LoginImpl中*/
        LoginUser getUser = (LoginUser) authenticate.getPrincipal();
        /*获取登录用户的id*/
        String id = getUser.getUser().getId().toString();
        /*使用JWT生成token*/
        String token = JwtUtil.createJWT(id);
        /*将登录的用户信息转为json格式存入redis中*/
        String redisUser = JSON.toJSONString(getUser);
        redisUtil.setCacheObject("sv_id:"+id,redisUser);
        /*携带token响应到浏览器*/
        return Message.success().add("token",token);
    }

    @PostMapping("/user/register")
    public Message register(@RequestBody User user){
        String encoderPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoderPassword);
       userService.save(user);
        return Message.success();
    }

    @DeleteMapping("/user/exit")
    public Message exit()  {
/**在过滤器中把登录用户的信息封装到SecurityContextHolder中，现在可以对其进行获取 */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
          /** 通过键删除*/
        redisUtil.deleteObject("sv_id:"+userId);
        return Message.success();
    }

    @PreAuthorize("hasAuthority('list:1')")
    @GetMapping("/test")
    public Message sss(){
        return Message.success();
  }

}
