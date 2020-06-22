package kr.inmo.retrofittutorialapp

import com.google.gson.annotations.SerializedName

class Post {

    var userId : Int = 0
    var id : Int = 0
    var title : String? = ""
    @SerializedName("body")
    var text : String = ""

    constructor(userId : Int, id : Int, title : String, text : String) {
        this.userId = userId
        this.id = id
        this.title = title
        this.text = text
    }

    constructor(userId: Int, title : String?, text : String) {
        this.userId = userId
        this.title = title
        this.text = text
    }
}