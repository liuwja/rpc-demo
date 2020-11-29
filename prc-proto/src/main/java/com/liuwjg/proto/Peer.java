package com.liuwjg.proto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 网络传输的一个端点
 *
 * @author liuwjg
 */
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}
