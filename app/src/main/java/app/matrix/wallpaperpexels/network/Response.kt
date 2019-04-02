package app.matrix.wallpaperpexels.network



sealed class Response<out T:Any> {

class Success<out T:Any>(val data:T):Response<T>()

class Error(val exception: Throwable):Response<Nothing>()

}