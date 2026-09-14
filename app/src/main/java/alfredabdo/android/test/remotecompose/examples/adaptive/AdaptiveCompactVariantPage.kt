package alfredabdo.android.test.remotecompose.examples.adaptive

import androidx.compose.material3.MaterialTheme
import androidx.compose.remote.creation.compose.layout.RemoteArrangement
import androidx.compose.remote.creation.compose.layout.RemoteBox
import androidx.compose.remote.creation.compose.layout.RemoteColumn
import androidx.compose.remote.creation.compose.layout.RemoteComposable
import androidx.compose.remote.creation.compose.layout.RemoteText
import androidx.compose.remote.creation.compose.modifier.RemoteModifier
import androidx.compose.remote.creation.compose.modifier.background
import androidx.compose.remote.creation.compose.modifier.fillMaxSize
import androidx.compose.remote.creation.compose.modifier.fillMaxWidth
import androidx.compose.remote.creation.compose.modifier.padding
import androidx.compose.remote.creation.compose.state.rc
import androidx.compose.remote.creation.compose.state.rf
import androidx.compose.remote.creation.compose.state.rs
import androidx.compose.remote.tooling.preview.RemoteComponentPreview
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@RemoteComposable
@Composable
fun AdaptiveCompactVariantPage(
    modifier: RemoteModifier = RemoteModifier,
) {
    RemoteColumn(
        modifier
            .padding(16.rf),
        verticalArrangement = RemoteArrangement.Center,
    ) {
        RemoteText(
            "This is the design on a phone".rs,
            RemoteModifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }
}


@RemoteComponentPreview
@Composable
private fun AdaptiveCompactVariantPage_Preview() {
    RemoteBox(
        RemoteModifier.background(MaterialTheme.colorScheme.background.rc),
    ) {
        AdaptiveCompactVariantPage(
            RemoteModifier.fillMaxSize(),
        )
    }
}