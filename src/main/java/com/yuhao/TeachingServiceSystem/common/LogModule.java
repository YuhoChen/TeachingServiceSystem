package com.yuhao.TeachingServiceSystem.common;

public enum LogModule {
    Chat(0, "即时通讯"),
    MChat(0, "手机端即时通讯"),

    Contract(0, "通讯录"),
    MContract(0, "手机端通讯录"),

    DishCategory(0, "菜品类别"),
    MDishCategory(0, "手机端菜品类别"),

    Dish(0, "菜品"),
    MDish(0, "手机端菜品"),

    Example(0, "示例"),

    Friend(0, "好友"),
    MFriend(0, "手机端好友"),

    FriendGroup(0, "好友群组"),
    MFriendGroup(0, "手机端好友群组"),

    FriendRequest(0, "好友请求"),
    MFriendRequest(0, "手机端好友请求"),

    Menu(0, "菜单"),

    MessagePush(0, "消息推送"),

    OnlieUser(0, "在线用户"),
    MOnlieUser(0, "手机端在线用户"),

    Order(0, "订单"),
    MOrder(0, "手机端订单"),

    OrderItem(0, "订单详情"),
    MOrderItem(0, "手机端订单详情"),

    Role(0, "角色"),

    SystemConfig(0, "系统配置"),
    MSystemConfig(0, "手机端系统配置"),

    SystemLog(0, "系统日志"),

    SystemMessage(0, "系统消息"),
    MSystemMessage(0, "手机端系统消息"),

    TopicComment(0, "话题评论"),
    MTopicComment(0, "手机端话题评论"),

    Topic(0, "公告或话题"),
    MTopic(0, "手机端公告或话题"),

    MMap(0, "手机端地图"),

    User(0, "用户"),
    Promo(0, "用户"),
    MUser(0, "手机端用户");

    private int value;
    private String remark;

    private LogModule(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }
    public String getRemark() {
        return remark;
    }

    public LogModule getByValue(int value) {
        for (LogModule o : LogModule.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
}
