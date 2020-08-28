package com.chenbo.baseutil.child;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-08-28
 */
public class MenuTest {

    @Test
    public void childTest() {
        List<Menu> childList = treeMenuList(getMenus(), null, 0);
        System.out.println(childList);
    }

    /**
     * 获取某个父节点下面的所有子节点
     *
     * @param source
     * @param pid
     * @return
     */
    public List<Menu> treeMenuList(List<Menu> source, List<Menu> target, int pid) {
        if (CollectionUtils.isEmpty(target)) {
            target = new ArrayList<>();
        }
        for (Menu menu : source) {
            // 遍历出父id等于参数的id，add进子节点集合
            if (menu.getPid() == pid) {
                // 递归遍历下一级
                target.add(menu);
                menu.setChild(treeMenuList(source, menu.getChild(), menu.getId()));
            }
        }
        return target;
    }

    private List<Menu> getMenus() {
        List<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu(1, 0, "目录"));
        menuList.add(new Menu(2, 1, "目录1"));
        menuList.add(new Menu(3, 2, "目录1.1"));
        menuList.add(new Menu(4, 2, "目录1.2"));
        menuList.add(new Menu(5, 1, "目录2"));
        menuList.add(new Menu(6, 5, "目录2.1"));
        menuList.add(new Menu(7, 5, "目录2.2"));
        menuList.add(new Menu(8, 7, "目录2.2.1"));
        menuList.add(new Menu(9, 10, "目录.不存在"));
        return menuList;
    }

}