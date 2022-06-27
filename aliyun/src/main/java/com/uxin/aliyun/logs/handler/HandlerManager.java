package com.uxin.aliyun.logs.handler;


import com.uxin.aliyun.logs.handler.access.AccessHandler;
import com.uxin.aliyun.logs.handler.cons.AppOption;
import com.uxin.aliyun.logs.handler.cons.TypeOption;
import com.uxin.aliyun.logs.handler.error.ErrorHandler;
import com.uxin.aliyun.logs.handler.info.InfoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("handlerManager")
public class HandlerManager {

    @Autowired
    private List<AbstractHander> handlers;

    public List<AbstractHander> select(AppOption appOption , TypeOption typeOption){
        return  selectType(selectApp(handlers,appOption),typeOption);
    }

    private List<AbstractHander> selectApp(List<AbstractHander> handlers ,AppOption option){

        if (option == null || AppOption.All.equals(option)) {
            return handlers;
        }
        List<AbstractHander> result = new ArrayList<>();

        for (AbstractHander h : handlers) {
            if (AppOption.KILA.equals(option) && h instanceof Kila) {
                result.add(h);
            }
            if (AppOption.MANB.equals(option) && h instanceof Manbo) {
                result.add(h);
            }
            if (AppOption.SERV.equals(option) && h instanceof Service) {
                result.add(h);
            }
        }

        return result;
    }

    private List<AbstractHander> selectType(List<AbstractHander> handlers ,TypeOption option ){
        if (option == null || TypeOption.All.equals(option)) {
            return handlers;
        }
        List<AbstractHander> result = new ArrayList<>();

        for (AbstractHander h : handlers) {
            if (TypeOption.INFO.equals(option) && h instanceof InfoHandler) {
                result.add(h);
            }
            if (TypeOption.ERROR.equals(option) && h instanceof ErrorHandler) {
                result.add(h);
            }
            if (TypeOption.ACCESS.equals(option) && h instanceof AccessHandler) {
                result.add(h);
            }
        }

        return result;
    }
}
