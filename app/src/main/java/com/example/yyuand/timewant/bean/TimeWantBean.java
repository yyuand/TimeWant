package com.example.yyuand.timewant.bean;

import java.util.List;

/**
 * Created by yyuand on 2016/8/4.
 */
public class TimeWantBean {

    /**
     * userName : xxx
     * portraitPath : http://xxx
     * description : 修改测试描述
     * rentID : xxx
     * imgList : [{"path":"http://xxx","imgPathID":"xxx"},{"path":"http://xxx","imgPathID":"xxx"}]
     * userID : xxx
     * collegeName : 东南大学
     * signatureList : [{"tag":0,"description":"时间出租系统标签三","signatureID":"xxx"}]
     * cityID : 63
     * visitedCount : 0
     * collegeID : 2
     */

    private String userName;
    private String portraitPath;
    private String description;
    private String rentID;
    private String userID;
    private String collegeName;
    private String cityID;
    private int visitedCount;
    private String collegeID;
    private float price;
    /**
     * path : http://xxx
     * imgPathID : xxx
     */
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    private List<ImgListBean> imgList;
    /**
     * tag : 0
     * description : 时间出租系统标签三
     * signatureID : xxx
     */

    private List<SignatureListBean> signatureList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPortraitPath() {
        return portraitPath;
    }

    public void setPortraitPath(String portraitPath) {
        this.portraitPath = portraitPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRentID() {
        return rentID;
    }

    public void setRentID(String rentID) {
        this.rentID = rentID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public int getVisitedCount() {
        return visitedCount;
    }

    public void setVisitedCount(int visitedCount) {
        this.visitedCount = visitedCount;
    }

    public String getCollegeID() {
        return collegeID;
    }

    public void setCollegeID(String collegeID) {
        this.collegeID = collegeID;
    }

    public List<ImgListBean> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImgListBean> imgList) {
        this.imgList = imgList;
    }

    public List<SignatureListBean> getSignatureList() {
        return signatureList;
    }

    public void setSignatureList(List<SignatureListBean> signatureList) {
        this.signatureList = signatureList;
    }

    public static class ImgListBean {
        private String path;
        private String imgPathID;

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

    public static class SignatureListBean {
        private int tag;
        private String description;
        private String signatureID;

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSignatureID() {
            return signatureID;
        }

        public void setSignatureID(String signatureID) {
            this.signatureID = signatureID;
        }
    }
}
