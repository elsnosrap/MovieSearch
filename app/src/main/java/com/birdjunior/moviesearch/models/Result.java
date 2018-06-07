package com.birdjunior.moviesearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Class created at http://www.jsonschema2pojo.org/
 *
 * Options specified:
 *  Language: Java
 *  Source type: YAML
 *  Annotation Style: Gson
 *  Include getters / setters
 *  Include constructors
 *
 * Source YAML schema:
    {
        "type": "object",
        "properties": {
            "Title": {
                "type": "string"
            },
            "Year": {
                "type": "string"
            },
            "imdbID": {
                "type": "string"
            },
            "Type": {
                "type": "string"
            },
            "Poster": {
                "type": "string"
            }
        }
    }
 */
public class Result {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("imdbID")
    @Expose
    private String imdbID;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Poster")
    @Expose
    private String poster;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param title
     * @param poster
     * @param year
     * @param imdbID
     * @param type
     */
    public Result(String title, String year, String imdbID, String type, String poster) {
        super();
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.type = type;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

}