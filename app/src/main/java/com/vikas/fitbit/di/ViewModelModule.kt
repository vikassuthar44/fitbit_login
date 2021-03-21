package com.vikas.fitbit.di

import androidx.lifecycle.ViewModel
import com.vikas.fitbit.di.scope.ViewModelKey
import com.vikas.fitbit.ui.homepage.HomePageModel
import com.vikas.fitbit.ui.login.LoginViewModel
import com.vikas.fitbit.ui.loginScreen.LoginScreenModel
import com.vikas.fitbit.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule : ViewModelFactoryModule() {

    /*
    * This method basically says
    * inject this object into a Map using the @IntoMap annotation,
    * with the  LoginViewModel.class as key,
    * and a Provider that will build a LoginViewModel
    * object.
    *
    * */

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun splashViewModel(splashViewModel: SplashViewModel): ViewModel



    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun loginViewModel(loginViewModel: LoginViewModel):ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(LoginScreenModel::class)
    abstract fun loginScreenViewModel(loginScreenModel: LoginScreenModel):ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(HomePageModel::class)
    abstract fun homePageViewModel(homePageModel: HomePageModel):ViewModel

}