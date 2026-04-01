package alfredabdo.android.test.remotecompose.examples.animated

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface AnimatedRoute : NavKey {

    @Serializable
    data object RemoteCreator : AnimatedRoute

    @Serializable
    class RemotePlayer(
        val infoToDisplay: ByteArray,
    ) : AnimatedRoute
}

fun EntryProviderScope<NavKey>.remoteAnimatedEntries(
    backStack: NavBackStack<NavKey>,
) {
    entry<AnimatedRoute.RemoteCreator> {
        RemoteCreatorPage(
            onRedirectToPlayer = { info ->
                backStack += AnimatedRoute.RemotePlayer(info)
            },
            onBack = backStack::removeLastOrNull,
        )
    }

    entry<AnimatedRoute.RemotePlayer> {
        RemotePlayerPage(
            it.infoToDisplay,
            onBack = backStack::removeLastOrNull,
        )
    }
}