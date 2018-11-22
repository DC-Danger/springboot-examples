package com.hz.learnboot.websocket.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息
 *
 * @Author hezhao
 * @Time 2018-07-09 0:58
 */
@Data @NoArgsConstructor
public class SocketMessage {
    public String message;

    public String date;
}
