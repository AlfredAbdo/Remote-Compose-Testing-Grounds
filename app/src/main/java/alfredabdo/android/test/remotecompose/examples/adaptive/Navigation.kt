package alfredabdo.android.test.remotecompose.examples.adaptive

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface AdaptiveRoute : NavKey {

    @Serializable
    data object RemoteCreator : AdaptiveRoute

    @Serializable
    class RemotePlayer(
        val infoToDisplay: ByteArray,
    ) : AdaptiveRoute
}

fun EntryProviderScope<NavKey>.remoteAdaptiveEntries(
    backStack: NavBackStack<NavKey>,
) {
    entry<AdaptiveRoute.RemoteCreator> {
        RemoteCreatorPage(
            onRedirectToPlayer = { info ->
                backStack += AdaptiveRoute.RemotePlayer(info)
            },
            onBack = backStack::removeLastOrNull,
        )
    }

    entry<AdaptiveRoute.RemotePlayer> {
        RemotePlayerPage(
            it.infoToDisplay,
            onBack = backStack::removeLastOrNull,
        )
    }
}