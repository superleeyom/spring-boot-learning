# 目录

- [x] [Hello World](#hello-world)
  - [x] [官方推荐目录](#官方推荐目录)
  - [x] [热部署](#热部署)
  - [x] [实例中涉及到的注解](#实例中涉及到的注解)
- [x] [快速Web开发](#快速web开发)
  - [x] [Json的支持](#json的支持)
  - [x] [请求传参](#请求传参)
  - [x] [参数校验](#参数校验)
  - [x] [自定义过滤器](#自定义过滤器)
  - [x] [读取Properties](#读取properties)
- [x] [Spring Data JPA 的使用](#spring-data-jpa-的使用)
  - [x] [JPA简单概念](#jpa简单概念)
  - [x] [快速上手](#快速上手)
  - [x] [基本查询](#基本查询)
  - [x] [复杂查询](#复杂查询)
  - [x] [关联查询](#关联查询)
- [ ] 模板引擎 Thymeleaf
- [ ] JPA 和 Thymeleaf 实践
- [ ] 使用Swagger2构建RESTful API 文档
- [ ] Spring Boot 集成 MyBatis
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

# <span id="hello-world">Hello World</span>

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

```
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

```
title:leeyom
description:一只有梦想的咸鱼
```

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
  ```
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
