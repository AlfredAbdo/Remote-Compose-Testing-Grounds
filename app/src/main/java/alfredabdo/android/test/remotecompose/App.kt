package alfredabdo.android.test.remotecompose

import android.app.Application
import androidx.compose.remote.player.core.platform.BitmapLoader
import okhttp3.OkHttpClient
import okhttp3.Request

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }


    override fun onCreate() {
        instance = this
        super.onCreate()
    }


    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            //...
            .build()
    }

    val bitmapLoader: BitmapLoader
        get() = BitmapLoader { url ->
            okHttpClient.newCall(
                Request.Builder()
                    .url(url)
                    .build()
            )
                .execute()
                .body
                .byteStream()
        }
}