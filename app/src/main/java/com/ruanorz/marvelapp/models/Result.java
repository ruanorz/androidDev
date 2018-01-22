
package com.ruanorz.marvelapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Result extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("digitalId")
    @Expose
    private Integer digitalId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("issueNumber")
    @Expose
    private Integer issueNumber;

    @SerializedName("variantDescription")
    @Expose
    private String variantDescription;
    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("upc")
    @Expose
    private String upc;
    @SerializedName("diamondCode")
    @Expose
    private String diamondCode;
    @SerializedName("ean")
    @Expose
    private String ean;
    @SerializedName("issn")
    @Expose
    private String issn;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("urls")
    @Expose
    private RealmList<com.ruanorz.marvelapp.Url> urls = null;
    @SerializedName("series")
    @Expose
    private com.ruanorz.marvelapp.Series series;
    @SerializedName("dates")
    @Expose
    private RealmList<com.ruanorz.marvelapp.Date> dates = null;
    @SerializedName("prices")
    @Expose
    private RealmList<com.ruanorz.marvelapp.Price> prices = null;
    @SerializedName("thumbnail")
    @Expose
    private com.ruanorz.marvelapp.Thumbnail thumbnail;
    @SerializedName("images")
    @Expose
    private RealmList<com.ruanorz.marvelapp.Image> images = null;
    @SerializedName("creators")
    @Expose
    private com.ruanorz.marvelapp.Creators creators;
    @SerializedName("characters")
    @Expose
    private com.ruanorz.marvelapp.Characters characters;
    @SerializedName("stories")
    @Expose
    private com.ruanorz.marvelapp.Stories stories;
    @SerializedName("events")
    @Expose
    private com.ruanorz.marvelapp.Events events;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param series
     * @param issn
     * @param events
     * @param id
     * @param title
     * @param dates
     * @param isbn
     * @param digitalId
     * @param pageCount
     * @param urls
     * @param format
     * @param upc
     * @param modified
     * @param creators
     * @param ean
     * @param issueNumber
     * @param stories
     * @param thumbnail
     * @param resourceURI
     * @param images
     * @param prices
     * @param characters
     * @param diamondCode
     */
    public Result(Integer id, String name, Integer digitalId, String title, Integer issueNumber, String modified, String isbn, String upc, String diamondCode, String ean, String issn, String format, Integer pageCount, String resourceURI, RealmList<com.ruanorz.marvelapp.Url> urls, com.ruanorz.marvelapp.Series series, RealmList<com.ruanorz.marvelapp.Date> dates, RealmList<com.ruanorz.marvelapp.Price> prices, com.ruanorz.marvelapp.Thumbnail thumbnail, RealmList<com.ruanorz.marvelapp.Image> images, com.ruanorz.marvelapp.Creators creators, com.ruanorz.marvelapp.Characters characters, com.ruanorz.marvelapp.Stories stories, com.ruanorz.marvelapp.Events events) {
        super();
        this.id = id;
        this.name = name;
        this.digitalId = digitalId;
        this.title = title;
        this.issueNumber = issueNumber;
        this.modified = modified;
        this.isbn = isbn;
        this.upc = upc;
        this.diamondCode = diamondCode;
        this.ean = ean;
        this.issn = issn;
        this.format = format;
        this.pageCount = pageCount;
        this.resourceURI = resourceURI;
        this.urls = urls;
        this.series = series;
        this.dates = dates;
        this.prices = prices;
        this.thumbnail = thumbnail;
        this.images = images;
        this.creators = creators;
        this.characters = characters;
        this.stories = stories;
        this.events = events;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Result withId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getDigitalId() {
        return digitalId;
    }

    public void setDigitalId(Integer digitalId) {
        this.digitalId = digitalId;
    }

    public Result withDigitalId(Integer digitalId) {
        this.digitalId = digitalId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Result withTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }

    public Result withIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
        return this;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
    }

    public Result withVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
        return this;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Result withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Result withModified(String modified) {
        this.modified = modified;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Result withIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Result withUpc(String upc) {
        this.upc = upc;
        return this;
    }

    public String getDiamondCode() {
        return diamondCode;
    }

    public void setDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
    }

    public Result withDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
        return this;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public Result withEan(String ean) {
        this.ean = ean;
        return this;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public Result withIssn(String issn) {
        this.issn = issn;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Result withFormat(String format) {
        this.format = format;
        return this;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Result withPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

//    public List<RealmString> getTextObjects() {
//        return textObjects;
//    }
//
//    public void setTextObjects(RealmList<RealmString> textObjects) {
//        this.textObjects = textObjects;
//    }
//
//    public Result withTextObjects(RealmList<RealmString> textObjects) {
//        this.textObjects = textObjects;
//        return this;
//    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public Result withResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
        return this;
    }

    public List<com.ruanorz.marvelapp.Url> getUrls() {
        return urls;
    }

    public void setUrls(RealmList<com.ruanorz.marvelapp.Url> urls) {
        this.urls = urls;
    }

    public Result withUrls(RealmList<com.ruanorz.marvelapp.Url> urls) {
        this.urls = urls;
        return this;
    }

    public com.ruanorz.marvelapp.Series getSeries() {
        return series;
    }

    public void setSeries(com.ruanorz.marvelapp.Series series) {
        this.series = series;
    }

    public Result withSeries(com.ruanorz.marvelapp.Series series) {
        this.series = series;
        return this;
    }

//    public List<RealmString> getVariants() {
//        return variants;
//    }
//
//    public void setVariants(RealmList<RealmString> variants) {
//        this.variants = variants;
//    }
//
//    public Result withVariants(RealmList<RealmString> variants) {
//        this.variants = variants;
//        return this;
//    }

//    public List<RealmString> getCollections() {
//        return collections;
//    }
//
//    public void setCollections(RealmList<RealmString> collections) {
//        this.collections = collections;
//    }
//
//    public Result withCollections(RealmList<RealmString> collections) {
//        this.collections = collections;
//        return this;
//    }
//
//    public List<RealmString> getCollectedIssues() {
//        return collectedIssues;
//    }
//
//    public void setCollectedIssues(RealmList<RealmString> collectedIssues) {
//        this.collectedIssues = collectedIssues;
//    }
//
//    public Result withCollectedIssues(RealmList<RealmString> collectedIssues) {
//        this.collectedIssues = collectedIssues;
//        return this;
//    }

    public RealmList<com.ruanorz.marvelapp.Date> getDates() {
        return dates;
    }

    public void setDates(RealmList<com.ruanorz.marvelapp.Date> dates) {
        this.dates = dates;
    }

    public Result withDates(RealmList<com.ruanorz.marvelapp.Date> dates) {
        this.dates = dates;
        return this;
    }

    public RealmList<com.ruanorz.marvelapp.Price> getPrices() {
        return prices;
    }

    public void setPrices(RealmList<com.ruanorz.marvelapp.Price> prices) {
        this.prices = prices;
    }

    public Result withPrices(RealmList<com.ruanorz.marvelapp.Price> prices) {
        this.prices = prices;
        return this;
    }

    public com.ruanorz.marvelapp.Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(com.ruanorz.marvelapp.Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Result withThumbnail(com.ruanorz.marvelapp.Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public RealmList<com.ruanorz.marvelapp.Image> getImages() {
        return images;
    }

    public void setImages(RealmList<com.ruanorz.marvelapp.Image> images) {
        this.images = images;
    }

    public Result withImages(RealmList<com.ruanorz.marvelapp.Image> images) {
        this.images = images;
        return this;
    }

    public com.ruanorz.marvelapp.Creators getCreators() {
        return creators;
    }

    public void setCreators(com.ruanorz.marvelapp.Creators creators) {
        this.creators = creators;
    }

    public Result withCreators(com.ruanorz.marvelapp.Creators creators) {
        this.creators = creators;
        return this;
    }

    public com.ruanorz.marvelapp.Characters getCharacters() {
        return characters;
    }

    public void setCharacters(com.ruanorz.marvelapp.Characters characters) {
        this.characters = characters;
    }

    public Result withCharacters(com.ruanorz.marvelapp.Characters characters) {
        this.characters = characters;
        return this;
    }

    public com.ruanorz.marvelapp.Stories getStories() {
        return stories;
    }

    public void setStories(com.ruanorz.marvelapp.Stories stories) {
        this.stories = stories;
    }

    public Result withStories(com.ruanorz.marvelapp.Stories stories) {
        this.stories = stories;
        return this;
    }

    public com.ruanorz.marvelapp.Events getEvents() {
        return events;
    }

    public void setEvents(com.ruanorz.marvelapp.Events events) {
        this.events = events;
    }

    public Result withEvents(com.ruanorz.marvelapp.Events events) {
        this.events = events;
        return this;
    }



}
