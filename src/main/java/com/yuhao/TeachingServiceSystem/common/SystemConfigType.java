package com.yuhao.TeachingServiceSystem.common;

public enum SystemConfigType {

    appid("appid", null), // 公众号appid
    appsecret("appsecret", null), // 公众号appsecret
    merchantId("merchantId", null), // 微信支付merchantId

    WxInterfaceToken("WxInterfaceToken", "testtoken"), // 微信接口配置的token
    Welcome("welcome", null), // 关注欢迎消息

    ImagePath("ImagePath", null),
    isTestServer("isTestServer", null),
    None("none", null);

    private String value;
    private String defaultValue;

    private SystemConfigType(String value, String defaultValue) {
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public String value() {
        return this.value;
    }
    public String defaultValue() {
        return this.defaultValue;
    }
}
