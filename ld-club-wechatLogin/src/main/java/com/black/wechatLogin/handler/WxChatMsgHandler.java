package com.black.wechatLogin.handler;

import java.util.Map;

/**
** 微信消息工厂处理接口
 */
public interface WxChatMsgHandler {

    WxChatMsgTypeEnum getMsgType();

    String dealMsg(Map<String, String> messageMap);

}
