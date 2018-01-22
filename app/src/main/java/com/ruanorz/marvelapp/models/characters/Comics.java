
package com.ruanorz.marvelapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Comics{

    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("collectionURI")
    @Expose
    private String collectionURI;
    @SerializedName("items")
    @Expose
    private List<com.ruanorz.marvelapp.Item> items = null;
    @SerializedName("returned")
    @Expose
    private Integer returned;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Comics() {
    }

    /**
     * 
     * @param items
     * @param collectionURI
     * @param available
     * @param returned
     */
    public Comics(Integer available, String collectionURI, List<com.ruanorz.marvelapp.Item> items, Integer returned) {
        super();
        this.available = available;
        this.collectionURI = collectionURI;
        this.items = items;
        this.returned = returned;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Comics withAvailable(Integer available) {
        this.available = available;
        return this;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public Comics withCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
        return this;
    }

    public List<com.ruanorz.marvelapp.Item> getItems() {
        return items;
    }

    public void setItems(List<com.ruanorz.marvelapp.Item> items) {
        this.items = items;
    }

    public Comics withItems(List<com.ruanorz.marvelapp.Item> items) {
        this.items = items;
        return this;
    }

    public Integer getReturned() {
        return returned;
    }

    public void setReturned(Integer returned) {
        this.returned = returned;
    }

    public Comics withReturned(Integer returned) {
        this.returned = returned;
        return this;
    }

}
