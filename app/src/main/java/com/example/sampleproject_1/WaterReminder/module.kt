package com.example.sampleproject_1.WaterReminder

import android.app.Application
import android.content.SharedPreferences
import androidx.work.WorkManager
import com.example.sampleproject_1.WaterReminder.Database.ViewModelWaterReminder
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.example.sampleproject_1.weightTracker.DatabaseWT.ViewModelWeightTracker
import com.example.sampleproject_1.weightTracker2.databaseWt2.ViewModelWeightTracker2
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    single { setSharedPrefs(androidApplication()) }
}

val viewModelModule: Module = module {
    viewModel { ViewModelWaterReminder(Application()) }
}

val viewModelModuleWT: Module = module {
    viewModel { ViewModelWeightTracker(Application()) }
}

val viewModelModuleWT2: Module = module {
    viewModel { ViewModelWeightTracker2(Application()) }
}

private fun setSharedPrefs(app: Application): SharedPreferences = app.getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)




