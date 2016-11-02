package com.alibaba.dubbo.common.logger.log4j2;

import com.alibaba.dubbo.common.logger.Level;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

public class Log4j2LoggerAdapter implements LoggerAdapter {

    @SuppressWarnings("unchecked")
    public Log4j2LoggerAdapter() {
        try {
            org.apache.logging.log4j.Logger logger = LogManager.getLogger();
            if (logger != null) {
                Map<String, Appender> appenderMap =
                        ((org.apache.logging.log4j.core.Logger) logger).getAppenders();
                for (Map.Entry<String, Appender> entry : appenderMap.entrySet()) {
                    if (entry.getValue() instanceof FileAppender) {
                        String filename = ((FileAppender) entry.getValue()).getFileName();
                        file = new File(filename);
                        break;
                    }
                }
            }
        } catch (Throwable t) {
        }
    }

    public Logger getLogger(String key) {
        return new Log4j2Logger(LoggerFactory.getLogger(key));
    }

    public Logger getLogger(Class<?> key) {
        return new Log4j2Logger(LoggerFactory.getLogger(key));
    }

    private File file;

    public void setLevel(Level level) {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(toLog4jLevel(level));
        ctx.updateLoggers();
    }

    public Level getLevel() {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        return fromLog4jLevel(loggerConfig.getLevel());
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }


    public static org.apache.logging.log4j.Level toLog4jLevel(Level level) {
        if (level == Level.ALL)
            return org.apache.logging.log4j.Level.ALL;
        if (level == Level.TRACE)
            return org.apache.logging.log4j.Level.TRACE;
        if (level == Level.DEBUG)
            return org.apache.logging.log4j.Level.DEBUG;
        if (level == Level.INFO)
            return org.apache.logging.log4j.Level.INFO;
        if (level == Level.WARN)
            return org.apache.logging.log4j.Level.WARN;
        if (level == Level.ERROR)
            return org.apache.logging.log4j.Level.ERROR;
        return org.apache.logging.log4j.Level.OFF;
    }

    public static Level fromLog4jLevel(org.apache.logging.log4j.Level level) {
        if (level == org.apache.logging.log4j.Level.ALL)
            return Level.ALL;
        if (level == org.apache.logging.log4j.Level.TRACE)
            return Level.TRACE;
        if (level == org.apache.logging.log4j.Level.DEBUG)
            return Level.DEBUG;
        if (level == org.apache.logging.log4j.Level.INFO)
            return Level.INFO;
        if (level == org.apache.logging.log4j.Level.WARN)
            return Level.WARN;
        if (level == org.apache.logging.log4j.Level.ERROR)
            return Level.ERROR;
        return Level.OFF;
    }

}
