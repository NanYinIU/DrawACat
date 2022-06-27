package com.uxin.aliyun.logs;
import com.uxin.aliyun.logs.executor.Executor;
import com.uxin.aliyun.logs.executor.ExecutorManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 * https://help.aliyun.com/document_detail/29014.html
 *
 */
public class QueryLogApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-main.xml");
        context.start();
        ExecutorManager bean = context.getBean(ExecutorManager.class);
        bean.print();
    }
}
