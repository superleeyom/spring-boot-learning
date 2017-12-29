# 目录

- [x] [Hello World](#hello-world)
- [ ] 快速体验 Web 开发
- [ ] Spring Data JPA 的使用
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
            mockMvc.perform(MockMvcRequestBuilders.post("/hello?name=小明").accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
        }
    }
    ```
