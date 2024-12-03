package `in`.abhranilnxt.cryptocore

import android.app.Application
import `in`.abhranilnxt.cryptocore.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CryptoCoreApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptoCoreApp)
            androidLogger()

            modules(appModule)
        }
    }
}