package com.fjt.utils;

public class UserHolder {
    private static final ThreadLocal<Long> userId = new ThreadLocal<>();
    private static final ThreadLocal<Integer> role = new ThreadLocal<>();

    public static void setUserId(Long id) {
        userId.set(id);
    }

    public static Long getUserId() {
        return userId.get();
    }

    public static void setRole(Integer r) {
        role.set(r);
    }

    public static Integer getRole() {
        return role.get();
    }

    public static void clear() {
        userId.remove();
        role.remove();
    }
}