package com.uxin.aliyun.logs;

import com.sun.tools.javac.util.Pair;
import com.uxin.aliyun.logs.executor.Executor;
import com.uxin.aliyun.logs.handler.AbstractHander;
import com.uxin.aliyun.logs.handler.HandlerManager;
import com.uxin.aliyun.logs.handler.cons.AppOption;
import com.uxin.aliyun.logs.handler.cons.TimeOption;
import com.uxin.aliyun.logs.handler.cons.TypeOption;
import com.uxin.util.PageParam;
import com.uxin.util.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;


@Component("assignLogQuery")
public class AssignLogQuery implements Executor {

    @Autowired
    private HandlerManager handlerManager;


    @Override
    public String welcome() {
        return "指定 日志/App 范围查询";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("请选择 App");
        AppOption.print();
        Scanner scanner = new Scanner(System.in);
        AppOption appOption = AppOption.get(scanner.nextInt());

        if (appOption == null) {
            System.out.println("选择无效返回上级");
            return;
        }

        System.out.println("请选择类型");
        TypeOption.print();
        TypeOption typeOption = TypeOption.get(scanner.nextInt());

        if (typeOption == null) {
            System.out.println("选择无效返回上级");
            return;
        }

        List<AbstractHander> selection = handlerManager.select(appOption, typeOption);

        if (selection == null || selection.size() < 1) {
            System.out.println("选择无效返回上级");
            return;
        }

        System.out.println("输入时间范围");
        TimeOption.print();
        Pair<Integer, Integer> fromTo = TimeOption.get(scanner.nextInt());

        System.out.println("输入关键词");
        String query = scanner.next();
        PageParam pageParam = new PageParam(fromTo.fst,fromTo.snd,query);

        QueryResult result = new QueryResult();
        for (AbstractHander hander : selection) {
            hander.logQuery(pageParam,result);
        }

        result.print();
    }
}
