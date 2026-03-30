package alfredabdo.android.test.remotecompose.examples.counter

import alfredabdo.android.test.remotecompose.annotations.DefaultPreview
import alfredabdo.android.test.remotecompose.ui.remote.preview.ImprovedRemotePreview
import alfredabdo.android.test.remotecompose.ui.theme.AppTheme
import alfredabdo.android.test.remotecompose.ui.topbar.MainBackIcon
import alfredabdo.android.test.remotecompose.ui.topbar.MainTopAppBar
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.remote.creation.compose.action.HostAction
import androidx.compose.remote.creation.compose.action.ValueChange
import androidx.compose.remote.creation.compose.capture.captureSingleRemoteDocument
import androidx.compose.remote.creation.compose.layout.RemoteAlignment
import androidx.compose.remote.creation.compose.layout.RemoteColumn
import androidx.compose.remote.creation.compose.layout.RemoteComposable
import androidx.compose.remote.creation.compose.layout.RemoteSpacer
import androidx.compose.remote.creation.compose.layout.RemoteText
import androidx.compose.remote.creation.compose.modifier.RemoteModifier
import androidx.compose.remote.creation.compose.modifier.border
import androidx.compose.remote.creation.compose.modifier.clickable
import androidx.compose.remote.creation.compose.modifier.fillMaxSize
import androidx.compose.remote.creation.compose.modifier.height
import androidx.compose.remote.creation.compose.modifier.padding
import androidx.compose.remote.creation.compose.state.RemoteColor
import androidx.compose.remote.creation.compose.state.rc
import androidx.compose.remote.creation.compose.state.rdp
import androidx.compose.remote.creation.compose.state.rememberMutableRemoteInt
import androidx.compose.remote.creation.compose.state.rs
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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


@SuppressLint("RestrictedApi")
@Composable
private fun RemoteCreatorUI(
    onRedirectToPlayer: (info: ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

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
            "Counter Example",
            Modifier.fillMaxWidth(),
            navigationIcon = { MainBackIcon(onBack) },
        )

        ImprovedRemotePreview(
            Modifier
                .weight(1f)
                .fillMaxWidth(),
            previewModifier = Modifier.fillMaxSize(),
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

@SuppressLint("RestrictedApi")
@RemoteComposable
@Composable
private fun RemoteContent() {
    val counter = rememberMutableRemoteInt(0)

    RemoteColumn(
        RemoteModifier
            .fillMaxSize()
            .padding(16.rdp)
            .border(1.rdp, MaterialTheme.colorScheme.primary.rc),
        horizontalAlignment = RemoteAlignment.CenterHorizontally,
    ) {
        RemoteText(
            "Counter current value is: ".rs + counter.toRemoteString(3),
            color = RemoteColor(Color.Black),
        )
        RemoteSpacer(RemoteModifier.height(8.rdp))
        RemoteText(
            "Press me to increase the counter above",
            RemoteModifier
                .clickable(
                    ValueChange(counter, counter + 1),
                )
                .padding(16.rdp),
        )
        RemoteSpacer(RemoteModifier.height(4.rdp))
        RemoteText(
            "Press me to get the counter above",
            RemoteModifier
                .clickable(
                    HostAction("use-counter".rs, counter),
                )
                .padding(16.rdp),
        )
    }
}


@DefaultPreview
@Composable
private fun RemoteCreatorUI_Preview() {
    AppTheme {
        RemoteCreatorUI(
            onRedirectToPlayer = {},
            Modifier.fillMaxSize(),
        )
    }
}