package app.matrix.wallpaperpexels.ui.base

interface iBasePresenter<V:iBaseView> {
    abstract fun onAttach(baseView:V)
    abstract fun onDetach()
    abstract fun onDestroy()
}