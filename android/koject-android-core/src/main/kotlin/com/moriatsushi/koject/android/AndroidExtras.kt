package com.moriatsushi.koject.android

import android.app.Application
import android.content.Context
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.extras.KojectExtras
import com.moriatsushi.koject.extras.KojectExtrasMessage

@OptIn(ExperimentalKojectApi::class)
@KojectExtrasMessage(
    """Please call `KojectBuilder.application()` in your application.

class YourApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Koject.start {
            application(this@YourApplication) // Add this line
        }
    }
}
""",
)
internal class AndroidExtras(
    val application: Application,
) : KojectExtras {
    val context: Context
        get() = application
}
