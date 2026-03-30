package alfredabdo.android.test.remotecompose.examples.simple

import alfredabdo.android.test.remotecompose.annotations.DefaultPreview
import alfredabdo.android.test.remotecompose.ui.theme.AppTheme
import alfredabdo.android.test.remotecompose.ui.topbar.MainBackIcon
import alfredabdo.android.test.remotecompose.ui.topbar.MainTopAppBar
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.remote.player.compose.RemoteDocumentPlayer
import androidx.compose.remote.player.core.RemoteDocument
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun RemotePlayerPage(
    infoToDisplay: ByteArray,
    onBack: () -> Unit,
) {
    Box(
        Modifier
            .safeDrawingPadding()
            .fillMaxSize(),
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()

        RemotePlayerUI(
            infoToDisplay,
            onSayHi = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Hi!")
                }
            },
            Modifier
                .fillMaxSize(),
            onBack = onBack,
        )

        SnackbarHost(
            snackbarHostState,
            Modifier.align(Alignment.BottomCenter),
        )
    }
}


@SuppressLint("RestrictedApi")
@Composable
private fun RemotePlayerUI(
    infoToDisplay: ByteArray?,
    onSayHi: () -> Unit,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
) {
    Column(
        modifier,
    ) {
        MainTopAppBar(
            "Simple Example",
            Modifier.fillMaxWidth(),
            navigationIcon = { MainBackIcon(onBack) },
        )
        Text("Content played is below:")
        Spacer(Modifier.height(16.dp))

        val document = remember(infoToDisplay) {
            infoToDisplay?.let { RemoteDocument(it) }?.document
        }
        document?.let {
            RemoteDocumentPlayer(
                document,
                document.width,
                document.height,
                Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onNamedAction = { actionId, value, _ ->
                    when (actionId) {
                        "say-hi" -> onSayHi()
                        //...
                        else -> {}
                    }
                },
            )
        }
    }
}


@DefaultPreview
@Composable
private fun RemotePlayerUI_Preview() {
    AppTheme {
        RemotePlayerUI(
            null,
            onSayHi = {},
            Modifier.fillMaxSize(),
        )
    }
}