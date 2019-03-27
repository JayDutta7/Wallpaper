package app.matrix.wallpaperpexels.ui.base

abstract class BasePresenter<V : iBaseView> : iBasePresenter<V> {

     var basEView: V? = null

    override fun onAttach(baseView: V) {
        basEView = baseView
    }

    override fun onDetach() {
        basEView = null
    }

    fun getBaseView(): V {
        return basEView!!
    }


}