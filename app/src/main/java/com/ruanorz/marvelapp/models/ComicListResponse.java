
package com.ruanorz.marvelapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;

public class ComicListResponse extends RealmObject {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("attributionText")
    @Expose
    private String attributionText;
    @SerializedName("attributionHTML")
    @Expose
    private String attributionHTML;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("data")
    @Expose
    private com.ruanorz.marvelapp.Data data;

    private Date SavedAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ComicListResponse() {
    }

    /**
     * 
     * @param attributionText
     * @param etag
     * @param status
     * @param data
     * @param copyright
     * @param code
     * @param attributionHTML
     */
    public ComicListResponse(Integer code, String status, String copyright, String attributionText, String attributionHTML, String etag, com.ruanorz.marvelapp.Data data) {
        super();
        this.code = code;
        this.status = status;
        this.copyright = copyright;
        this.attributionText = attributionText;
        this.attributionHTML = attributionHTML;
        this.etag = etag;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ComicListResponse withCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ComicListResponse withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public ComicListResponse withCopyright(String copyright) {
        this.copyright = copyright;
        return this;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public ComicListResponse withAttributionText(String attributionText) {
        this.attributionText = attributionText;
        return this;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public ComicListResponse withAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
        return this;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public ComicListResponse withEtag(String etag) {
        this.etag = etag;
        return this;
    }

    public com.ruanorz.marvelapp.Data getData() {
        return data;
    }

    public void setData(com.ruanorz.marvelapp.Data data) {
        this.data = data;
    }

    public ComicListResponse withData(com.ruanorz.marvelapp.Data data) {
        this.data = data;
        return this;
    }


    public Date getSavedAt() {
        return SavedAt;
    }

    public void setSavedAt(Date savedAt) {
        SavedAt = savedAt;
    }
}
