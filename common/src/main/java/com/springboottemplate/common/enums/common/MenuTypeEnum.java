package com.springboottemplate.common.enums.common;


import com.springboottemplate.common.enums.BasicEnum;

/**
 * @author Sleepyhead 对应 sys_menu表的menu_type字段
 */
public enum MenuTypeEnum implements BasicEnum<Integer> {

    /**
     * 菜单类型
     */
    MENU(1, "页面"),
    CATALOG(2, "目录"),
    IFRAME(3, "内嵌Iframe"),
    OUTSIDE_LINK_REDIRECT(4, "外链跳转"),
    Button(5, "按钮");
    private final int value;
    private final String description;

    MenuTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String description() {
        return description;
    }


}
