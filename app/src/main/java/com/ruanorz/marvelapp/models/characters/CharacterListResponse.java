
package com.ruanorz.marvelapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class CharacterListResponse{

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

    /**
     * No args constructor for use in serialization
     * 
     */
    public CharacterListResponse() {
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
    public CharacterListResponse(Integer code, String status, String copyright, String attributionText, String attributionHTML, String etag, com.ruanorz.marvelapp.Data data) {
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

    public CharacterListResponse withCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CharacterListResponse withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public CharacterListResponse withCopyright(String copyright) {
        this.copyright = copyright;
        return this;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public CharacterListResponse withAttributionText(String attributionText) {
        this.attributionText = attributionText;
        return this;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public CharacterListResponse withAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
        return this;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public CharacterListResponse withEtag(String etag) {
        this.etag = etag;
        return this;
    }

    public com.ruanorz.marvelapp.Data getData() {
        return data;
    }

    public void setData(com.ruanorz.marvelapp.Data data) {
        this.data = data;
    }

    public CharacterListResponse withData(com.ruanorz.marvelapp.Data data) {
        this.data = data;
        return this;
    }

}
