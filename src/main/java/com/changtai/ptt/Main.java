package com.changtai.ptt;

import java.util.List;

/**
 * 启动类
 * @author zhaoct
 * @date 2020-07-20 9:25
 */
public class Main {

    /**
     * 启动方法
     * @param args
     * -n 即requests，用于指定压力测试总共的执行次数
     * -c 即concurrency，用于指定的并发数
     */
    public static void main(String[] args) {
        Request request = new Request();
        request.setUrl(args[args.length-1]);
        for(int i=0; i<args.length; i++){
           if("-n".equals(args[i])){
               request.setRequests(Integer.parseInt(args[i+1]));
           }else if("-c".equals(args[i])){
                request.setConcurrency(Integer.parseInt(args[i+1]));
           }
        }
        Engine engine = new Engine(request);
        List<Result> list = engine.execute();
        Statistics statistics = new Statistics(list);
        System.out.println("平均响应时间：" + statistics.getAvgRT() + " ms ");
        System.out.println("95% 响应时间：" + statistics.get95PRT() + " ms ");
    }
}