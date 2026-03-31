package alfredabdo.android.test.remotecompose.examples.image

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface ImageRoute : NavKey {

    @Serializable
    data object RemoteCreator : ImageRoute

    @Serializable
    class RemotePlayer(
        val infoToDisplay: ByteArray,
    ) : ImageRoute
}

fun EntryProviderScope<NavKey>.remoteImageEntries(
    backStack: NavBackStack<NavKey>,
) {
    entry<ImageRoute.RemoteCreator> {
        RemoteCreatorPage(
            onRedirectToPlayer = { info ->
                backStack += ImageRoute.RemotePlayer(info)
            },
            onBack = backStack::removeLastOrNull,
        )
    }

    entry<ImageRoute.RemotePlayer> {
        RemotePlayerPage(
            it.infoToDisplay,
            onBack = backStack::removeLastOrNull,
        )
    }
}