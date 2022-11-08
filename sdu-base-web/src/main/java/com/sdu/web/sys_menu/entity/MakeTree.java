package com.sdu.web.sys_menu.entity;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Connor
 * @date 2022/11/4 11:51
 */
public class MakeTree {
    public static List<SysMenu> makeMenuTree(List<SysMenu> menuList, Long pid) {
        // 定义接收返回值的list
        List<SysMenu> list = new ArrayList<>();
        // jdk1.8 新特性
        // ofNullable: 判断menuList是否为空
        // orElse: 如果为空,返回空的ArrayList,如果不为空,往下走stream
        // filter: 过滤,要求item都不为空,而且getParentId要与传过来的pid一致
        // forEach: 把当前的数据放进menu
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && item.getParentId() == pid)
                .forEach(dom -> {
                    SysMenu menu = new SysMenu();
                    BeanUtils.copyProperties(dom, menu);
                    // 获取该项的下级菜单,递归
                    List<SysMenu> menuTree = makeMenuTree(menuList, dom.getMenuId());
                    menu.setChildren(menuTree);
                    list.add(menu);
                });
        return list;
    }

    /**
     * 生成路由数据格式
     * @param menuList
     * @param pid
     * @return
     */
    public static List<RouterVo> makeRouter(List<SysMenu> menuList, Long pid){
        // 接受生产的路由数据
        List<RouterVo> list = new ArrayList<>();
        // 组装数据
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item ->item != null && item.getParentId() == pid)
                .forEach(item ->{
                    RouterVo router = new RouterVo();
                    router.setName(item.getName());
                    router.setPath(item.getPath());
                    // 判断是否是一级菜单
                    if(item.getParentId() == 0L){
                        router.setComponent("Layout");
                        router.setAlwaysShow(true);
                    }else{
                        router.setComponent(item.getUrl());
                        router.setAlwaysShow(false);
                    }
                    // 设置meta
                    router.setMeta(router.new Meta(
                            item.getTitle(),
                            item.getIcon(),
                            item.getCode().split(",")
                    ));
                    // 设置children
                    List<RouterVo> children = makeRouter(menuList, item.getMenuId());
                    router.setChildren(children);
                    if(router.getChildren().size() > 0){
                        router.setAlwaysShow(true);
                    }
                    list.add(router);
                });
        return list;
    }
}
