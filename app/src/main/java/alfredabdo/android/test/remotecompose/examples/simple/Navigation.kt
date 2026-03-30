package alfredabdo.android.test.remotecompose.examples.simple

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface SimpleRoute : NavKey {

    @Serializable
    data object RemoteCreator : SimpleRoute

    @Serializable
    class RemotePlayer(
        val infoToDisplay: ByteArray,
    ) : SimpleRoute
}

fun EntryProviderScope<NavKey>.remoteSimpleEntries(
    backStack: NavBackStack<NavKey>,
) {
    entry<SimpleRoute.RemoteCreator> {
        RemoteCreatorPage(
            onRedirectToPlayer = { info ->
                backStack += SimpleRoute.RemotePlayer(info)
            },
            onBack = backStack::removeLastOrNull,
        )
    }

    entry<SimpleRoute.RemotePlayer> {
        RemotePlayerPage(
            it.infoToDisplay,
            onBack = backStack::removeLastOrNull,
        )
    }
}