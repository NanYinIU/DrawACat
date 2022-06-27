package com.uxin.aliyun.logs;

import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.response.GetHistogramsResponse;
import com.aliyun.openservices.log.response.GetLogsResponse;
import com.uxin.aliyun.logs.executor.Executor;
import com.uxin.aliyun.logs.handler.cons.AppOption;
import com.uxin.util.ApiSuccessReq;
import javafx.util.Pair;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;


@Component("apiSuccessRateExecutor")
public class ApiSuccessRateExecutor implements Executor {

    private static Client client = new Client("cn-beijing.log.aliyuncs.com","LTAI5tE8c91rSRPseHPRoKxM","w0jzaQ1iiHgPLIwzfyX3xktBBcdg3O");

    private static String project = "hd-live";

    public static void main(String[] args)throws Exception {


        query(ApiSuccessReq.rangeReq(),"/user/h5/account/cancellation",AppOption.KILA);

    }

    public static void query(List<ApiSuccessReq> reqs, String query , AppOption option) throws Exception{

        String infoLogName = "";
        String errorLogName = "";

        if (AppOption.MANB.equals(option)) {
            infoLogName = "hd_manbo_portal_access";
            errorLogName = "hd_manbo_portal_error";
        }

        if(AppOption.KILA.equals(option)){
            infoLogName = "hd_portal_access";
            errorLogName = "hd_portal_live_error";
        }

        if (AppOption.All.equals(option)) {
            infoLogName = "hd_portal_access,hd_manbo_portal_access";
            errorLogName = "hd_portal_live_error,hd_manbo_portal_error";
        }

        if ("".equals(infoLogName) || "".equals(errorLogName)) {
            System.out.println("不支持的类型");
            return;
        }

        System.out.println(query);
        for (ApiSuccessReq req : reqs) {

            long totalCount = 0L , failedCount = 0L , highFailedCount = 0L , highTotalCount = 0L;

            String[] infos = infoLogName.split(",");
            String[] errors = errorLogName.split(",");

            for ( int i = 0 ; i < infos.length ; i++ ) {
                GetHistogramsResponse infoResp = getComplateResp(req.getTotal(), query, infoLogName);
                GetHistogramsResponse errorResp = getComplateResp(req.getFailed(), query, errorLogName);
                GetHistogramsResponse highResp = getComplateResp(req.getHigh(), query, infoLogName);
                GetHistogramsResponse highErrorResp = getComplateResp(req.getHigh(), query, errorLogName);

                totalCount += infoResp.GetTotalCount();
                failedCount += errorResp.GetTotalCount();
                highTotalCount += highResp.GetTotalCount();
                highFailedCount += highErrorResp.GetTotalCount();
            }
            System.out.println(new DateTime(Long.valueOf(req.getTotal().getKey()) * 1000).toString("yyyy-MM-dd HH:mm:ss") + "  -  " + new DateTime(Long.valueOf(req.getTotal().getValue()) * 1000).toString("yyyy-MM-dd HH:mm:ss"));
            System.out.println("请求总量:"  + totalCount + "     失败总量:" + failedCount + "     晚高峰请求总量" + highTotalCount +  "     高峰期失败总量:" + highFailedCount + "     成功率:" + successRate(failedCount,totalCount));

        }
    }

    public static GetHistogramsResponse getComplateResp(Pair<Integer,Integer> pair, String query , String logName) throws Exception{
        GetHistogramsResponse result = null;
        do{
            result = client.GetHistograms(project,logName,pair.getKey(),pair.getValue(),"",query);
            GetLogsResponse getLogsResponse = client.GetLogs(project, logName, pair.getKey(), pair.getValue(), "", query, 100, 0, false);
        }while (result != null && ! result.IsCompleted());
        return result;
    }

    public static String successRate(Long errorCount , Long totalCount){
        if (totalCount.equals(0L)) {
            return "0%";
        }
        BigDecimal error = new BigDecimal(errorCount);
        BigDecimal total = new BigDecimal(totalCount);
        BigDecimal divide = error.divide(total, 5, RoundingMode.CEILING);
        String result = new BigDecimal(1).subtract(divide).multiply(new BigDecimal(100)).toString();
        return result +"%";
    }

    @Override
    public String welcome() {
        return "接口成功率查询(最近一周,包含晚高峰成功率)";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("输入需要查询的 App");
        AppOption.print();
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        AppOption option = AppOption.get(i);
        System.out.println("输入关键词或接口名称");
        String query = "";
        do{
            query+=scanner.nextLine();
        }while (scanner.hasNext());
        query(ApiSuccessReq.rangeReq(),query,option);
    }
}
