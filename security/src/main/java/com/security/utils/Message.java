package com.security.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Integer code;
    private String message;
    Model model=new ConcurrentModel();

    public static Message success(){
        Message message=new Message();
        message.setCode(100);
        message.setMessage("处理成功");
        return message;
    }
    public static Message fail(){
        Message message=new Message();
        message.setCode(200);
        message.setMessage("处理失败");
        return message;
    }
    public  Message add(String key,Object value){
        this.getModel().addAttribute(key,value);
        return this;
    }

}
