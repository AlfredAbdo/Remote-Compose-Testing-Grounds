package alfredabdo.android.test.remotecompose.remote

import alfredabdo.android.test.remotecompose.annotations.DefaultPreview
import alfredabdo.android.test.remotecompose.ui.remote.preview.ImprovedRemotePreview
import alfredabdo.android.test.remotecompose.ui.theme.AppTheme
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.remote.creation.compose.capture.captureSingleRemoteDocument
import androidx.compose.remote.creation.compose.layout.RemoteAlignment
import androidx.compose.remote.creation.compose.layout.RemoteBox
import androidx.compose.remote.creation.compose.layout.RemoteComposable
import androidx.compose.remote.creation.compose.layout.RemoteText
import androidx.compose.remote.creation.compose.modifier.RemoteModifier
import androidx.compose.remote.creation.compose.modifier.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun RemoteCreatorPage(
    onRedirectToPlayer: (info: ByteArray) -> Unit,
) {
    RemoteCreatorUI(
        onRedirectToPlayer = onRedirectToPlayer,
        Modifier
            .safeDrawingPadding()
            .fillMaxSize(),
    )
}


@SuppressLint("RestrictedApi")
@Composable
private fun RemoteCreatorUI(
    onRedirectToPlayer: (info: ByteArray) -> Unit,
    modifier: Modifier = Modifier,
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

@RemoteComposable
@Composable
private fun RemoteContent() {
    RemoteBox(
        RemoteModifier.fillMaxSize(),
        contentAlignment = RemoteAlignment.TopCenter,
    ) {
        RemoteText("Hello World!")
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