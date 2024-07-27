# ld-club
临大Club
项目描述：
临大Club是一款专门为临大学生打造的沟通交流社区，采用主流的微服务框架+主流C端技术栈来实现。旨在
为临大同学提供一个练习计算机试题的平台。

技术栈：
SpringBoot+SpringCloud Alibaba+SSM+MySQL+Redis+Nacos+Gateway+Minio+RcoketMQ

项目职责
1、采用微服务领域拆分思想，对项目模块进行领域设计，业务解耦，专注自身职责；

2、基于Nacos来实现业务项目的服务注册与发现及业务动态配置切换；

3、选取主流鉴权框架Sa-Token来替代传统的Secruity，提高开发效率；

4、采用Gateway配合Redis实现统一的鉴权及分布式会话共享功能，在网关层实现统一的全局异常处理；

5、采取工厂+策略模式实现微信的消息解耦处理，采取适配器模式实现OSS对接.

6、使用ThreadLocal配合网关拦截器，feign拦截器，封装用户上下文全局工具。

7、针对高并发接口，采取了Caffeine本地缓存配合函数式编程**，泛型封装本地缓存工具，提升性能及通用
性；

8、封装自定义的Esclient，支持多集群，多索引切换，封装了常用的业务函数，实现网站的高亮搜索功能；

9、基于Redis的Zset实现实时排行榜功能，解决传统数据库大量交互的瓶颈点；

10、使用RocketMQ，优化原有点赞功能，解决了redis存储点赞可能丢数据的问题。

