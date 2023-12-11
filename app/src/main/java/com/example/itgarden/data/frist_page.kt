package com.example.itgarden.data

data class ModelingContent(
    var id: String,
    var UrlImage:String,
    var HeadText:String,
    var BodyText:String
)
data class ModelingTree(
    var id:Int,
    var TreeID:Int,
    var UrlImage:String,
    var TreeName:String,
    var SicName:String,
    var Benefit:String,
    var TakeCare:String
)

data class ModelingEnvi(
    var Text:String
)