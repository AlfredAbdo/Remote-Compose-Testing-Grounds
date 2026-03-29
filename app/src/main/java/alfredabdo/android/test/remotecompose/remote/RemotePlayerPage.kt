package alfredabdo.android.test.remotecompose.remote

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
            "Simple Example",
            Modifier.fillMaxWidth(),
            navigationIcon = { MainBackIcon(onBack) },
        )
        Text("Content played is below:")
        Spacer(Modifier.height(16.dp))
        infoToDisplay?.let {
            val document = RemoteDocument(it).document

            RemoteDocumentPlayer(
                document,
                document.width,
                document.height,
                Modifier
                    .weight(1f)
                    .fillMaxWidth(),
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