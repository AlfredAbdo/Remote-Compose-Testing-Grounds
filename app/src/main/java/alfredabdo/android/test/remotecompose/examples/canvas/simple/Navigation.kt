package alfredabdo.android.test.remotecompose.examples.canvas.simple

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface CanvasSimpleRoute : NavKey {

    @Serializable
    data object RemoteCreator : CanvasSimpleRoute

    @Serializable
    class RemotePlayer(
        val infoToDisplay: ByteArray,
    ) : CanvasSimpleRoute
}

fun EntryProviderScope<NavKey>.remoteCanvasSimpleEntries(
    backStack: NavBackStack<NavKey>,
) {
    entry<CanvasSimpleRoute.RemoteCreator> {
        RemoteCreatorPage(
            onRedirectToPlayer = { info ->
                backStack += CanvasSimpleRoute.RemotePlayer(info)
            },
            onBack = backStack::removeLastOrNull,
        )
    }

    entry<CanvasSimpleRoute.RemotePlayer> {
        RemotePlayerPage(
            it.infoToDisplay,
            onBack = backStack::removeLastOrNull,
        )
    }
}