
package com.ruanorz.marvelapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Creators extends RealmObject {

    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("collectionURI")
    @Expose
    private String collectionURI;
    @SerializedName("items")
    @Expose
    private RealmList<com.ruanorz.marvelapp.Item> items = null;
    @SerializedName("returned")
    @Expose
    private Integer returned;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Creators() {
    }

    /**
     * 
     * @param items
     * @param collectionURI
     * @param available
     * @param returned
     */
    public Creators(Integer available, String collectionURI, RealmList<com.ruanorz.marvelapp.Item> items, Integer returned) {
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

    public Creators withAvailable(Integer available) {
        this.available = available;
        return this;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public Creators withCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
        return this;
    }

    public List<com.ruanorz.marvelapp.Item> getItems() {
        return items;
    }

    public void setItems(RealmList<com.ruanorz.marvelapp.Item> items) {
        this.items = items;
    }

    public Creators withItems(RealmList<com.ruanorz.marvelapp.Item> items) {
        this.items = items;
        return this;
    }

    public Integer getReturned() {
        return returned;
    }

    public void setReturned(Integer returned) {
        this.returned = returned;
    }

    public Creators withReturned(Integer returned) {
        this.returned = returned;
        return this;
    }


}
