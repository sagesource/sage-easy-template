# sage-easy-template

# 介绍
**Sage-Easy-Templete**是一个J2EE项目快速开发脚手架,集成了目前常用的开发框架,并可以灵活的进行配置.适用于RESTFUL风格的serveice开发,同时也适用于后台项目开发.

## 框架组成
### 主要开发框架
- **Spring4.3.7** : 基于最新的Spring,尽量使用组件提供的新功能
- **SpringMVC4.3.7** : 流行的MVC开发框架
- **MyBatis3.4.1** : ORM框架,使用主流的Mybatis
- **Logback1.2.1** : 日志系统
    + 使用over-slf4j组件,将所有的其他日志系统转换到SLF4J
- **Bootstrap3** : 前端页面CSS框架(因为前端结构多样,这里只是为使用者引入起到规范和示例作用)
- **Maven** : 项目构建工具

### 可选的工具框架
- **Spring Session1.3.0** :  基于Spring的Session管理,当项目进行集群部署时,无需对容器进行session一致性配置
- **Spring Mail** : 基于Spring的邮件组件
- **Spring JMS** : Spring JMS 组件,脚手架使用ActiveMQ作为参考MQ服务

### 测试工具框架
- **Spring Test** : 基于的Spring的单元测试框架
- **Mock MVC** : MockMvc实现了对Http请求的模拟，能够直接使用网络的形式，转换到Controller的调用，这样可以使得测试速度快、不依赖网络环境，而且提供了一套验证的工具，这样可以使得请求的验证统一而且很方便。

### 文档生成工具
- **Swagger** : Rest文档的生成工具,由于该工具要对已有代码进行注解,侵入性比较强,我个人不太建议使用.而是api的开发人员编写文档和测试页面

# 使用

# 未来规划
- 以该项目为蓝本,编写基于web的,可管理,可插拔的工程结构生成器