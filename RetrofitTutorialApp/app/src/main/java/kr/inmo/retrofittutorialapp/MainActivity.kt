package kr.inmo.retrofittutorialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitDemo {

}

class MainActivity : AppCompatActivity() {

    lateinit var jsonPlaceHolderApi : JsonPlaceHolderApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        GlobalScope.launch(Dispatchers.IO) {
//        }

        val gson = GsonBuilder().serializeNulls().create()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(object  : Interceptor {
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val originalRequest = chain.request()

                    val newRequest = originalRequest.newBuilder()
                        .header("Interceptor-Header", "xyz")
                        .build()

                    return chain.proceed(newRequest)
                }
            })
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        //getPosts()
        //getComments()
        //createPost()
        updatePost()
        //deletePost()
    }

    fun getPosts() {

        val parameters = HashMap<String, String>()
        parameters.put("userId", "1")
        parameters.put("_sort", "id")
        parameters.put("_order", "desc")

        //val call : Call<List<Post>> = jsonPlaceHolderApi.getPosts(4, "id", "desc")
        //val call : Call<List<Post>> = jsonPlaceHolderApi.getPosts(intArrayOf(1, 3, 6), "id", "desc")
        val call : Call<List<Post>> = jsonPlaceHolderApi.getPosts(parameters)
        call.enqueue(object : Callback<List<Post>> {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {

                if(!response.isSuccessful) {
                    text_view_result.text = "Code: ${response.code()}"
                    return
                }

                val posts : List<Post>? = response.body()

                if (posts != null) {
                    for(post in posts) {

                        var content = ""
                        content += "ID: ${post.id}\n"
                        content += "User ID: ${post.userId}\n"
                        content += "Title: ${post.title}\n"
                        content += "Text: ${post.text}\n\n"

                        text_view_result.append(content)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

                text_view_result.text = t.message
            }
        })
    }

    fun getComments() {

        //val call : Call<List<Comment>> = jsonPlaceHolderApi.getComments(3)
        val call : Call<List<Comment>> = jsonPlaceHolderApi.getComments("https://jsonplaceholder.typicode.com/posts/3/comments")

        call.enqueue(object : Callback<List<Comment>> {

            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {

                if(!response.isSuccessful) {
                    text_view_result.text = "Code: ${response.code()}"
                    return
                }

                val comments : List<Comment>? = response.body()

                if (comments != null) {
                    for(comment in comments) {

                        var content = ""
                        content += "ID: ${comment.id}\n"
                        content += "Post ID: ${comment.postId}\n"
                        content += "Name: ${comment.name}\n"
                        content += "Email: ${comment.email}\n"
                        content += "Text: ${comment.text}\n\n"

                        text_view_result.append(content)
                    }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {

            }
        })
    }

    fun createPost() {
        val post = Post(23, "New Title", "New Text")
        val fields = HashMap<String, String>()
        fields.put("userId", "25")
        fields.put("title", "New Title")

        //val call : Call<Post> = jsonPlaceHolderApi.createPost(post)
        //val call : Call<Post> = jsonPlaceHolderApi.createPost(23, "New Title", "New Text")
        val call : Call<Post> = jsonPlaceHolderApi.createPost(fields)

        call.enqueue(object  : Callback<Post> {

            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                if(!response.isSuccessful) {
                    text_view_result.text = "Code: ${response.code()}"
                    return
                }

                val postResponse : Post? = response.body()

                var content = ""
                content += "Code: ${response.code()}\n"
                content += "ID: ${postResponse?.id}\n"
                content += "Post ID: ${postResponse?.userId}\n"
                content += "Name: ${postResponse?.title}\n"
                content += "Text: ${postResponse?.text}\n\n"

                text_view_result.text = content

            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

                text_view_result.text = t.message
            }
        })
    }

    fun updatePost() {

        val post = Post(12, null, "Next Text")

        val headers = HashMap<String, String>()
        headers.put("Map-Header1", "def")
        headers.put("Map-Header2", "ghi")

        //val call : Call<Post> = jsonPlaceHolderApi.putPost(5, post)
        //val call : Call<Post> = jsonPlaceHolderApi.putPost("abc",5, post)
        val call : Call<Post> = jsonPlaceHolderApi.patchPost(headers,5, post)

        call.enqueue(object  : Callback<Post> {

            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                if(!response.isSuccessful) {
                    text_view_result.text = "Code: ${response.code()}"
                    return
                }

                val postResponse : Post? = response.body()

                var content = ""
                content += "Code: ${response.code()}\n"
                content += "ID: ${postResponse?.id}\n"
                content += "Post ID: ${postResponse?.userId}\n"
                content += "Name: ${postResponse?.title}\n"
                content += "Text: ${postResponse?.text}\n\n"

                text_view_result.text = content

            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

                text_view_result.text = t.message

            }
        })
    }

    fun deletePost() {

        val call : Call<Unit> = jsonPlaceHolderApi.deletePost(5)

        call.enqueue(object : Callback<Unit> {

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {

                text_view_result.text = "Code: ${response.code()}"
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                text_view_result.text = t.message
            }
        })

    }
}
