package alfredabdo.android.test.remotecompose.examples.canvas.animated

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
import androidx.compose.remote.creation.compose.capture.captureSingleRemoteDocument
import androidx.compose.remote.creation.compose.layout.RemoteCanvas
import androidx.compose.remote.creation.compose.layout.RemoteComposable
import androidx.compose.remote.creation.compose.layout.RemoteOffset
import androidx.compose.remote.creation.compose.modifier.RemoteModifier
import androidx.compose.remote.creation.compose.modifier.border
import androidx.compose.remote.creation.compose.modifier.fillMaxSize
import androidx.compose.remote.creation.compose.modifier.padding
import androidx.compose.remote.creation.compose.state.RemotePaint
import androidx.compose.remote.creation.compose.state.animateRemoteFloat
import androidx.compose.remote.creation.compose.state.rc
import androidx.compose.remote.creation.compose.state.rdp
import androidx.compose.remote.creation.compose.state.rf
import androidx.compose.remote.creation.compose.state.rs
import androidx.compose.remote.creation.compose.state.rsp
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
            "Canvas Animated Example",
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

    RemoteCanvas(
        RemoteModifier
            .fillMaxSize()
            .padding(16.rdp)
            .border(1.rdp, primaryColor),
    ) {
        val padding = 16.rdp.toPx()

        val time = remote.time.ContinuousSec()

        val textPaint = RemotePaint {
            textSize = 12.rsp.toPx() + (2.rsp.toPx() * (1.rf + sin(time)))
        }
        val separatorPadding = 8.rdp.toPx()
        val separatorPaint = RemotePaint {
            color = primaryColor
            strokeWidth = 1.rsp.toPx()
        }
        val circlePaint = RemotePaint {
            color = primaryColor
        }
        val circleRadius = 32.rdp.toPx() + (4.rdp.toPx() * sin(time))

        val circleOffset = animateRemoteFloat(0.rf, duration = 3f, initialValue = 128f)


        drawAnchoredText("Hello!".rs, padding, padding, (-1).rf, 1.rf, paint = textPaint)
        withTransform({
            translate(0.rf, padding + textPaint.textSize + separatorPadding)
            clipRect(padding, 0.rf, width - padding, height)
        }) {
            drawLine(separatorPaint, RemoteOffset(0f, 0f), RemoteOffset(width, 0f))
        }
        drawCircle(circlePaint, RemoteOffset(center.x, center.y + circleOffset), radius = circleRadius)
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