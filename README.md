# EaseBlog
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
>    ```java
>    @SpringBootApplication
>    public class EaseBlogApplication {
>        public static void main(String[] args) {
>            SpringApplication.run(EaseBlogApplication.class, args);
>        }
>    }
>    ```
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
> 4. 创建实体类
>
> 5. 创建Controller测试接口
