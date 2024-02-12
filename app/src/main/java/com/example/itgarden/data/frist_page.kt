package com.example.itgarden.data


data class ModelingContent(
    var id: String,
    var UrlImage:String,
    var HeadText:String,
    var BodyText:String,
    var ActType:String
)
data class ModelingTree(
    var id:String,
    var TreeID: String,
    var UrlImage:String,
    var TreeName:String,
    var SicName:String,
    var Type:String,
    var Benefit:String,
    var TakeCare:String,
    var attribute:String
)

data class ModelingEnvi(
    var id:String,
    var Envi:String,
    var ImageURL:String
)