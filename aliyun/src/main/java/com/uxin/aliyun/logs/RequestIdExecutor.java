package com.uxin.aliyun.logs;

import com.uxin.aliyun.logs.executor.Executor;

public class RequestIdExecutor implements Executor {
    @Override
    public String welcome() {
        return "全局 RequestId 查询 ";
    }

    @Override
    public void execute() throws Exception {

    }
}
