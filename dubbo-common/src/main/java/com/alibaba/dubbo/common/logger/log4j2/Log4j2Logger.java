
package com.alibaba.dubbo.common.logger.log4j2;

import com.alibaba.dubbo.common.logger.Logger;

/**
 * author: yangjunming (http://yjmyzz.cnblogs.com/)
 * since: 2016-02-24
 */
public class Log4j2Logger implements Logger {

    //private static final String FQCN = FailsafeLogger.class.getName();

    private final org.slf4j.Logger logger;

    public Log4j2Logger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    public void trace(String msg) {
        logger.trace(msg);
    }

    public void trace(Throwable e) {
        logger.trace(e == null ? null : e.getMessage());
    }

    public void trace(String msg, Throwable e) {
        logger.trace(msg, e);
    }

    public void debug(String msg) {
        logger.debug(msg);
    }

    public void debug(Throwable e) {
        logger.debug(e == null ? null : e.getMessage());
    }

    public void debug(String msg, Throwable e) {
        logger.debug(msg, e);
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void info(Throwable e) {
        logger.info(e == null ? null : e.getMessage());
    }

    public void info(String msg, Throwable e) {
        logger.info(msg, e);
    }

    public void warn(String msg) {
        logger.warn(msg);
    }

    public void warn(Throwable e) {
        logger.warn(e == null ? null : e.getMessage());
    }

    public void warn(String msg, Throwable e) {
        logger.warn(msg, e);
    }

    public void error(String msg) {
        logger.error(msg);
    }

    public void error(Throwable e) {
        logger.error(e == null ? null : e.getMessage());
    }

    public void error(String msg, Throwable e) {
        logger.error(msg, e);
    }

    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

}