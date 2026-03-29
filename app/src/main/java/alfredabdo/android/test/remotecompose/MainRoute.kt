package alfredabdo.android.test.remotecompose

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface MainRoute : NavKey {

    @Serializable
    data object Home : MainRoute

    @Serializable
    data object RemoteCreator : MainRoute

    @Serializable
    class RemotePlayer(
        val infoToDisplay: ByteArray,
    ) : MainRoute

    //...
}