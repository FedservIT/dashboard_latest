package com.fedserv.pulse_dashboard.model;

public class FedCorner {
    String deeplinkUrl;
    String imgUrl;
    String isInternal;
    String pageType;
//    String pdfURL;
    String title;
//    String videoURL;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

//    public String getPdfURL() {
//        return pdfURL;
//    }
//
//    public void setPdfURL(String pdfURL) {
//        this.pdfURL = pdfURL;
//    }
//
//    public String getVideoURL() {
//        return videoURL;
//    }
//
//    public void setVideoURL(String videoURL) {
//        this.videoURL = videoURL;
//    }

    public String getDeeplinkUrl() {
        return deeplinkUrl;
    }

    public void setDeeplinkUrl(String deeplinkUrl) {
        this.deeplinkUrl = deeplinkUrl;
    }

    public String getIsInternal() {
        return isInternal;
    }

    public void setIsInternal(String isInternal) {
        this.isInternal = isInternal;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
