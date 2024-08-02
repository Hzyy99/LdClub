# ld-club
临大Club
项目描述：
临大Club是一款专门为临大学生打造的沟通交流社区，采用主流的微服务框架+主流C端技术栈来实现。旨在
为临大同学提供一个练习计算机试题的平台。

技术栈：
SpringBoot+SpringCloud Alibaba+SSM+MySQL+Redis+Nacos+Gateway+Minio+RcoketMQ

项目职责
1、采用微服务领域拆分思想，对项目模块进行领域设计，业务解耦，专注自身职责。

2、封装注解式工具实现对请求、响应日志的全面解析。

3、选取主流鉴权框架Sa-Token来替代传统的Secruity，提高开发效率。

4、采用Gateway配合Redis实现统一的鉴权及分布式会话共享功能，在网关层实现统一的全局异常处理。

5、采取工厂+策略模式实现微信的消息解耦处理，采取适配器模式实现OSS对接。

6、使用ThreadLocal配合网关拦截器，Feign拦截器，封装用户上下文全局工具。

7、针对高并发接口，利用AOP切面编程封装工具类搭配Caffeine+Redis实现二级缓存，提升性能及通用性。

8、封装自定义的Esclient，支持多索引切换，封装常用的业务函数，实现网站的高亮搜索功能。

9、基于Redis的Zset实现实时排行榜功能，并利用RcoletMq优化，解决了Redis存储点赞可能丢数据的问题。

10、基于Futuretask及Completablefuture实现了分类标签的并发查询，提升系统性能。

11、封装了自定义的线程工厂，实现了线程池间的日志区分，提升了日志排查效率。

12、选取xxl-job配合redis的Hash接口实现点赞收藏功能的开发及数据持久化，减轻数据库交互压力。

