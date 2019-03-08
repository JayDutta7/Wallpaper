package baseclass.uiinterfaces

/**
 * Created by Matrix
 */

interface IBaseResponseListener {

    fun onSuccessResponse(message: String)
    fun onErrorResponse(error: String)
    fun onFailedResponse(message: String)
}
