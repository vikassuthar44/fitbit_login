package com.vikas.fitbit.di

import android.content.Context
import com.vikas.fitbit.AppController
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindApplication(appController: AppController): Context
}