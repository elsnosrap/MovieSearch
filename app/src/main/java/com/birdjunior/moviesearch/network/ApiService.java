package com.birdjunior.moviesearch.network;

import com.birdjunior.moviesearch.models.SearchResults;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    @GET("/?apikey=ee60b1cc")
    Observable<SearchResults> performSearch(@Query("s") String searchTerm, @Query("page") int pageNum);
}
