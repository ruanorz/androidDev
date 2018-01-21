
package com.ruanorz.marvelapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Data extends RealmObject {

    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("results")
    @Expose
    private RealmList<com.ruanorz.marvelapp.Result> results = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param total
     * @param limit
     * @param results
     * @param count
     * @param offset
     */
    public Data(Integer offset, Integer limit, Integer total, Integer count, RealmList<com.ruanorz.marvelapp.Result> results) {
        super();
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.count = count;
        this.results = results;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Data withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Data withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Data withTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Data withCount(Integer count) {
        this.count = count;
        return this;
    }

    public List<com.ruanorz.marvelapp.Result> getResults() {
        return results;
    }

    public void setResults(RealmList<com.ruanorz.marvelapp.Result> results) {
        this.results = results;
    }

    public Data withResults(RealmList<com.ruanorz.marvelapp.Result> results) {
        this.results = results;
        return this;
    }



}
