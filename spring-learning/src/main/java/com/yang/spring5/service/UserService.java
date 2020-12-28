package com.yang.spring5.service;

import com.yang.spring5.dao.UserDao;

/**
 * @Author: csy
 * @Date: 2020/12/28 22:01
 * @Description:
 */
public class UserService {

    /**
     * 创建UserDao类型属性，生成set方法
     */
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        System.out.println("service add....");
        userDao.update();
    }
}
