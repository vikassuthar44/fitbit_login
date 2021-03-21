package com.vikas.fitbit.di


import com.vikas.fitbit.di.scope.ActivityScoped

import com.vikas.fitbit.ui.homepage.HomePageActivity
import com.vikas.fitbit.ui.login.LoginActivity
import com.vikas.fitbit.ui.login.LoginModule
import com.vikas.fitbit.ui.loginScreen.LoginScreenActivity
import com.vikas.fitbit.ui.splash.SplashActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [ViewModelModule::class]
)
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity


    @ActivityScoped
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun loginActivity():LoginActivity


    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun loginScreenActivity():LoginScreenActivity


    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun homePageActivity():HomePageActivity

}