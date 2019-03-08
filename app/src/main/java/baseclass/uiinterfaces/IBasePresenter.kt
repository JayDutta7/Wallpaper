package baseclass.uiinterfaces

/**
 * Created by Matrix
 */

interface IBasePresenter<V : IBaseView> {

    fun onAttach(baseView: V)
    fun onDetach()
    fun onDestroy()


}
