参考[程序猿DD](https://github.com/dyc87112)大佬的[Spring Boot基础教程](http://blog.didispace.com/Spring-Boot%E5%9F%BA%E7%A1%80%E6%95%99%E7%A8%8B/)，整理的学习笔记。

# 工程配置

1. **spring-boot-properties-demo**：
    * **内容**：
        * 属性配置文件的自定义属性与加载。
        * [属性配置文件的参数引用和随机数](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-properties-demo/src/main/resources/application.properties)。
        * 多环境配置，根据不同的环境将属性配置文件切换为生产、开发、测试。

# Web开发

1. **spring-boot-restful-demo**：
    * **内容**：
        * 构建RESTful API。
        * 总结常用的spring MVC注解，参见[UserController.java](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-restful-demo/src/main/java/com/leeyom/controller/UserController.java)注释。
        * 采用chrome浏览器的Postman插件测试各接口的调用情况。
    * **笔记**：
        * **[@RestController](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-restful-demo/src/main/java/com/leeyom/controller/UserController.java)**：Spring4之后加入的注解，原来在@Controller中返回json需要@ResponseBody来配合，如果直接用@RestController替代@Controller就不需要再配置。
        * **[@ModelAttribute](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-restful-demo/src/main/java/com/leeyom/controller/UserController.java)**: 绑定请求参数到指定的对象。
        * **[@PathVariable](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-restful-demo/src/main/java/com/leeyom/controller/UserController.java)**：获取请求url中的动态参数。
        * **[@RequestParam](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-restful-demo/src/main/java/com/leeyom/controller/UserController.java)**: 接受简单类型的属性，也可以接受对象类型。类似@RequestParam("id")等价于request.getParameter("id");
    
2. **spring-boot-template-demo**：
    * **内容**：
        * spring boot 配置 [thymeleaf](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-template-demo/src/main/resources/templates/index.html) 模板引擎。
        * 修改模板文件的后缀，以及默认路径。
        * Web应用的统一异常处理。
    * **笔记**：
        * 如果需要修改模板文件的后缀，以及默认路径，只需要在application.properties文件里修改如下的属性即可:
            ```properties
          # 是否启用缓存
          spring.thymeleaf.cache=true
          # 检查当前模板文件是否存在
          spring.thymeleaf.check-template-location=true
          # 设置模板文件的Content-Type
          spring.thymeleaf.content-type=text/html
          # 是否启用thymeleaf模板引擎
          spring.thymeleaf.enabled=true
          # 编码格式
          spring.thymeleaf.encoding=UTF-8
          # Comma-separated list of view names that should be excluded from resolution.
          spring.thymeleaf.excluded-view-names=
          # 模板文件的模式
          spring.thymeleaf.mode=HTML5
          # 模板文件的路径
          spring.thymeleaf.prefix=classpath:/templates/
          # 模板文件的后缀
          spring.thymeleaf.suffix=.html
            ```
        * **[@ControllerAdvice](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-template-demo/src/main/java/com/leeyom/web/GlobalExceptionHandler.java)**：定义一个统一的异常处理类。
        * **[@ExceptionHandler](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-template-demo/src/main/java/com/leeyom/web/GlobalExceptionHandler.java)**：用来定义函数针对的异常类型。这里定义的是顶级的异常父类 Execption，也就是只要其他的 handler 里面的有抛出任何的异常，
                都会触发这个方法，然后将其映射到 error.html页面中，也可以根据抛出的具体Exception类型匹配@ExceptionHandler中配置的异常类型来匹配错误映射和处理。    
3. **spring-boot-swagger-demo**：
    * **内容**：
        * Spring Boot中使用Swagger2构建RESTful API 文档。
    * **笔记**：
        * **[@ApiOperation](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-swagger-demo/src/main/java/com/leeyom/controller/UserController.java)**：给API增加说明
        * **[@ApiImplicitParam](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-swagger-demo/src/main/java/com/leeyom/controller/UserController.java)**：给单个参数添加说明
        * **[@ApiImplicitParams](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-swagger-demo/src/main/java/com/leeyom/controller/UserController.java)**：给多个参数添加说明
        
# 安全管理
    
1. **spring-boot-shiro-demo**：   
    * **内容**：
        * Spring boot 整合 shiro 进行权限控制和会话管理。
    * **笔记**：
        * 数据库表结构参考`resources`下面的[db.sql](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-shiro-demo/src/main/resources/db.sql)。
        * 数据库连接池采用阿里巴巴的`Druid(德鲁伊)`，配置文件[DruidConfig.java](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-shiro-demo/src/main/java/com/leeyom/config/DruidConfig.java)。
        * 集成shiro，可以对用户进行授权和认证以及会话管理，核心配置文件[ShiroConfig.java](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-shiro-demo/src/main/java/com/leeyom/config/ShiroConfig.java)。
        * 集成模板引擎thymeleaf，pom里面引入`thymeleaf-extras-shiro`依赖，这样在模板引擎里面可以使用shiro标签进行权限粗细度的控制。
        * 采用redis来管理session会话，redis配置文件[RedisConfig.java](https://github.com/wangleeyom/spring-boot-learning/blob/master/spring-boot-shiro-demo/src/main/java/com/leeyom/config/RedisConfig.java)。
        * 集成分页插件`PageHelper`，详情见[分页插件 PageHelper](https://pagehelper.github.io/)。
        * 集成大牛封装好的`通用Mapper`插件，详情见[通用Mapper](https://mapperhelper.github.io/docs/)。
        * mybatis相关的工具、源码、文档、插件、示例参考：[MyBatis 相关工具](http://www.mybatis.tk/)。
    * **问题**：
        * 无法注入mapper，经过检查发现，在程序启动的入口处（Application.java），需要对dao包进行扫描，添加注解`@MapperScan(basePackages = "com.leeyom.dao")`既可以解决问题。
        * `Application.java`应该和service、dao、controller等package同级的位置，不然会引起扫描不到注解的问题，这里是需要注意的一点。