package app.matrix.wallpaperpexels.localdatabase

import app.matrix.wallpaperpexels.BuildConfig

class Constant{
    companion object {
        //Application class name
        const val application:String="WallPaperPexels"
        //ApiKey
        const val apiKey:String=BuildConfig.ApiKey
        //baseUrl
        val baseUrl:String="https://api.pexels.com/v1/"




        /* Shared prefrence key's*/
        const val UserEmail:String="UserEmail"
        const val IMAGE:String="Image"
        const val WelcomeShown:String="IsSeen"
        const val RegType:String="Sqllite"

        //*******//
        const val Firebase:String="Firebase"
        const val Sqllite:String="Sqllite"


    }
}