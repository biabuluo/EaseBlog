# EaseBlog后端开发记录
Separation of front and back 

基于对三更草堂老师的前后端分离项目复刻

> 技术栈：
>
> - SpringBoot
> - MybatisPlus
> - SpringSequrity
> - EasyExcel
> - Swagger2
> - Redis
> - Echarts
> - Vue
> - ElementUI



## Swagger2使用小记录

### 快速入门

启动项目后访问：http://localhost:yourPort/swagger-ui.html

1. 添加依赖

   ```xml
   <dependency>
       <groupId>io.springfox</groupId>
       <artifactId>springfox-swagger2</artifactId>
       <version>2.9.2</version>
   </dependency>
   <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
   <dependency>
       <groupId>io.springfox</groupId>
       <artifactId>springfox-swagger-ui</artifactId>
       <version>2.9.2</version>
   </dependency>
   ```

2. 配置swagger

   启动类中添加注解@EnableSwagger2
   
   ```java
   @SpringBootApplication
   @EnableScheduling
   @EnableSwagger2
   public class EaseBlogApplication {
       public static void main(String[] args) {
           SpringApplication.run(EaseBlogApplication.class);
       }
   }
   
   ```





### 细节配置

#### Controller信息配置

> **@Api注解**
>
> 属性：tags（设置标签） description（设置描述信息）

#### 接口信息配置

> **@ApiOperation**
>
> 属性：value（接口标签）notes（接口描述信息）

#### 接口参数信息配置

> @ApiImplicitParams(
>
> ​	@ApiImplicitParam(name="变量名", value = "描述 ")
>
> ​	...
>
> )

#### 实体类配置

> @ApiModel描述实体类
>
> ​	属性：description
>
> @ApiModelProperty描述实体属性
>
> ​	属性：notes

#### 文档信息配置

> **使用swagger配置类**
>
> 



## 后端架构

> 前后台系统：使用多模块架构设计，减少代码冗余

### 后端依赖配置

#### 创建父模块

> - 父模块就不需要在src里创建文件，只需要配置pom
>
>   - 配置JDK版本
>
>   - 文件编码
>
> ```xml
>   <properties>
>     <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
>     <java.version>1.8</java.version>
>   </properties>
> ```
>
> - 依赖的版本控制
>   - 使用dependencymanagement可以对所有子模块的版本控制
>   - 里边的dependences并没有添加，而是锁定
> ```xml
> <!--依赖的控制-->
> <dependencyManagement>
>   <dependencies>
> <!--      springboot-->
>     <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-dependencies -->
>     <dependency>
>       <groupId>org.springframework.boot</groupId>
>       <artifactId>spring-boot-dependencies</artifactId>
>       <version>2.5.0</version>
>       <type>pom</type>
>       <scope>import</scope>
>     </dependency>
> <!--      fastjson-->
>     <!-- https://mvnrepository.com/artifact/com.alibaba.fastjson2/fastjson2 -->
>         <dependency>
>             <groupId>com.alibaba</groupId>
>             <artifactId>fastjson</artifactId>
>             <version>1.2.33</version>
>         </dependency>
> <!--      jwt-->
>     <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt -->
>     <dependency>
>       <groupId>io.jsonwebtoken</groupId>
>       <artifactId>jjwt</artifactId>
>       <version>0.9.0</version>
>     </dependency>
> <!--      mybatis plus-->
>     <!-- https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-boot-starter -->
>     <dependency>
>       <groupId>com.baomidou</groupId>
>       <artifactId>mybatis-plus-boot-starter</artifactId>
>       <version>3.4.3</version>
>     </dependency>
> <!--      aliyun OSS-->
>     <!-- https://mvnrepository.com/artifact/com.aliyun.oss/aliyun-sdk-oss -->
>     <dependency>
>       <groupId>com.aliyun.oss</groupId>
>       <artifactId>aliyun-sdk-oss</artifactId>
>       <version>3.10.2</version>
>     </dependency>
> <!--easyexcel-->
>     <!-- https://mvnrepository.com/artifact/com.alibaba/easyexcel -->
>     <dependency>
>       <groupId>com.alibaba</groupId>
>       <artifactId>easyexcel</artifactId>
>       <version>3.0.5</version>
>     </dependency>
> <!--      swagger-->
>     <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
>     <dependency>
>       <groupId>io.springfox</groupId>
>       <artifactId>springfox-swagger2</artifactId>
>       <version>2.9.2</version>
>     </dependency>
>     <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
>     <dependency>
>       <groupId>io.springfox</groupId>
>       <artifactId>springfox-swagger-ui</artifactId>
>       <version>2.9.2</version>
>     </dependency>
>   </dependencies>
> </dependencyManagement>
> ```
>
> - JDK版本控制-使用配置好的变量
> ```xml
>   <build>
>     <plugins>
>       <plugin>
>         <groupId>org.apache.maven.plugins</groupId>
>         <artifactId>maven-compiler-plugin</artifactId>
>         <version>3.1</version>
>         <configuration>
>           <source>${java.version}</source>
>           <target>${java.version}</target>
>           <encoding>${project.build.sourceEncoding}</encoding>
>         </configuration>
>       </plugin>
>     </plugins>
>   </build>
> 
> ```
>
> - 模块聚合控制（创建子模块会自动添加）
> ```xml
>     <!--  模块聚合控制-->
>     <modules>
>         <module>EaseBlog-public</module>
>     </modules>
> ```
>

####  创建公共子模块

> ```xml
> <!--    依赖-->
>     <dependencies>
> <!--        springboot-->
>         <dependency>
>             <groupId>org.springframework.boot</groupId>
>             <artifactId>spring-boot-starter-web</artifactId>
>         </dependency>
> <!--        lombk-->
>         <dependency>
>             <groupId>org.projectlombok</groupId>
>             <artifactId>lombok</artifactId>
>             <optional>true</optional>
>         </dependency>
> <!--        junit-->
>         <dependency>
>             <groupId>org.springframework.boot</groupId>
>             <artifactId>spring-boot-starter-test</artifactId>
>             <scope>test</scope>
>         </dependency>
> <!--        springsecurity启动器-->
>         <dependency>
>             <groupId>org.springframework.boot</groupId>
>             <artifactId>spring-boot-starter-security</artifactId>
>         </dependency>
> <!--        redis依赖-->
>         <dependency>
>             <groupId>org.springframework.boot</groupId>
>             <artifactId>spring-boot-starter-data-redis</artifactId>
>         </dependency>
> <!--        fastjson-->
>         <dependency>
>             <groupId>com.alibaba</groupId>
>             <artifactId>fastjson</artifactId>
>         </dependency>
> <!--        jwt-->
>         <dependency>
>             <groupId>io.jsonwebtoken</groupId>
>             <artifactId>jjwt</artifactId>
>         </dependency>
> <!--        mybatisplus-->
>         <dependency>
>             <groupId>com.baomidou</groupId>
>             <artifactId>mybatis-plus-boot-starter</artifactId>
>         </dependency>
> <!--        mysql数据库驱动-->
>         <dependency>
>             <groupId>mysql</groupId>
>             <artifactId>mysql-connector-java</artifactId>
>         </dependency>
> <!--        OSS-->
>         <dependency>
>             <groupId>com.aliyun.oss</groupId>
>             <artifactId>aliyun-sdk-oss</artifactId>
>         </dependency>
> <!--        AOP-->
>         <dependency>
>             <groupId>org.springframework.boot</groupId>
>             <artifactId>spring-boot-starter-aop</artifactId>
>         </dependency>
> <!--        easyexcel-->
>         <dependency>
>             <groupId>com.alibaba</groupId>
>             <artifactId>easyexcel</artifactId>
>         </dependency>
> <!--        swagger-->
>         <dependency>
>             <groupId>io.springfox</groupId>
>             <artifactId>springfox-swagger2</artifactId>
>         </dependency>
>         <dependency>
>             <groupId>io.springfox</groupId>
>             <artifactId>springfox-swagger-ui</artifactId>
>         </dependency>
>     </dependencies>
> ```
>
> 



####  前台模块

依赖公共模块

> ```xml
> <!--   依赖-->
>     <dependencies>
>         <dependency>
>             <groupId>com.biabuluo</groupId>
>             <artifactId>EaseBlog-public</artifactId>
>             <version>1.0-SNAPSHOT</version>
>         </dependency>
>     </dependencies>
> ```



####  后台模块

依赖公共模块

> ```xml
>     <dependencies>
> <!--        添加公共模块-->
>         <dependency>
>             <groupId>com.biabuluo</groupId>
>             <artifactId>EaseBlog-public</artifactId>
>             <version>1.0-SNAPSHOT</version>
>         </dependency>
>     </dependencies>
> ```



### 系统前台

#### 准备工作

##### 1. Springboot和mybatisPlus整合配置测试

> 1. 创建启动类
>
> 2. 创建application.yml配置文件
>
>    ```yml
>    server:
>      port: 7777
>    spring:
>      datasource:
>        url: jdbc:mysql://localhost:3306/easeblog?characterEncoding=utf-8&serverTimezones=Asia/Shanghai
>        username: root
>        password: 2020101642
>        driver-class-name: com.mysql.cj.jdbc.Driver
>      servlet:
>        multipart:
>          max-file-size: 2MB
>          max-request-size: 5MB
>    mybatis-plus:
>      configuration:
>        # 日志
>        log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
>      global-config:
>        db-config:
>          logic-delete-field: delFlag
>          logic-delete-value: 0
>          logic-not-delete-value: 0
>          id-type: auto
>                                           
>    ```
>
> 
>
> 3. SQL语句
>
>    使用easycode插件生成代码
>
> 4. 创建实体类
>
> 5. 创建Controller测试接口
>





##### 2. 其它一些工具类和配置

>
>7. 后端解决跨域问题（其它笔记上有）
>
>8. 项目常量类+响应类+响应码code枚举类
>
>   项目常量类（正式项目中不允许出现数字）
>
>   ```java
>   public class SystemConstants {
>       //文章草稿
>       public static final int ARTICLA_ATATUS_DRAFT = 1;
>       //正常发布文章
>       public static final int ARTICLE_STATUS_NOMAL = 0;
>       //查询热门文章页码
>       public static final int HOTARTICLE_PAGE = 1;
>       //查询热门文章数量
>       public static final int HOTARTICLE_NUM = 10;
>
>   }
>   ```
>
>   响应类升级版
>
>   ```java
>   @JsonInclude(JsonInclude.Include.NON_NULL)
>   public class ResponseResult<T> implements Serializable {
>       private Integer code;
>       private String msg;
>       private T data;
>
>       public ResponseResult() {
>           this.code = AppHttpCodeEnum.SUCCESS.getCode();
>           this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
>       }
>
>       public ResponseResult(Integer code, T data) {
>           this.code = code;
>           this.data = data;
>       }
>
>       public ResponseResult(Integer code, String msg, T data) {
>           this.code = code;
>           this.msg = msg;
>           this.data = data;
>       }
>
>       public ResponseResult(Integer code, String msg) {
>           this.code = code;
>           this.msg = msg;
>       }
>
>       public static ResponseResult errorResult(int code, String msg) {
>           ResponseResult result = new ResponseResult();
>           return result.error(code, msg);
>       }
>       public static ResponseResult okResult() {
>           ResponseResult result = new ResponseResult();
>           return result;
>       }
>       public static ResponseResult okResult(int code, String msg) {
>           ResponseResult result = new ResponseResult();
>           return result.ok(code, null, msg);
>       }
>
>       public static ResponseResult okResult(Object data) {
>           ResponseResult result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getMsg());
>           if(data!=null) {
>               result.setData(data);
>           }
>           return result;
>       }
>
>       public static ResponseResult errorResult(AppHttpCodeEnum enums){
>           return setAppHttpCodeEnum(enums,enums.getMsg());
>       }
>
>       public static ResponseResult errorResult(AppHttpCodeEnum enums, String msg){
>           return setAppHttpCodeEnum(enums,msg);
>       }
>
>       public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums){
>           return okResult(enums.getCode(),enums.getMsg());
>       }
>
>       private static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums, String msg){
>           return okResult(enums.getCode(),msg);
>       }
>
>       public ResponseResult<?> error(Integer code, String msg) {
>           this.code = code;
>           this.msg = msg;
>           return this;
>       }
>
>       public ResponseResult<?> ok(Integer code, T data) {
>           this.code = code;
>           this.data = data;
>           return this;
>       }
>
>       public ResponseResult<?> ok(Integer code, T data, String msg) {
>           this.code = code;
>           this.data = data;
>           this.msg = msg;
>           return this;
>       }
>
>       public ResponseResult<?> ok(T data) {
>           this.data = data;
>           return this;
>       }
>
>       public Integer getCode() {
>           return code;
>       }
>
>       public void setCode(Integer code) {
>           this.code = code;
>       }
>
>       public String getMsg() {
>           return msg;
>       }
>
>       public void setMsg(String msg) {
>           this.msg = msg;
>       }
>
>       public T getData() {
>           return data;
>       }
>
>       public void setData(T data) {
>           this.data = data;
>       }
>   }
>   ```
>
>   响应码枚举类
>
>   ```java
>   public enum AppHttpCodeEnum {
>       // 成功
>       SUCCESS(200,"操作成功"),
>       // 登录
>       NEED_LOGIN(401,"需要登录后操作"),
>       NO_OPERATOR_AUTH(403,"无权限操作"),
>       SYSTEM_ERROR(500,"出现错误"),
>       USERNAME_EXIST(501,"用户名已存在"),
>       PHONENUMBER_EXIST(502,"手机号已存在"),
>       EMAIL_EXIST(503, "邮箱已存在"),
>       REQUIRE_USERNAME(504, "必需填写用户名"),
>       CONTENT_NOT_NULL(506, "评论内容不能为空"),
>       FILE_TYPE_ERROR(507, "文件类型错误，请上传png文件"),
>       USERNAME_NOT_NULL(508, "用户名不能为空"),
>       NICKNAME_NOT_NULL(509, "昵称不能为空"),
>       PASSWORD_NOT_NULL(510, "密码不能为空"),
>       EMAIL_NOT_NULL(511, "邮箱不能为空"),
>       NICKNAME_EXIST(512, "昵称已存在"),
>       LOGIN_ERROR(505,"用户名或密码错误");
>       int code;
>       String msg;
>
>       AppHttpCodeEnum(int code, String errorMessage){
>           this.code = code;
>           this.msg = errorMessage;
>       }
>
>       public int getCode() {
>           return code;
>       }
>
>       public String getMsg() {
>           return msg;
>       }
>   }
>   ```
>
>9. Bean拷贝工具类（实现vo优化）
>
>   ```java
>   public class BeanCopyUtil {
>       private BeanCopyUtil(){}
>   
>       //实现list对象拷贝
>       public static <V, E> List<V> copyBeanList(List<E> list, Class<V> clazz){
>           //使用流
>           return list.stream()
>                   .map(o->copyBean(o, clazz))
>                   .collect(Collectors.toList());
>       }
>   
>   
>       //实现单个对象拷贝
>       public static<V> V copyBean(Object source, Class<V> clazz){
>           V result = null;
>           //创建目标对象
>           try {
>               result = clazz.getDeclaredConstructor().newInstance();
>               //实现copy
>               BeanUtils.copyProperties(source, result);
>   
>           } catch (Exception e) {
>               throw new RuntimeException(e);
>           }
>           return result;
>       }
>   }
>   ```
>



##### 3. fastjson配置（mvc）

```java
@Bean
public HttpMessageConverter fastJsonHttpMessageConverters(){
    //需要定义一个Convert转换消息对象
    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
    fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

    SerializeConfig.globalInstance.put(Long.class, ToStringSerializer.instance);

    fastJsonConfig.setSerializeConfig(SerializeConfig.globalInstance);
    fastConverter.setFastJsonConfig(fastJsonConfig);
    HttpMessageConverter<?> converter = fastConverter;
    return converter;
}

@Override
public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(fastJsonHttpMessageConverters());
}
```







#### 热门文章列表功能

##### 需求

> - 可查询浏览量前十的文章信息：展示标题+浏览量
> - 按浏览量降序排序
> - 能点击跳转
> - 注意不能将草稿文章展示（status=0）



#### 查询分类需求

##### 需求

> - 页面需要展示分类列表
>
> - 可点击具体分类查询分类下文章列表
> - 只有展示有发布正式文章分类
> - 非禁用分类status=0



#### 分页查询文章列表

##### 需求

> - 在首页和分类页面都需要查询文章列表
> - 查询所有文章列表
> - 分类页面：查询对应分类下的文章
> - 要求：只能查询正式发布文章
> - 要求：置顶文章要现实在最前面

##### 注意

> 分页查询需要一个PageVo，封装rows和total字段



#### 文章详情功能

##### 需求

> - 要求文章列表点击阅读全文能够跳转到文章详情页面，可以让用户阅读正文
>
> - 要求文章详情中展示分类名
> - 接口设计使用路径参数



#### 友链查询功能

##### 需求

> - 在友链页面查询出所有的审核通过的友链
> - 接口设计：/link/getAllLink



#### 登录功能实现（超级重要！！！）

##### 需求

> - 前台后台统一使用SpringSecurity安全框架来实现（注意前后台的鉴权不同，必须分离配置）
> - 需要实现登录基本功能
> - 有些功能必须登录之后才能使用，未登录状态不能使用
> - 接口设计：/login

##### 设计过程

###### 登录功能

> 1. 自定义登录接口
>    - 调用ProviderManager的方法进行认证，成功返回jwt
>    - 把用户信息存入redis
> 2. 自定义UserDetailsService
>    - 查询数据库返回用户的一个UserDetail对象
> 3. 配置passwordEncoder为BCryptPasswordEncoder

###### 校验功能

> 1. 定义jwt认证过滤器
>    - 获取token
>    - 解析token获取其中Userid
>    - 从redis中获取用户信息
>    - 存入SecurityContextHolder

###### 认证授权失败处理

> 自定义异常处理
>
> - AuthenticationEntryPoint认证失败处理器
> - AccessDeniedHandler授权失败处理器
> - 配置给security



##### 准备工作

###### 依赖引入

> security+jwt+fastjson+redis

###### 工具类和相关配置类

> - redis序列化器+redis配置类
>
> - jwt工具类
> - redisCache封装类
> - WebUtils：向响应体书写





#### 统一异常处理

> 在controller层也需要做许多判断校验，可以使用抛出异常的方式来对异常统一处理
>
> 把异常中的信息封装给ResponseResult响应给前端

处理细节：

> - 创建一个系统异常继承RunTimeException
> - 构造时可以传入异常枚举类
> - 抛出异常可以统一处理：创建GlobalExceptionHandler封装成ResponseResult返回





#### 退出登录功能设计

##### 需求

> - post
> - /logout
> - 请求头：需要token请求头





#### 评论列表功能

##### 需求

> - 接口：/comment/getCommentList
> - 不需要请求头+token
> - 评论有分页需求
> - 评论最多两级
> - 请求参数封装：querystring类型：articleid+pagenum+pagesize

##### 响应格式（重要）

> rows：{articleId
>
> +children
>
> +contents
>
> +createBy
>
> +createTime
>
> +id+
>
> rootId（-1）
>
> +toCommentId（根评论-1）
>
> +toCommentUserId
>
> +username
>
> }
>
> total





#### 发送评论接口功能

##### 需求

> - 用户登录之后才能够发表评论+对评论回复 需要token
> - 也可以在友链页面进行评论
> - 接口设计：/comment/addComment post
> - 请求参数：articleId+type+rootId+toCommentId+toCommentUserId+content
> - 响应格式：code+msg

##### 注意

> 可以添加一个securityUtil类获取securityContext中userid





##### 友链评论列表

##### 需求

> - /comment/getLinkCommentList
> - 不需要token





#### 个人信息查询接口

##### 需求

> - get：/user/getUserInfo
> - 需要token
> - 响应参数：avatar、email、id、nickname、sex



#### 头像上传接口

##### 注意

> 一般来说，企业级不会将对象直接存储到web服务器中
>
> 而是使用OSS对象存储服务

##### 过程

> - 添加七牛云对象存储的依赖
>
>   ```java
>   <dependency>
>     <groupId>com.qiniu</groupId>
>     <artifactId>qiniu-java-sdk</artifactId>
>     <version>[7.13.0, 7.13.99]</version>
>   </dependency>
>   ```
>
>  - 使用服务器直传
>
>  -  查看文档

##### 需求

> - 请求方式：POST 请求地址：/upload
> - 需要token
> - 请求头：Content-Type：mutipart/form-data
> - 传递参数：MultipartFile img





#### 更新个人信息接口

##### 需求

> - 编辑完个人信息后点击保存会更新个人信息
> - 接口设计：PUT：/user/setUserInfo
> - 需要token
> - 请求体参数：avatar+email+id+nickName+sex







#### 用户注册

##### 需求

> - 要求用户名，昵称，邮箱不能和数据库中原有数据重复
> - 重复有对应提示
> - 要求用户名，密码，昵称，邮箱不能为空
> - 注意密码必须明文存储到数据库中
> - 请求方式：POST：/user/register
> - 不需要token
> - email+nickName+password+userName



#### 博客浏览量功能需求

##### 需求

> 在用户浏览博客时实现对应博客浏览量新增
>
> - 请求方式：PUT：/article/updateViewCount/{id}

##### 分析

> 并发量大时如何配置：
>
> 解决方案：使用redis作为缓存
>

##### 需要解决问题

> - 在应用启动时把博客的浏览量存储到redis中
> - 更新浏览量时去更新redis中的数据
> - 每隔10分钟把redis中浏览量更新到数据库中（使用定时任务）
> - 访问浏览量：直接读取redis（解决写锁问题）

##### CommandLineRunner接口在应用启动时预处理

> 该接口会在程序的所有bean加载完全之后再进行调用
>
> 记得@Component





##### 定时任务

> **实现步骤**（指定执行什么代码-什么时间进行）
>
> **Spring框架提供的**
>
> 1. 使用@EnableScheduling注解开启定时任务功能（在启动类上添加）
>
> 2. 确定定时任务执行代码，并配置任务执行时间
>
>    - 新建job包
>
>    - 确定任务
>
>      ```java
>      @Component
>      public class Testjob{
>          //涉及cron表达式
>          @Scheduled(cron = "0/5 * * * * ?")
>          public void testJob(){
>          	//要执行的代码
>      	}
>      }
>      ```



### 系统后台















## 辅助设计

### AOP实现日志记录

#### 需求

> 需要通过日志记录接口调用信息。便于后期调试排查。
>
> 并且可能有很多接口进行日志记录
>
> 接口被调用时日志打印格式如下：
>
> URL+BusinessName+HttpMethod+ClassMethod+IP+RequestArgs+Response

#### 设计

> - aop依赖
>
> - 定义注解
>
> - 定义切面类
>
>   获取request：
>
>   ```java
>   ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
>   HttpServletRequest request = requestAttributes.getRequest();
>   ```
>
> - 使用注解









### cron表达式

cron表达式是用来设置定时任务执行时间的表达式

> - cron表达式由七个部分构成（spring6个部分）
> - 秒（0-59）分 小时 日期 月期 月份 星期 年份
> - 通用特殊字符：,/*-
> - *任意符
> - ,定义列表
> - -表示范围
> - /表示每隔多少时间

> **cron表达式特殊字符**
>
> - 日期：？L（表示最后） W（当月最接近的工作日）
> - 星期：？L #（表示第几个星期五）
