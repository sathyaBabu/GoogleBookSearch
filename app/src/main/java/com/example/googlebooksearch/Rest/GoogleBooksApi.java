package com.example.googlebooksearch.Rest;

import com.example.googlebooksearch.Model.GoogleBookRespond;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBooksApi {
    @GET("/books/v1/volumes?langRestrict=en&maxResults=5&printType=books")
    public Call <GoogleBookRespond> searchBooks (@Query("q") String searchText,
                                                 @Query("Key") String key);
}
