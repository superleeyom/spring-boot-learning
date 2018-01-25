<p align="center">
  <a href="#"><img src="https://img.shields.io/badge/devTool-IDEA-yellow.svg" alt=""></a>
  <a href="#"><img src="https://travis-ci.org/Alamofire/Alamofire.svg?branch=master" alt=""></a>
  <a href="#"><img src="https://img.shields.io/packagist/l/doctrine/orm.svg" alt="LICENSE"></a>
  <a href="#"><img src="https://img.shields.io/badge/platform-OSX%7CWin%7CLinux-blue.svg" alt=""></a>
  <a href="#"><img src="https://badges.frapsoft.com/os/v1/open-source.svg?v=103" alt=""></a>   	
  <a href="#"><img src="https://img.shields.io/badge/language-java-blue.svg" alt=""></a>  
</p>

# 目录
- [x] [Hello World](#hello-world)
  - [x] [官方推荐目录](#官方推荐目录)
  - [x] [热部署](#热部署)
  - [x] [实例中涉及到的注解](#实例中涉及到的注解)
  - [x] [项目源码](#项目源码)
- [x] [快速Web开发](#快速web开发)
  - [x] [Json的支持](#json的支持)
  - [x] [请求传参](#请求传参)
  - [x] [参数校验](#参数校验)
  - [x] [自定义过滤器](#自定义过滤器)
  - [x] [读取Properties](#读取properties)
  - [x] [项目源码](#项目源码-1)
- [x] [Spring Data JPA 的使用](#spring-data-jpa-的使用)
  - [x] [JPA简单概念](#jpa简单概念)
  - [x] [快速上手](#快速上手)
  - [x] [基本查询](#基本查询)
  - [x] [复杂查询](#复杂查询)
  - [x] [关联查询](#关联查询)
  - [x] [项目源码](#项目源码-2)
- [x] [模板引擎Thymeleaf](#模板引擎-thymeleaf)
  - [x] [简单上手](#简单上手)
  - [x] [常用标签汇总](#常用标签汇总)
  - [x] [项目源码](#项目源码-3)
- [x] [JPA 和 Thymeleaf 实践](#jpa-和-thymeleaf-实践)
  - [x] [多环境配置](#多环境配置)
  - [x] [添加Servlet支持](#添加servlet支持)
  - [x] [效果图](#效果图)
  - [x] [项目源码](#项目源码-4)
- [x] [使用Swagger2构建RESTful API 文档](#使用swagger2构建restful-api-文档)
  - [x] [RESTful API设计准则](#restful-api设计准则)
  - [x] [swagger注解总结](#swagger注解总结)
  - [x] [快速上手](#快速上手-1)
  - [x] [项目源码](#项目源码-5)
- [ ] [Spring Boot 集成 MyBatis](#spring-boot-集成-myBatis)
  - [x] [Mybatis原理简介](#mybatis原理简介)
  - [ ] [官方组件包使用](#mybatis官方组件包使用)
    - [ ] [XML版本](#xml版本)
    - [ ] [注解版本](#注解版本)
    - [ ] [项目源码](#项目源码-6)
  - [ ] [第三方组件包使用](#第三方组件包使用)
    - [ ] [集成分页插件 PageHelper](#集成分页插件-pagehelper)
    - [ ] [集成通用 Mapper 插件](#集成通用-mapper-插件)
    - [ ] [项目源码](#项目源码-7)
- [ ] MyBatis Druid 多数据源
- [ ] 集成 Redis 实现数据缓存和 Session 共享
- [ ] 集成 dubbo+zookeeper
- [ ] 集成 RabbitMQ
- [ ] 集成 MongoDB
- [ ] Spring Boot 发送邮件
- [ ] 集成 quartz
- [ ] Spring Boot 集成测试和部署运维
- [ ] 综合实战用户管理系统

# 开发环境

- jdk 1.8
- IntelliJ IDEA 2017.2.4
- spring boot 1.5.9

# Hello World

## 官方推荐目录

spring boot官方建议的目录如下：
- root package 结构：`com.example.myproject`
  ```
  myproject
   +-src
      +- main
           +- java
                +- com.example.myproject
                      +- comm
                      +- domain
                      +- repository
                      +- service
                      +- web
                      +- Application.java
           +- resources
                +- static
                +- templates
                +- application.properties
      +- test
   +-pom.xml
  ```
- com.example.myproject 目录下：
  - Application.java：建议放到根目录下面，是项目的启动类，Spring Boot 项目只能有一个 main() 方法。
  - comm：目录建议放置公共的类，如全局的配置文件、工具类等。
  - domain：目录主要用于实体（Entity）与数据访问层（Repository）。
  - repository：数据库访问层代码。
  - service：该层主要是业务类代码。
  - web：该层负责页面访问控制。
- resources 目录下：
  - static：目录存放 Web 访问的静态资源，如 JS、CSS、图片等。
  - templates：目录存放页面模板。
  - application.properties：项目的配置信息。
- test目录下：
  - 单元测试的代码
- pom.xml：用于配置项目依赖包

## 热部署
- pom文件中引入热部署的依赖：
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
      <optional>true</optional>
  </dependency>
  ```
- 在 plugin 中配置另外一个属性 fork，并且配置为 true：
  ```xml
  <build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <fork>true</fork>
            </configuration>
        </plugin>
    </plugins>
  </build>
  ```
- Eclipse到此就已经支持spring boot的热部署了，idea还需要配置如下的几步：
  - 选择 `File-->Settings-->Compiler` 勾选 `Build project automatically`，低版本 idea 勾选 `make project automatically`。
  - 使用快捷键：`CTRL + SHIFT + A` 输入`Registry` 找到选项 `compile.automake.allow.when.app.running` 勾选。ok，搞定！

## 实例中涉及到的注解

- web开发：
  - `@RestController`：controller里面返回的结果都以json格式输出，就不需要引入`fastjson`相关的依赖，也不需要每个方法都要加`@ResponseBody`注解。
  - `@Controller`：就代表着输出为页面内容，跟spring mvc里面的注解一样。
- 单元测试：
  - `@SpringBootTest`：有此标签，系统会加载spring boot容器，这样就可以注入实例bean进行业务测试。
  - `@Test`：测试类启动入口。
  - 使用 `mockmvc` 进行 web 测试：
    ```java
    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class HelloWorldTest {
        private MockMvc mockMvc;
        @Before
        public void setUp() throws Exception {
            mockMvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
        }
        @Test
        public void getHello() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/hello?name=小明")
            .accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
        }
    }
    ```
## 项目源码

源码参考：[spring-boot-helloworld](https://github.com/wangleeyom/spring-boot-learning/tree/master/spring-boot-helloworld)

# 快速Web开发

## Json的支持

在使用SSM架构编写controller的时候，如果要返回json格式的数据，需要如下几步：
1. 在pom文件中添加解析json的库，比如`gson`、`fastjson`。
2. 配置spring mvc。
3. 在controller里的每个方法的上面添加`@ResponseBody`注解。

`@RestController`注解是Spring4之后加入的注解，原来在`@Controller`中返回json需要`@ResponseBody`来配合，如果直接用`@RestController`替代`@Controller`就不需要再配置，也不需要配置spring mvc，就可以发布一个http接口，非常方便快捷。

```java
@RestController
public class WebController {
    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setName("leeyom");
        user.setAge(24);
        user.setPass("123456");
        return user;
    }
}
```

## 请求传参
常用的传参注解：
- `@RequestBody`：绑定参数到指定对象，只适用于post和put请求，get请求不适用，在Spring Boot中，这个注解其实都不需要加。
- `@ModelAttribute`: 绑定请求参数到指定的对象，跟`@RequestBody`差不多。
- `@PathVariable`：获取请求url中的动态参数。
  - 比如请求url：`http://localhost:8080/get/leeyom`。
    ```java
    @RequestMapping(value="get/{name}", method= RequestMethod.GET)
    public User get(@PathVariable String name) {
        User user=new User();
        user.setName(name);
        return user;
    }
    ```
    那么，参数`name`的值为：`leeyom`。
- `@RequestParam`: 接受简单类型的属性，也可以接受对象类型。类似`@requestparam("id")`等价于`request.getParameter("id")`;

## 参数校验

实际请求中除了前端要做参数校验，后台也需要做参数校验，在Spring Boot的`spring-boot-starter-web`库中集成了 `hibernate-validator` 来进行参数校验，常用的校验注解：

- Bean Validation 中内置的 constraint 注解：
  - `@Valid`：用于标识指定的校验对象。
  - `@Null`：被注释的元素必须为 null。
  - `@NotNull`：被注释的元素必须不为 null。
  - `@AssertTrue`：被注释的元素必须为 true。
  - `@AssertFalse`：被注释的元素必须为 false。
  - `@Min(value)`：被注释的元素必须是一个数字，其值必须大于等于指定的最小值。
  - `@Max(value)`：被注释的元素必须是一个数字，其值必须小于等于指定的最大值。
  - `@DecimalMin(value)`：被注释的元素必须是一个数字，其值必须大于等于指定的最小值。
  - `@DecimalMax(value)`：被注释的元素必须是一个数字，其值必须小于等于指定的最大值。
  - `@Size(max, min)`：被注释的元素的大小必须在指定的范围内。
  - `@Digits (integer, fraction)`：被注释的元素必须是一个数字，其值必须在可接受的范围内。
  - `@Past`：被注释的元素必须是一个过去的日期。
  - `@Future`：被注释的元素必须是一个将来的日期。
  - `@Pattern(value)`：被注释的元素必须符合指定的正则表达式。
- Hibernate Validator 附加的 constraint 注解：
  - `@Email`：被注释的元素必须是电子邮箱地址。
  - `@Length`：被注释的字符串的大小必须在指定的范围内。
  - `@NotEmpty`：被注释的字符串的必须非空。
  - `@Range`：被注释的元素必须在合适的范围内。

对User参数进行参数校验：

```java
public class User {
    @NotEmpty(message="姓名不能为空")
    private String name;
    @Max(value = 100, message = "年龄不能大于 100 岁")
    @Min(value= 18 ,message= "必须年满 18 岁！" )
    private int age;
    @NotEmpty(message="密码不能为空")
    @Length(min=6,message="密码长度不能小于 6 位")
    private String pass;
    //...
}
```

Controller里面采用使用 `@Valid + BindingResult`绑定参数和打印校验结果：

```java
@RequestMapping("/saveUser")
public void saveUser(@Valid User user, BindingResult result) {
    System.out.println("user:" + user);
    if (result.hasErrors()) {
        List<ObjectError> list = result.getAllErrors();
        for (ObjectError error : list) {
            System.out.println(error.getCode() + "-" + error.getDefaultMessage());
        }
    }
}
```

## 自定义过滤器

创建一个名为`MyFilter`过滤器，实现Filter，拦截所有的请求，然后打印对应的请求的URL：

```java
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse
    , FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("this is MyFilter,url :" + request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
```
在SSM架构中，需要在`web.xml`文件中配置自定义的过滤器，在Spring Boot中，需要创建一个带`@Configuration`注解的类`MyWebConfiguration.java`，用于注册我们自定义的过滤器：
```java
@Configuration
public class MyWebConfiguration {

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    /**
     * 注册自定义的过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }

}
```

## 读取Properties

在`application.properties`文件中添加如下的属性：

```properties
com.leeyom.title=leeyom
com.leeyom.description=一只有梦想的咸鱼
```

将`application.properties`里面的属性映射到一个配置类中：

```java
@Component
public class LeeyomProperties {

    @Value("${com.leeyom.title}")
    private String title;
    @Value("${com.leeyom.description}")
    private String description;

    //省略getter和setter
}
```
在测试类中打印`application.properties`里面的属性：

```java

@Resource
private LeeyomProperties leeyomProperties;
@Test
public void testProperties() throws Exception {
    System.out.println("title:" + leeyomProperties.getTitle());
    System.out.println("description:" + leeyomProperties.getDescription());
}
```
打印结果：

```properties
title:leeyom
description:一只有梦想的咸鱼
```

## 项目源码

源码参考：[spring-boot-fastweb](https://github.com/wangleeyom/spring-boot-learning/tree/master/spring-boot-fastweb)

# Spring Data JPA 的使用

## JPA简单概念

Spring Data JPA 是 Spring 基于 ORM 框架、JPA 规范的基础上封装的一套 JPA 应用框架，可使开发者用极简的代码即可实现对数据的访问和操作。它提供了包括增删改查等在内的常用功能，且易于扩展。学习并使用 Spring Data JPA 可以极大提高开发效率。

## 快速上手

- 添加相关的依赖：
  ```xml
  <dependency>
    <groupId>org.Springframework.boot</groupId>
    <artifactId>Spring-boot-starter-data-jpa</artifactId>
  </dependency>
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
  </dependency>
  ```
- 配置数据源：
  ```properties
  Spring.datasource.url=jdbc:mysql://localhost:3306/jpa-test
  Spring.datasource.username=root
  Spring.datasource.password=root
  Spring.datasource.driver-class-name=com.mysql.jdbc.Driver

  Spring.jpa.properties.hibernate.hbm2ddl.auto=update
  Spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
  Spring.jpa.show-sql= true
  ```
  - `hibernate.hbm2ddl.auto`：用于：自动创建 | 更新 | 验证数据库表结构
    - `create`：每次加载 hibernate 时都会删除上一次的生成的表，然后根据 model 类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。
    - `create-drop`：每次加载 hibernate 时根据 model 类生成表，但是 sessionFactory 一关闭，表就自动删除。
    - `update`：最常用的属性，第一次加载 hibernate 时根据 model 类会自动建立起表的结构（前提是先建立好数据库），以后加载 hibernate 时根据 model 类自动更新表结构，即使表结构改变了，但表中的行仍然存在，不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等应用第一次运行起来后才会。
    - `validate`：每次加载 hibernate 时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。
  - `dialect`：设置数据库方言，指定生成表名的存储引擎为 InneoDB。
  - `show-sql`：是否打印出自动生产的 SQL，方便调试的时候查看。
- 实体类：实体类中不映射成列的字段得加 `@Transient` 注解，不加注解也会映射成列：
  ```java
  @Entity
  public class User implements Serializable {
      private static final long serialVersionUID = 1L;
      @Id
      @GeneratedValue
      private Long id;
      @Column(nullable = false, unique = true)
      private String userName;
      @Column(nullable = false)
      private String passWord;
      @Column(nullable = false, unique = true)
      private String email;
      @Column(nullable = true, unique = true)
      private String nickName;
      @Column(nullable = false)
      private String regTime;
      //省略 getter settet 方法、构造方法
  }
  ```
- dao层：Dao 只要继承 `JpaRepository` 类就可以使用常用的一些增删改成，对于一些复杂的查询，可以在dao层新增方法进行扩展。
  ```java
  User findByUserName(String userName);
  User findByUserNameOrEmail(String username, String email);
  ```
- 测试：
  ```java
  @Resource
  private UserRepository userRepository;
  @Test
  public void testUserRespository() {

      Date date = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String formattedDate = dateFormat.format(date);

      userRepository.save(new User("leeyom1", "123", "leeyomwang1@qq.com", "Leeyom1", formattedDate));
      userRepository.save(new User("leeyom2", "456", "leeyomwang2@qq.com", "Leeyom2", formattedDate));
      userRepository.save(new User("leeyom3", "789", "leeyomwang3@qq.com", "Leeyom3", formattedDate));

      System.out.println(userRepository.findAll().size());
      System.out.println("Leeyom3".equals(
      userRepository.findByUserNameOrEmail("leeyom3", "leeyomwang3@qq.com").getNickName()));
      userRepository.delete(userRepository.findByUserName("leeyom2"));
      System.out.println(userRepository.findAll().size());

      // 打印后的结果为：
      // 3
      // true
      // 2
  }
  ```

## 基本查询

第一种，使用Spring Data JPA默认预先生成了一些基本的 CURD 的方法，如增、删、改等。只需要我们创建到dao继承`JpaRepository` 类：
```java
public interface UserRepository extends JpaRepository<User, Long> {
}
```
提供的方法有：

```java
@Test
public void testBaseQuery() {
    userRepository.findAll();
    userRepository.findOne(1l);
    userRepository.save(user);
    userRepository.delete(user);
    userRepository.count();
    userRepository.exists(1l);
    // ...
}
```
第二种，Spring Data JPA 提供自定义的简单查询，根据方法名来自动生成 SQL，主要的语法是 findXXBy、readAXXBy、queryXXBy、countXXBy、getXXBy 后面跟属性名称：
```java
User findByUserName(String userName);
User findByUserNameOrEmail(String username, String email);
```

## 复杂查询

可以使用在方法上加注解`@Query`，进行自定义查询，但是需要注意的是，由于Spring Data JPA底层采用的Hibernate，**所以这里的查询语句为HQL，不是sql，需要注意**，比如分页查询：
```java
/**
  * 分页查询
  * @param pageable 分页参数封装
  * @return
  */
 @Query("select u from User u")
 @Override
 Page<User> findAll(Pageable pageable);
```
测试：
```java
@Test
public void testPageQuery() {
    int page=1,size=20;
    Sort sort = new Sort(Sort.Direction.DESC, "id");
    Pageable pageable = new PageRequest(page, size, sort);
    Page<User> userPage = userRepository.findAll(pageable);
    System.out.println("总数量："+userPage.getTotalPages());
    System.out.println(userPage.getContent());
}
```
## 关联查询

- 有时候会有多表关联查询，首先先创建用户详情类：
  ```java
  @Entity
  public class UserDetail implements Serializable {

      private static final long serialVersionUID = 1L;
      @Id
      @GeneratedValue
      private Long id;
      @Column(nullable = false, unique = true)
      private String userId;
      @Column(nullable = true)
      private String address;
      @Column(nullable = true)
      private String hobby;
      //省略 getter settet 方法
  }
  ```
- 定义一个结果集的接口类，接口类的内容来自于用户表和用户详情表：
  ```java
  public interface UserInfo {
      String getUserName();
      String getEmail();
      String getAddress();
      String getHobby();
  }
  ```
- 创建对应的dao，接口的返回类型为UserInfo：
  ```java
  @Query("select u.userName as userName, u.email as email, d.address as address ,
  d.hobby as hobby from User u , UserDetail d " +
      "where u.id=d.userId  and  d.hobby = ?1 ")
  List<UserInfo> findUserInfo(String hobby);
  ```
- 测试：
  ```java
  @Resource
  private UserDetailRepository userDetailRepository;
  @Test
  public void testUserInfo(){
      userDetailRepository.save(new UserDetail("12","Hong Kong","running"));
      List<UserInfo> userInfos=userDetailRepository.findUserInfo("running");
      for (UserInfo userInfo:userInfos){
          System.out.println("addree "+userInfo.getAddress());
      }
  }
  ```
- 打印结果：
  ```
  addree Hong Kong
  ```

## 项目源码

源码参考：[spring-boot-jpa](https://github.com/wangleeyom/spring-boot-learning/tree/master/spring-boot-jpa)

# 模板引擎 Thymeleaf

## 简单上手

- 添加相关的依赖。
  ```xml
  <dependency>
  	<groupId>org.springframework.boot</groupId>
  	<artifactId>spring-boot-starter-thymeleaf</artifactId>
  </dependency>
  <dependency>
  	<groupId>org.springframework.boot</groupId>
  	<artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  ```
- 添加配置文件`application.properties`。
  ```properties
  # 是否启用缓存，生产环境开启，开发环境关闭
  spring.thymeleaf.cache=false
  # 检查当前模板文件是否存在
  spring.thymeleaf.check-template-location=true
  # 设置模板文件的Content-Type
  spring.thymeleaf.content-type=text/html
  # 是否启用thymeleaf模板引擎
  spring.thymeleaf.enabled=true
  # 编码格式
  spring.thymeleaf.encoding=UTF-8
  # 模板文件的模式
  spring.thymeleaf.mode=HTML5
  # 模板文件的路径
  spring.thymeleaf.prefix=classpath:/templates/
  # 模板文件的后缀
  spring.thymeleaf.suffix=.html
  ```
- 创建`controller`控制器`HelloController.java`。
  ```java
  @RequestMapping("/")
   public String helloWorld(ModelMap map) {
       map.addAttribute("message", "http:www.leeyom.top");
       return "index";
   }
  ```
- 创建一个简单的页面`index.html`。
  ```html
  <!DOCTYPE html>
  <html xmlns:th="http://www.w3.org/1999/xhtml">
  <head lang="en">
      <meta charset="UTF-8"/>
      <title>spring boot 模板引擎示例</title>
  </head>
  <body>
  <h1 th:text="${message}">Hello World</h1>
  <!--th:include 和 th:replace 区别，include 只是加载，replace 是替换。-->
  <div th:include="layout/copyright :: copyright"></div>
  <div th:replace="layout/copyright :: copyright"></div>
  </body>
  </html>
  ```
- 启动项目，访问[http://localhost:8080/](http://localhost:8080/)，页面打印：
  ```
  http:www.leeyom.top
  ```

## 常用标签汇总
| 关键字   | 功能介绍 |  案例  |
| :-: | :-: | :-: |
| `th:id` | 替换id   | `<input th:id="'xxx' + ${collect.id}"/>` |
| `th:text` | 文本替换 | `<p th:text="${collect.description}">description</p>` |
| `th:utext` | 支持html的文本替换 | `<p th:utext="${htmlcontent}">conten</p>`  |
| `th:object` | 替换对象 | `<div th:object="${session.user}">` |
| `th:value` | 属性赋值 | `<input th:value="${user.name}" />` |
| `th:with` | 变量赋值运算 | `<div th:with="isEven=${prodStat.count}%2==0"></div>` |
| `th:style` | 设置样式 | `th:style="'display:' + @{(${sitrue} ? 'none' : 'inline-block')} + ''"` |
| `th:onclick` | 点击事件 | `th:onclick="'getCollect()'"` |
| `th:each` |  循环 | `<tr th:each="user,userStat:${users}">` |
| `th:if` | 条件判断 | `<a th:if="${userId == collect.userId}" >` |
| `th:unless` | 和th:if判断相反 | `<a th:href="@{/login}" th:unless=${session.user != null}>Login</a>` |
| `th:href` | 链接地址 | `<a th:href="@{/login}" th:unless=${session.user != null}>Login</a> />` |
| `th:switch` | 多路选择 配合th:case 使用 |  `<div th:switch="${user.role}">` |
| `th:case` | th:switch的一个分支 | `<p th:case="'admin'">User is an administrator</p>` |
| `th:fragment` | 布局标签，定义一个代码片段，方便其它地方引用 | `<div th:fragment="alert">` |
| `th:include` | 布局标签，替换内容到引入的文件 | `<head th:include="layout :: htmlhead" th:with="title='xx'"></head> />` |
| `th:replace` | 布局标签，替换整个标签到引入的文件 | `<div th:replace="fragments/header :: title"></div>` |
| `th:selected` | selected选择框选中 | `th:selected="(${xxx.id} == ${configObj.dd})"` |
| `th:src`  | 图片类地址引入  | `<img class="img-responsive" alt="App Logo" th:src="@{/img/logo.png}" />`  |
| `th:inline` | 定义js脚本可以使用变量 | `<script type="text/javascript" th:inline="javascript">` |
| `th:action` | 表单提交的地址  | `<form action="subscribe.html" th:action="@{/subscribe}">` |
| `th:remove` | 删除某个属性 | `<tr th:remove="all/body/tag/all-but-first/none">`  |
| `th:attr` | 设置标签属性，多个属性可以用逗号分隔 | `th:attr="src=@{/image/aa.jpg},title=#{logo}"` |

## 项目源码

源码参考：[spring-boot-thymeleaf](https://github.com/wangleeyom/spring-boot-learning/tree/master/spring-boot-thymeleaf)

# JPA 和 Thymeleaf 实践

## 多环境配置

在实际开发中，开发环境、测试环境、生产环境对应的数据库，或者其他的端口信息都有不同，在发布新的版本的时候，我们需要将配置文件进行相应的修改，但是有时候我们总会忘记或者记错，这样就会导致线上出问题，所以根据不同的环境可以将属性配置文件切换为开发、测试、生产，分別对应的配置文件为：`application-dev.properties`、`application-test.properties`、`application-prod.properties`。只需要在总的配置文件`application.properties`中将属性配置文件切换为开发环境：
```properties
# 将属性配置文件切换为开发环境
spring.profiles.active = dev
```

## 添加Servlet支持

一个完整的web应用需要添加servlet支持，在spring boot的启动类`Application.java`中继承`SpringBootServletInitializer`类，代码如下：
```java
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
## 效果图
- 用户列表：
  ![用户列表](http://image.leeyom.top/blog/180113/F7L5Lc0F8d.png)
- 新增用户：
  ![新增用户](http://image.leeyom.top/blog/180113/IIfHd81LLJ.png)
- 编辑用户：
  ![编辑用户](http://image.leeyom.top/blog/180113/0DE7GdAaae.png)
- 删除用户：
  ![删除用户](http://image.leeyom.top/blog/180113/FmagcGG1gd.png)

## 项目源码

源码参考：[spring-boot-jpa-thymeleaf](https://github.com/wangleeyom/spring-boot-learning/tree/master/spring-boot-jpa-thymeleaf)

# 使用Swagger2构建RESTful API 文档

## RESTful API设计准则

现在越来越多的项目开始进行前后端分离，那为了方便前后端进行通信，就需要一套 API 准则，RESTful API 是目前比较成熟的一套互联网应用程序的 API 设计理论，RESTful API具体设计如下：

| 请求类型    | URL         |   功能说明           |
| :--------:   | :-----:      |   :----:        |
| GET        | /users      |   查询用户列表       |
| POST       | /users      |   创建一个用户       |
| GET        | /users/id   |   根据id查询一个用户  |
| PUT        | /users/id   |   根据id更新一个用户  |
| DELETE     | /users/id   |   根据id删除一个用户  |

如果对RESTful API设计准则还不是很清楚的话，推荐可以看下阮一峰老师的博文：[《RESTful API 设计指南》](http://www.ruanyifeng.com/blog/2014/05/restful_api.html)，便会对RESTful API 有个大概的了解。

## swagger注解总结

这里就不详述了，我之前有写过总结这些注解的一篇文章，当然也可以参考官方的文档，链接如下：
- [swagger注解总结](http://www.leeyom.top/2017/09/25/tech-swagger-annotation/)
- [Swagger Core Annotations](https://github.com/swagger-api/swagger-core/wiki/Annotations-1.5.X)

## 快速上手

- 添加`swagger2`依赖：
  ```xml
  <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger2</artifactId>
      <version>2.4.0</version>
  </dependency>
  <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger-ui</artifactId>
      <version>2.4.0</version>
  </dependency>
  ```
- 创建`swagger2`配置`SwaggerConfig.java`，设置API接口扫描包、作者、版本等等相关信息：
  ```java
  @Configuration
  @EnableSwagger2
  public class SwaggerConfig {

      @Bean
      public Docket createRestApi() {
          return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                  .apis(RequestHandlerSelectors.basePackage("com.leeyom.controller"))
                  .paths(PathSelectors.any())
                  .build();
      }

      private ApiInfo apiInfo() {
          return new ApiInfoBuilder()
                  .title("Spring Boot中使用Swagger2构建RESTful API")
                  .description("更多Spring Boot相关文章请关注：http://leeyom.top/")
                  .termsOfServiceUrl("http://leeyom.top/")
                  .contact("Leeyom")
                  .version("1.0")
                  .build();
      }
  }  
  ```
- 添加文档：
  ```java
  @Api(description = "用户管理", tags = "UserController", basePath = "/user")
  @RestController
  @RequestMapping(value = "/user")
  public class UserController {
      /**
       * 创建一个线程安全的map，模拟数据库，存放用户信息
       */
      static Map<Long, User> users = new HashMap<>();

      /**
       * 获取用户列表
       * @return
       */
      @ApiOperation(value = "获取用户列表", notes = "获取所有用户信息")
      @RequestMapping(value = "/", method = RequestMethod.GET)
      public List<User> getUserList() {
          List<User> userList = new ArrayList<User>(users.values());
          return userList;
      }

      /**
       * 新增用户
       * @param user 新增用户实体
       * @return
       */
      @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
      @RequestMapping(value = "/", method = RequestMethod.POST)
      public String addUser(@ApiParam(value = "新增用户实体封装", required = true) @RequestBody User user) {
          users.put(user.getId(), user);
          return "success";
      }

      /**
       * 获取用户信息
       * @param id 用户主键id
       * @return
       */
      @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
      @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
      @RequestMapping(value = "/{id}", method = RequestMethod.GET)
      public User getUser(@PathVariable Long id) {
          return users.get(id);
      }

      /**
       * 更新用户
       * @param id   用户id
       * @param user 更新实体封装
       * @return
       */
      @ApiOperation(value = "更新用户详细信息"
      , notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
      @ApiImplicitParams({
              @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"
              , paramType = "path")
      })
      @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
      public String updateUser(@PathVariable Long id, @ApiParam(value = "更新实体封装", required = true)
      @RequestBody User user) {
          User oldUser = users.get(id);
          oldUser.setAge(user.getAge());
          oldUser.setName(user.getName());
          users.put(id, oldUser);
          return "success";
      }

      /**
       * 删除用户
       * @param id 用户id
       * @return
       */
      @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
      @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
      @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
      public String deleteUser(@PathVariable Long id) {
          users.remove(id);
          return "success";
      }
  }
  ```
- 启动Spring Boot程序，访问：[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)，出现如下的结果：
  ![API接口文档](http://image.leeyom.top/blog/180115/c1Ggail8Jh.png)
- SSM架构的项目中，整合swagger可以参考我以前的写一篇博文，就是配置那里有点小区别，其他的没啥区别。
  - [Spring MVC中使用Swagger2构建Restful API](http://www.leeyom.top/2017/09/23/tech-spring-mvc-swagger2/)

## 项目源码

源码参考：[spring-boot-restful-swagger](https://github.com/wangleeyom/spring-boot-learning/tree/master/spring-boot-restful-swagger)

# Spring Boot 集成 MyBatis

## Mybatis原理简介

MyBatis的工作流程如下：
- 首先加载mapper配置的sql映射文件，或者注解相关的sql内容。
- 通过读取配置文件，创建会话工厂 SqlSessionFactory。
- 然后通过会话工厂，创建会话对象 SqlSession，会话对象其实就是一个接口，是对某个数据库增删改查的封装。
- 创建执行器 Executor 。
- 封装sql对象，执行器将待处理的 SQL 信息封装到 MappedStatement 对象中，该对象包括 SQL 语句、输入参数映射信息和输出结果映射信息。
- 正式开始操作数据库，返回操作结果，结束流程。

流程图如下：

![mybatis工作流程图](http://image.leeyom.top/2018012315167174589667.png)

## 官方组件包使用

### XML版本

- 引入mybatis核心依赖：
  ```xml
  <dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.1</version>
  </dependency>
  ```
  除了mybatis的核心依赖，还需要mysql数据库驱动包`mysql-connector-java`等等，这里就不贴出来了，完整的可以看项目源码。
- 配置`application.properties`，主要是配置mybatis和数据库连接：
  ```properties
  # mybatis
  mybatis.config-location=classpath:mybatis/mybatis-config.xml
  mybatis.mapper-locations=classpath:mybatis/mapper/*.xml
  mybatis.type-aliases-package=com.leeyom.pojo

  # dataSource
  spring.datasource.driverClassName=com.mysql.jdbc.Driver
  spring.datasource.url=jdbc:mysql://localhost:3306/spring-boot-mybatis
  ?useUnicode=true&characterEncoding=utf-8
  spring.datasource.username=root
  spring.datasource.password=root  
  ```
  - `mybatis.config-location`：配置 `mybatis-config.xml` 路径，`mybatis-config.xml` 中配置 MyBatis 基础属性。
  - `mybatis.mapper-locations`：配置 Mapper 对应的 XML 文件路径。
  - `mybatis.type-aliases-package`：配置项目中实体类包路径。
  - `spring.datasource.*`：数据源配置。
- 配置 `mybatis-config.xml`：
  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
      <!--配置别名-->
      <typeAliases>
          <typeAlias alias="Integer" type="java.lang.Integer"/>
          <typeAlias alias="Long" type="java.lang.Long"/>
          <typeAlias alias="HashMap" type="java.util.HashMap"/>
          <typeAlias alias="LinkedHashMap" type="java.util.LinkedHashMap"/>
          <typeAlias alias="ArrayList" type="java.util.ArrayList"/>
          <typeAlias alias="LinkedList" type="java.util.LinkedList"/>
      </typeAliases>
  </configuration>  
  ```
  该配置文件的主要作用是配置mybatis的基础属性，比如这里可以设置别名，也还可以设置第三方分页等等。设置别名的好处就是，打个比方在编写mapper.xml的时候
  ```java
  resultType="java.lang.Integer"
  ```
  可以简写为：
  ```java
  resultType="Integer"
  ```
- 配置包扫描器，在spring boot的启动类上面添加注解`@MapperScan("com.leeyom.mapper")`，`com.leeyom.mapper`为mapper接口的package路径。
  ```java
  @SpringBootApplication
  @MapperScan("com.leeyom.mapper")
  public class Application {

      public static void main(String[] args) {
          SpringApplication.run(Application.class, args);
      }
  }  
  ```
- 编写 UserMapper.xml，其实我觉得xml方式的的最大的好处就是可以复写sql，可以将重复使用的sql抽离出来，例如这里我抽离两个sql：
  ```xml
  <!--查询结果封装-->
  <sql id="Base_Column_List">
      id, user_name, password, user_sex, nick_name
  </sql>

  <!--条件查询封装-->
  <sql id="Base_Where_List">
      <if test="userName != null  and userName != ''">
          and user_name = #{userName}
      </if>
      <if test="userSex != null and userSex != ''">
          and user_sex = #{userSex}
      </if>
  </sql>  
  ```
  然后在其他的sql里面进行复用：
  ```xml
  <!--查询所有-->
  <select id="selectAll" resultMap="BaseResultMap">
      select
      <include refid="Base_Column_List"/>
      from users
  </select>


  <!--分页查询-->
  <select id="getUserListByPage" resultMap="BaseResultMap" parameterType="com.leeyom.param.UserParam">
      select
      <include refid="Base_Column_List"/>
      from users
      where 1=1
      <include refid="Base_Where_List"/>
      order by id desc
      limit #{pageNumber} , #{pageSize}
  </select>  
  ```
  是不是简化了sql的代码呢？
- 编写 UserMapper.java：
  ```java
  public interface UserMapper {

      int deleteByPrimaryKey(Integer id);

      int insert(User record);

      User selectByPrimaryKey(Integer id);

      List<User> selectAll();

      int updateByPrimaryKey(User record);

      List<User> getUserListByPage(UserParam userParam);
  }  
  ```

- 这个就是spring boot 集成mybatis的以xml方式操作数据库，虽然说配置要有点多，但是xml的方式可以极大的复用sql，并且可以高度定制化sql，sql语句与java代码解耦，在实际的开发中使用的还是蛮多的。像UserMapper.java、User.java、UserMapper.xml我们都可以使用[mybatis-generator](https://github.com/wangleeyom/mybatis-generator)自动生成，还是挺方便的。

### 注解版本

### 项目源码

## 第三方组件包使用

### 集成分页插件 PageHelper

### 集成通用 Mapper 插件

### 项目源码
