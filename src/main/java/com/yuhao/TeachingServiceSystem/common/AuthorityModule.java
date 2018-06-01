package com.yuhao.TeachingServiceSystem.common;

public enum AuthorityModule {
    Merchant(1, "商户模块"),
    System(2, "系统模块");

    private int value;
    private String remark;

    private AuthorityModule(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public static AuthorityModule getByValue(int value) {
        for (AuthorityModule o : AuthorityModule.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
}
