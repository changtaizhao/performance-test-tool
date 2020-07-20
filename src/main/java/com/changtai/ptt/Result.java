package com.changtai.ptt;

/**
 * 执行结果
 *
 * @author zhaoct
 * @date 2020-07-20 9:33
 */
public class Result implements Comparable<Result> {

    private Long startTime;

    private Long endTime;

    private Long executeTime;

    private int code;

    private Boolean isError;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getExecuteTime() {
        return this.endTime - this.startTime;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Boolean getError() {
        return "200".equals(code);
    }

    @Override
    public int compareTo(Result result) {
        if(this.getExecuteTime() > result.getExecuteTime()) {
            return 1;
        }else if(this.getExecuteTime() < result.getExecuteTime()) {
            return -1;
        }else{
            return 0;
        }
    }
}
