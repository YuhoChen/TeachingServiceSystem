package com.yuhao.TeachingServiceSystem.common;

import java.util.HashMap;
import java.util.Map;

public enum MenuLevels {
    Level0(0, "根菜单"),
    Level1(1, "一级菜单"),
    Level2(2, "二级菜单"),
    Level3(3, "三级菜单");

    private static Map<Integer, MenuLevels> map = new HashMap<Integer, MenuLevels>();

    static {
        map.put(0, Level0);
        map.put(1, Level1);
        map.put(2, Level2);
        map.put(3, Level3);
    }

    private Integer level;
    private String levelName;
    private MenuLevels(Integer level, String levelName) {
        this.level = level;
        this.levelName = levelName;
    }

    public Integer level() {
        return level;
    }

    public String levelName() {
        return levelName;
    }

    public static MenuLevels get(int level) {
        return map.get(level);
    }
}
