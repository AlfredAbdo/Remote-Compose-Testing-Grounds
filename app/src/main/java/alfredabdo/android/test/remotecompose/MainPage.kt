package alfredabdo.android.test.remotecompose

import alfredabdo.android.test.remotecompose.examples.counter.remoteCounterEntries
import alfredabdo.android.test.remotecompose.examples.simple.remoteSimpleEntries
import alfredabdo.android.test.remotecompose.home.HomePage
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
            remoteSimpleEntries(backStack)
            remoteCounterEntries(backStack)
            //...
        },
    )
}