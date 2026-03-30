package alfredabdo.android.test.remotecompose.examples.counter

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface CounterRoute : NavKey {

    @Serializable
    data object RemoteCreator : CounterRoute

    @Serializable
    class RemotePlayer(
        val infoToDisplay: ByteArray,
    ) : CounterRoute
}

fun EntryProviderScope<NavKey>.remoteCounterEntries(
    backStack: NavBackStack<NavKey>,
) {
    entry<CounterRoute.RemoteCreator> {
        RemoteCreatorPage(
            onRedirectToPlayer = { info ->
                backStack += CounterRoute.RemotePlayer(info)
            },
            onBack = backStack::removeLastOrNull,
        )
    }

    entry<CounterRoute.RemotePlayer> {
        RemotePlayerPage(
            it.infoToDisplay,
            onBack = backStack::removeLastOrNull,
        )
    }
}