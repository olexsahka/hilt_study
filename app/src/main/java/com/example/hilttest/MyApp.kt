package com.example.hilttest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp //  указать в манифесте
class MyApp : Application() {
}