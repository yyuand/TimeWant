package com.example.yyuand.timewant.bean;

/**
 * Created by yyuand on 2016/8/4.
 */
public class ImageBean {

    /**
     * url : http://sjmemory.com/
     * path : http://xxx
     * imgPathID : xxx
     */

    private String url;
    private String path;
    private String imgPathID;

    public ImageBean(String url, String path, String imgPathID) {
        this.url = url;
        this.path = path;
        this.imgPathID = imgPathID;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImgPathID() {
        return imgPathID;
    }

    public void setImgPathID(String imgPathID) {
        this.imgPathID = imgPathID;
    }
}
