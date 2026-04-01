package alfredabdo.android.test.remotecompose.examples.animated

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
import androidx.compose.remote.creation.Rc
import androidx.compose.remote.creation.compose.capture.captureSingleRemoteDocument
import androidx.compose.remote.creation.compose.layout.RemoteAlignment
import androidx.compose.remote.creation.compose.layout.RemoteArrangement
import androidx.compose.remote.creation.compose.layout.RemoteBox
import androidx.compose.remote.creation.compose.layout.RemoteColumn
import androidx.compose.remote.creation.compose.layout.RemoteComposable
import androidx.compose.remote.creation.compose.layout.RemoteOffset
import androidx.compose.remote.creation.compose.modifier.RemoteModifier
import androidx.compose.remote.creation.compose.modifier.background
import androidx.compose.remote.creation.compose.modifier.border
import androidx.compose.remote.creation.compose.modifier.clip
import androidx.compose.remote.creation.compose.modifier.fillMaxSize
import androidx.compose.remote.creation.compose.modifier.offset
import androidx.compose.remote.creation.compose.modifier.padding
import androidx.compose.remote.creation.compose.modifier.size
import androidx.compose.remote.creation.compose.shaders.RemoteLinearGradient
import androidx.compose.remote.creation.compose.shapes.RemoteRoundedCornerShape
import androidx.compose.remote.creation.compose.state.RemoteFloat
import androidx.compose.remote.creation.compose.state.asRemoteDp
import androidx.compose.remote.creation.compose.state.rc
import androidx.compose.remote.creation.compose.state.rdp
import androidx.compose.remote.creation.compose.state.rf
import androidx.compose.remote.creation.compose.state.sin
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
            "Animated Example",
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
    val primaryColor = MaterialTheme.colorScheme.primary.rc
    val secondaryColor = MaterialTheme.colorScheme.secondary.rc

    val time = RemoteFloat(Rc.Time.CONTINUOUS_SEC)
    val cornerRadius = 32.rf / 2.rf * (1.rf + sin(time))
    val brushOffset = 128.rdp.toPx() * (1.rf + sin(time))
    val relativeOffset = 32.rf * sin(time)


    RemoteColumn(
        RemoteModifier
            .fillMaxSize()
            .padding(16.rdp)
            .border(1.rdp, primaryColor),
        horizontalAlignment = RemoteAlignment.CenterHorizontally,
        verticalArrangement = RemoteArrangement.spacedBy(16.rdp, RemoteAlignment.CenterVertically),
    ) {
        RemoteBox(
            RemoteModifier
                .offset(relativeOffset.asRemoteDp(), 0.rdp)
                .size(128.rdp)
                .clip(RemoteRoundedCornerShape(cornerRadius.asRemoteDp()))
                .background(primaryColor)
        )

        //fixme clipping and offset modifiers do not work with RemoteLinearGradient brush.
        RemoteBox(
            RemoteModifier
//                .offset(relativeOffset.asRemoteDp(), 0.rdp)
                .size(128.rdp)
//                .clip(RemoteRoundedCornerShape(cornerRadius.asRemoteDp()))
                .background(
                    RemoteLinearGradient(
                        listOf(primaryColor, secondaryColor),
                        start = RemoteOffset(0f, 0f),
                        end = RemoteOffset(brushOffset, 0f),
                    )
                )
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