package alfredabdo.android.test.remotecompose.ui.remote.preview

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.remote.creation.compose.capture.captureSingleRemoteDocument
import androidx.compose.remote.creation.compose.layout.RemoteComposable
import androidx.compose.remote.player.core.RemoteDocument
import androidx.compose.remote.tooling.preview.RemotePreview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.runBlocking

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
    val context = LocalContext.current

    val document = remember {
        runBlocking {
            RemoteDocument(
                captureSingleRemoteDocument(context = context, content = content)
                    .bytes
            )
        }
    }

//    LaunchedEffect(Unit) {} //fixme alfred.abdo: why?

    Box(modifier = modifier) {
        ImprovedRemoteDocPreview(document, previewModifier)
    }
}