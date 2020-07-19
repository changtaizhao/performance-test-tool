package com.changtai.ptt;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class Engine {

    private Request request;
    private ExecutorService executorService = null;
    private CountDownLatch countDownLatch = null;

    public Engine(Request request){
        this.request = request;
        countDownLatch = new CountDownLatch(request.getConcurrency());
        //executorService = new ThreadPoolExecutor()
    }

    public void init(){


    }

    public void run(){
        for(int i=0; i<request.getConcurrency(); i++){
            new Thread(
                    () -> {
                        int code = 0;
                        try {
                            countDownLatch.countDown();
                            countDownLatch.await();
                            for(int j=0; j<request.getRequests(); j++){
                                code = HttpUtil.doGet(request.getUrl());
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {

                        }
                    }
            ).start();
        }
    }

}
