package com.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sv_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "menu_id",type = IdType.AUTO)
    private Integer menuId;
    private String name;
    private String params;
}
