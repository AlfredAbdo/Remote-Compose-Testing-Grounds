package alfredabdo.android.test.remotecompose.examples.canvas.animated

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface CanvasAnimatedRoute : NavKey {

    @Serializable
    data object RemoteCreator : CanvasAnimatedRoute

    @Serializable
    class RemotePlayer(
        val infoToDisplay: ByteArray,
    ) : CanvasAnimatedRoute
}

fun EntryProviderScope<NavKey>.remoteCanvasAnimatedEntries(
    backStack: NavBackStack<NavKey>,
) {
    entry<CanvasAnimatedRoute.RemoteCreator> {
        RemoteCreatorPage(
            onRedirectToPlayer = { info ->
                backStack += CanvasAnimatedRoute.RemotePlayer(info)
            },
            onBack = backStack::removeLastOrNull,
        )
    }

    entry<CanvasAnimatedRoute.RemotePlayer> {
        RemotePlayerPage(
            it.infoToDisplay,
            onBack = backStack::removeLastOrNull,
        )
    }
}