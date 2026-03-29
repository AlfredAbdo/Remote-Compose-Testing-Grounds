package alfredabdo.android.test.remotecompose

import alfredabdo.android.test.remotecompose.home.HomePage
import alfredabdo.android.test.remotecompose.remote.RemoteCreatorPage
import alfredabdo.android.test.remotecompose.remote.RemotePlayerPage
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay

@Composable
fun MainPage() {
    val backStack = rememberNavBackStack(MainRoute.Home)

    NavDisplay(
        backStack,
        Modifier.fillMaxSize(),
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<MainRoute.Home> {
                HomePage(
                    onRedirectTo = { route -> backStack += route },
                )
            }

            entry<MainRoute.RemoteCreator> {
                RemoteCreatorPage(
                    onRedirectToPlayer = { info ->
                        backStack += MainRoute.RemotePlayer(info)
                    },
                )
            }

            entry<MainRoute.RemotePlayer> {
                RemotePlayerPage(
                    it.infoToDisplay,
                )
            }
        },
    )
}