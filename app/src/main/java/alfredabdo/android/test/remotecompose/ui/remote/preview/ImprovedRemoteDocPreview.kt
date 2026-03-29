package alfredabdo.android.test.remotecompose.ui.remote.preview

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.remote.player.compose.RemoteDocumentPlayer
import androidx.compose.remote.player.core.RemoteDocument
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity

// Source: androidx.compose.remote.tooling.preview.RemoteDocPreview

/**
 * This version takes into account the dimensions of the container by using [BoxWithConstraints], as opposed to the
 * whole document.
 */
@SuppressLint("RestrictedApi")
@Composable
fun ImprovedRemoteDocPreview(
    @SuppressLint("RestrictedApi") remoteDocument: RemoteDocument,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier = modifier) {
        val density = LocalDensity.current
        RemoteDocumentPlayer(
            document = remoteDocument.document,
            documentWidth = with(density) { maxWidth.roundToPx() },
            documentHeight = with(density) { maxHeight.roundToPx() },
            modifier = Modifier.fillMaxSize(),
            debugMode = 0,
            onNamedAction = { _, _, _ -> },
        )
    }
}