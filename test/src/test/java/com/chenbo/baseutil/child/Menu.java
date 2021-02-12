package com.chenbo.baseutil.child;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-08-28
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Integer id;
    private Integer pid;
    private String name;
    private List<Menu> child;

    public Menu(Integer id, Integer pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        //return new Menu(id, pid, name, null);
    }
}