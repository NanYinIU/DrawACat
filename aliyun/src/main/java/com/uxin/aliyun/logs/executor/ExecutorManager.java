package com.uxin.aliyun.logs.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;


@Component("executorManager")
public class ExecutorManager {
    @Autowired
    private List<Executor> executors;

    public void print(){
        for(;;){
            System.out.println("欢迎使用日志查询工具");
            System.out.println("查询结果都是精确结果");
            System.out.println("wiki:");
            int i = 1 ;
            for (Executor executor : executors) {
                System.out.print(i+" )");
                System.out.println(executor.welcome());
                i++;
            }
            System.out.print("输入需要使用的功能 : ");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();
            try {
                executors.get(index - 1).execute();
            } catch (Exception e) {
                System.out.println("错误" + e.getMessage());
            }
        }
    }
}
