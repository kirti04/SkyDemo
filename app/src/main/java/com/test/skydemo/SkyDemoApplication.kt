package com.test.skydemo

import com.test.skydemo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class SkyDemoApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}