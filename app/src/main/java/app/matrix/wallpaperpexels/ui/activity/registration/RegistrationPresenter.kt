package app.matrix.wallpaperpexels.ui.activity.registration

class RegistrationPresenter(private var regview:IRegistrationView):IRegistrationPresenter {
    override fun redirectLogin() {
        regview.redirectLogin()
    }

    override fun registerSqllite() {
        regview.registerSqllite()
    }


}