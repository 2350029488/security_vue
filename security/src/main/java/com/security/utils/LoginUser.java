package com.security.utils;


import com.alibaba.fastjson.annotation.JSONField;
import com.security.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 实现UserDetails 作为封装类
 *
 因为UserDetailsService方法的返回值是UserDetails类型
 ，所以需要定义一个类，实现该接口，把用户信息封装在其中。
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {
    private User user;

    private List<String> permissions;
    public LoginUser(User user,List<String> permissions){
        this.user=user;
        this.permissions=permissions;
    }

    /*一定的那个要拿出来 不然User permissions 为null*/
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    /**返回权限信息的*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities!=null){
            return authorities;
        }
    authorities=new ArrayList<>();
        for (String permission:permissions){
            SimpleGrantedAuthority simpleGrantedAuthority
                    =new SimpleGrantedAuthority(permission);
            authorities.add(simpleGrantedAuthority);
        }
        return authorities;
    }
    /**用来获取密码*/
    
    @Override
    public String getPassword() {

        System.out.println("是否获取密码"+user.getPassword());

        return user.getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println("是否获取姓名"+user.getUserName());
        return user.getUserName();
    }
/**判断是否没过期的*/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**判断是否过期的*/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
/** 凭证没过期*/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**判断是否可用*/
    @Override
    public boolean isEnabled() {
        return true;
    }
}
