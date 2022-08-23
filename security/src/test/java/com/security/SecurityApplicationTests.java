package com.security;

import com.security.entity.User;
import com.security.service.IMenuService;
import com.security.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SecurityApplicationTests {
@Autowired
private IMenuService menuService;
  @Autowired
  private IUserService userService;
  @Test
  void contextLoads() {
    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    String encode = bCryptPasswordEncoder.encode("1234");
    System.out.println(encode);
  }
@Test
  public void test(){
  List<User> list = userService.list();
  list.forEach(System.out::println);
}
@Test
  public void test2(){
  List<String> list = menuService.selectAllParamsByUserId(1);
  list.forEach(System.out::println);

}
}
