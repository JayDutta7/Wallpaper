package app.matrix.wallpaperpexels.ui.activity.registration

import app.matrix.wallpaperpexels.WallPaperApp
import app.matrix.wallpaperpexels.database.StaticKT.Constant

class RegistrationPresenter(private var regview: IRegistrationView) : IRegistrationPresenter {

    override fun registerFirebase() {

        regview.registerFirebase()

    }

    override fun typeRegistration() {

        when {
            WallPaperApp.getPref().getValueString(Constant.RegType).equals("Sqllite") -> {

                regview.registerSqllite()

            }
            WallPaperApp.getPref().getValueString(Constant.RegType).equals("Firebase") -> {

                regview.registerFirebase()
            }

            else -> {
                regview.showErrorToast()
            }
        }


    }

    override fun redirectLogin() {

        regview.redirectLogin()

    }

    override fun registerSqllite() {

        regview.registerSqllite()

    }


}