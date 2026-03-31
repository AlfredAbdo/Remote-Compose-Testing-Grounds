package alfredabdo.android.test.remotecompose.examples.image

import alfredabdo.android.test.remotecompose.annotations.DefaultPreview
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
import androidx.compose.material3.Text
import androidx.compose.remote.player.compose.RemoteDocumentPlayer
import androidx.compose.remote.player.core.RemoteDocument
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RemotePlayerPage(
    infoToDisplay: ByteArray,
    onBack: () -> Unit,
) {
    RemotePlayerUI(
        infoToDisplay,
        Modifier
            .safeDrawingPadding()
            .fillMaxSize(),
        onBack = onBack,
    )
}


@SuppressLint("RestrictedApi")
@Composable
private fun RemotePlayerUI(
    infoToDisplay: ByteArray?,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
) {
    Column(
        modifier,
    ) {
        MainTopAppBar(
            "Image Example",
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
                //bitmapLoader = ,
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
            Modifier.fillMaxSize(),
        )
    }
}