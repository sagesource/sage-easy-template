import ch.qos.logback.classic.AsyncAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

import java.nio.charset.Charset

// Logback 配置脚本
def env = System.getProperty('spring.profiles.active') ?: 'production'
println '.... >>>>log env<<<<:' + env

def appenderList = []
def level = ERROR

// 日志路径配置
def LOG_RECEIVE_DIR = '/apps/logs/log_receiver/sage-easy-templete'

// 配置不同环境的日志等级
if (env == 'production') {
    appenderList.add("ROLLING-ASYNC")
    level = WARN
} else if (env == 'development') {
    appenderList.add("CONSOLE")
    level = DEBUG
} else if (env == 'integrationtest') {
    appenderList.add("ROLLING-ASYNC")
    level = INFO
}

// 配置appender
if (env == 'development') {
    appender("CONSOLE", ConsoleAppender) {
        encoder(PatternLayoutEncoder) {
            pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
        }
    }
} else if (env == 'production' || env == 'integrationtest') {
    appender("ROLLING", RollingFileAppender) {
        encoder(PatternLayoutEncoder) {
            Pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
            charset = Charset.forName("UTF-8")
        }
        rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = "${LOG_RECEIVE_DIR}/translator-%d{yyyy-MM-dd-HH}.zip"
            timeBasedFileNamingAndTriggeringPolicy { maxFileSize = '10M' }
        }
    }

    appender("ROLLING-ASYNC", AsyncAppender) {
        discardingThreshold = 0
        queueSize = 1024
        appenderRef("ROLLING")
    }
}
root(level, appenderList)
