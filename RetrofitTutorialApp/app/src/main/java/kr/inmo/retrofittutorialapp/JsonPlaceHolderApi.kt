package kr.inmo.retrofittutorialapp

import retrofit2.Call
import retrofit2.http.*


interface JsonPlaceHolderApi {

//    @GET("posts ")
//     fun getPosts() : Call<List<Post>>
//
//    @GET("posts")
//    fun getPosts(@Query("userId") userId : Int, @Query("_sort") sort : String, @Query("_order") order : String) : Call<List<Post>>

    @GET("posts")
    fun getPosts(@Query("userId") userId : IntArray, @Query("_sort") sort : String, @Query("_order") order : String) : Call<List<Post>>

    @GET("posts")
    fun getPosts(@QueryMap parameters : Map<String, String>) : Call<List<Post>>


    //@GET("posts/2/comments")
    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId : Int) :  Call<List<Comment>>

    @GET
    fun getComments(@Url url : String) :  Call<List<Comment>>
}