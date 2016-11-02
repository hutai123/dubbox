package com.alibaba.dubbo.rpc.utils;

import com.alibaba.dubbo.rpc.RpcException;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by yangjunming on 2/24/16.
 * author: yangjunming@huijiame.com
 */
public class ErrorCodeUtils {

    public static int getErrorCode(Throwable e, Class<?> cls) {
        // 是根据测试Case发现的问题，对RpcException.setCode进行设置
        if (SocketTimeoutException.class.equals(cls)) {
            return RpcException.TIMEOUT_EXCEPTION;
        } else if (IOException.class.isAssignableFrom(cls)) {
            return RpcException.NETWORK_EXCEPTION;
        } else if (ClassNotFoundException.class.isAssignableFrom(cls)) {
            return RpcException.SERIALIZATION_EXCEPTION;
        }
        return 0;
    }
}
