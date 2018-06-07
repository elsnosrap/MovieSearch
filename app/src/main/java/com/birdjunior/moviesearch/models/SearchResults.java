package com.birdjunior.moviesearch.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Class created at http://www.jsonschema2pojo.org/
 */
public class SearchResults {

    @SerializedName("Search")
    @Expose
    private List<Result> searchResults = null;

    @SerializedName("totalResults")
    @Expose
    private long totalResults;

    @SerializedName("Response")
    @Expose
    private boolean response;

    /**
     * No args constructor for use in serialization
     *
     */
    public SearchResults() {
    }

    /**
     *
     * @param response
     * @param totalResults
     * @param searchResults
     */
    public SearchResults(List<Result> searchResults, long totalResults, boolean response) {
        super();
        this.searchResults = searchResults;
        this.totalResults = totalResults;
        this.response = response;
    }

    public List<Result> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Result> searchResults) {
        this.searchResults = searchResults;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public boolean getResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

}
