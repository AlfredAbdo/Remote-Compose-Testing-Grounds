package alfredabdo.android.test.remotecompose.examples.adaptive

import alfredabdo.android.test.remotecompose.ui.theme.AppTheme
import alfredabdo.android.test.remotecompose.ui.topbar.MainBackIcon
import alfredabdo.android.test.remotecompose.ui.topbar.MainTopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.remote.creation.compose.capture.captureSingleRemoteDocument
import androidx.compose.remote.creation.compose.layout.RemoteComposable
import androidx.compose.remote.creation.compose.layout.RemoteFitBox
import androidx.compose.remote.creation.compose.modifier.RemoteModifier
import androidx.compose.remote.creation.compose.modifier.border
import androidx.compose.remote.creation.compose.modifier.fillMaxSize
import androidx.compose.remote.creation.compose.modifier.padding
import androidx.compose.remote.creation.compose.state.rc
import androidx.compose.remote.creation.compose.state.rdp
import androidx.compose.remote.tooling.preview.RemoteContentPreview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun RemoteCreatorPage(
    onRedirectToPlayer: (info: ByteArray) -> Unit,
    onBack: () -> Unit,
) {
    RemoteCreatorUI(
        onRedirectToPlayer = onRedirectToPlayer,
        Modifier
            .safeDrawingPadding()
            .fillMaxSize(),
        onBack = onBack,
    )
}


@Composable
private fun RemoteCreatorUI(
    onRedirectToPlayer: (info: ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    //val document = rememberRemoteDocument() { RemoteContent() } //TODO use this when stable

    fun onSendToPlayer() {
        coroutineScope.launch {
            val document = captureSingleRemoteDocument(context) {
                RemoteContent()
            }
            onRedirectToPlayer(document.bytes)
        }
    }

    Column(modifier) {
        MainTopAppBar(
            "Simple Example",
            Modifier.fillMaxWidth(),
            navigationIcon = { MainBackIcon(onBack) },
        )

        RemoteContentPreview(
            Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            RemoteContent()
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = ::onSendToPlayer,
            Modifier.align(Alignment.CenterHorizontally),
        ) {
            Text("See the design in the player")
        }
    }
}

@RemoteComposable
@Composable
private fun RemoteContent() {
    RemoteFitBox(
        RemoteModifier
            .fillMaxSize()
            .padding(16.rdp)
            .border(1.rdp, MaterialTheme.colorScheme.primary.rc),
    ) {
        AdaptiveTabletVariantPage(
            RemoteModifier.fillMaxSize(),
        )

        AdaptiveCompactVariantPage(
            RemoteModifier.fillMaxSize(),
        )
    }
}


@Preview(
    name = "Fold",
    locale = "en", //fixme workaround for Remote Compose's tooling messing up locales
    showBackground = true,
    device = "spec:width=673dp,height=841dp",
)
@Preview(
    name = "Phone",
    locale = "en", //fixme workaround for Remote Compose's tooling messing up locales
    showBackground = true,
    device = "spec:width=411dp,height=891dp",
)
@Composable
private fun RemoteCreatorUI_Preview() {
    AppTheme {
        RemoteCreatorUI(
            onRedirectToPlayer = {},
            Modifier.fillMaxSize(),
        )
    }
}