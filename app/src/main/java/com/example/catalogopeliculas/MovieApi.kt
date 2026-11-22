package com.example.catalogopeliculas

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MovieApi {

    @GET("movies")
    fun getMovies(): Call<List<Movie>>

    @GET("movies/{id}")
    fun getMovieById(@Path("id") id: String): Call<Movie>

    @POST("movies")
    fun createMovie(@Body movie: Movie): Call<Movie>

    @DELETE("movies/{id}")
    fun deleteMovie(@Path("id") id: String): Call<Movie>
}