package app.matrix.wallpaperpexels.localdatabase

import app.matrix.wallpaperpexels.BuildConfig

class Constant{
    companion object {
        //Application class name
        const val application:String="WallPaperPexels"
        //ApiKey
        const val apiKey:String=BuildConfig.ApiKey
        //Pexels-baseUrl
        const val PexelsbaseUrl:String="https://api.pexels.com/v1/"
        //PexelsbaseUrl-Unspalsh
        const val UnsplashBaseUrl:String="https://api.unsplash.com/"

        /*RandomPhoto*/
        const val randomPhoto= PexelsbaseUrl+"curated?per_page=80&page=1"//curated?per_page=100&page=1
        /*Get A Photo*/
        const val getAPhoto= PexelsbaseUrl+"photos"



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