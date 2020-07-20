package com.changtai.ptt;

import java.util.Collections;
import java.util.List;

/**
 * 统计数据类
 * @author zhaoct
 * @date 2020-07-20 9:25
 */
public class Statistics {

    private List<Result> list = null;

    public Statistics(List<Result> list){
        this.list = list;
    }

    public long getAvgRT(){
        long sumRT = 0;
        for(Result result : list){
            sumRT += result.getExecuteTime();
        }
        return sumRT / list.size();
    }

    public long get95PRT(){
        //排序
        Collections.sort(list);

        //95分位值
        return list.get((int)(list.size() * 95 / 100) - 1).getExecuteTime();
    }


}
