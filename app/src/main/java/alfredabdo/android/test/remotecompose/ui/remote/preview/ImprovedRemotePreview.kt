package alfredabdo.android.test.remotecompose.ui.remote.preview

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.remote.creation.compose.capture.RememberRemoteDocumentInline
import androidx.compose.remote.creation.compose.layout.RemoteComposable
import androidx.compose.remote.player.core.RemoteDocument
import androidx.compose.remote.tooling.preview.RemotePreview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

// Source: androidx.compose.remote.tooling.preview.RemotePreview

/** Display a Remote Compose Composable in the Android Studio Preview.
 *
 * This version allows you to pass other modifiers to the [Box] and content, as opposed to [RemotePreview].*/
@SuppressLint("RestrictedApi")
@Composable
fun ImprovedRemotePreview(
    modifier: Modifier = Modifier,
    previewModifier: Modifier = Modifier,
    content: @RemoteComposable @Composable () -> Unit,
) {
    var documentState by remember { mutableStateOf<RemoteDocument?>(null) }

    Box(modifier = modifier) {
        @Suppress("COMPOSE_APPLIER_CALL_MISMATCH") // b/446706254
        RememberRemoteDocumentInline(
            onDocument = { doc ->
                println("Document generated: $doc")
                if (documentState == null) {
                    // Generate seems to get called again with a partial document
                    // Essentially re-recording but with existing state, so document is incomplete
                    documentState = RemoteDocument(doc)
                }
            },
            content = content,
        )

        documentState?.let { ImprovedRemoteDocPreview(it, previewModifier) }
    }
}