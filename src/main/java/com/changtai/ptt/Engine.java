package com.changtai.ptt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 模拟请求类
 * @author zhaoct
 * @date 2020-07-20 9:25
 */
public class Engine {

    /**
     * 请求
     */
    private Request request = null;

    /**
     * 所有线程都准备好之后，再发起请求，防止线程启动时间带来误差
     */
    private CountDownLatch startCountDownLatch = null;

    /**
     * 等待所有线程执行完成后返回结果
     */
    private CountDownLatch endCountDownLatch = null;


    public Engine(Request request){
        this.request = request;
        startCountDownLatch = new CountDownLatch(request.getConcurrency());
        endCountDownLatch = new CountDownLatch(request.getConcurrency());
    }

    public List<Result> execute(){
        List<Result> list = Collections.synchronizedList(new ArrayList<>());
        for(int i=0; i<request.getConcurrency(); i++){
            //线程只需要执行一次任务，不需要创建线程池
            new Thread(
                    () -> {
                        startCountDownLatch.countDown();
                        try {
                            //等所有线程都启动了在执行,模拟并发
                            startCountDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        for(int j=0; j<request.getRequests(); j++){
                            int code = 0;
                            long startTime = 0;
                            long endTime = 0;
                            try {
                                startTime = System.currentTimeMillis();
                                code = HttpUtil.doGet(request.getUrl());
                                endTime = System.currentTimeMillis();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }finally {
                                Result result = new Result();
                                result.setCode(code);
                                result.setStartTime(startTime);
                                result.setEndTime(endTime);
                                list.add(result);
                            }
                        }
                        endCountDownLatch.countDown();
                    }
            ).start();
        }
        //所有线程都执行完了返回结果
        try {
            endCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

}
