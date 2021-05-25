package com.test.skydemo.di

import android.app.Application
import com.test.lib_api.inject.NewsApiLibraryModule
import com.test.skydemo.SkyDemoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NewsApiLibraryModule::class]
)

interface AppComponent : AndroidInjector<SkyDemoApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}