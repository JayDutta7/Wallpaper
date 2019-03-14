package app.matrix.wallpaperpexels.ui.activity.registration

interface IRegistrationView {

    fun redirectLogin()

    fun registerSqllite()

    fun registerFirebase()

    fun showErrorToast()
}