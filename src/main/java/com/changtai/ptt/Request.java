package com.changtai.ptt;

public class Request {

    /**
     * 请求URL
     */
    private String url;

    /**
     * 请求总次数
     */
    private Integer requests;

    /**
     * 请求并发数
     */
    private Integer concurrency;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRequests() {
        return requests;
    }

    public void setRequests(Integer requests) {
        this.requests = requests;
    }

    public Integer getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(Integer concurrency) {
        this.concurrency = concurrency;
    }
}
