package com.mine.junit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
JUnit 4 
Spring Test （Spring框架中的test包）
Spring 相关其他依赖包（不再赘述了，就是context等包）


<dependency>  
            <groupId>junit</groupId>  
            <artifactId>junit</artifactId>  
            <version>4.9</version>  
            <scope>test</scope>  
        </dependency>   
<dependency>  
            <groupId>org.springframework</groupId>  
            <artifactId>spring-test</artifactId>  
            <version> 3.2.4.RELEASE  </version>  
            <scope>provided</scope>  
        </dependency>   

**/

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration
//({"/spring/app*.xml","/spring/service/app*.xml"}) //加载配置文件
  
//------------如果加入以下代码，所有继承该类的测试类都会遵循该配置，也可以不加，在测试类的方法上///控制事务，参见下一个实例  
//这个非常关键，如果不加入这个注解配置，事务控制就会完全失效！  
//@Transactional  
//这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），同时//指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！  
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)  
//------------  
public class BaseTest {
}  
