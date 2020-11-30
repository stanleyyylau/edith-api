package com.abc.todo.common;

import com.abc.todo.model.User;

public class LocalUser {

    private static ThreadLocal<User> local = new ThreadLocal<>();

    /**
     * 得到当前登录用户
     *
     * @return user | null
     */
    public static User getLocalUser() {
        return LocalUser.local.get();
    }

    /**
     * 设置登录用户
     *
     * @param user user
     */
    public static void setLocalUser(User user) {
        LocalUser.local.set(user);
    }

    public static <T> T getLocalUser(Class<T> clazz) {
        return (T) local.get();
    }

    /**
     * 清理当前用户
     */
    public static void clearLocalUser() {
        LocalUser.local.remove();
    }
}

