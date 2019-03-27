package app.matrix.wallpaperpexels.ui.base

interface iBasePresenter<V:iBaseView> {
     fun onAttach(baseView:V)
     fun onDetach()
     fun onDestroy()
}