package alfredabdo.android.test.remotecompose.examples.adaptive

import androidx.compose.material3.MaterialTheme
import androidx.compose.remote.creation.compose.layout.RemoteArrangement
import androidx.compose.remote.creation.compose.layout.RemoteBox
import androidx.compose.remote.creation.compose.layout.RemoteColumn
import androidx.compose.remote.creation.compose.layout.RemoteComposable
import androidx.compose.remote.creation.compose.layout.RemoteRow
import androidx.compose.remote.creation.compose.layout.RemoteText
import androidx.compose.remote.creation.compose.modifier.RemoteModifier
import androidx.compose.remote.creation.compose.modifier.background
import androidx.compose.remote.creation.compose.modifier.fillMaxHeight
import androidx.compose.remote.creation.compose.modifier.fillMaxSize
import androidx.compose.remote.creation.compose.modifier.fillMaxWidth
import androidx.compose.remote.creation.compose.modifier.padding
import androidx.compose.remote.creation.compose.modifier.width
import androidx.compose.remote.creation.compose.modifier.widthIn
import androidx.compose.remote.creation.compose.state.rc
import androidx.compose.remote.creation.compose.state.rdp
import androidx.compose.remote.creation.compose.state.rf
import androidx.compose.remote.creation.compose.state.rs
import androidx.compose.remote.tooling.preview.RemoteComponentPreview
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@RemoteComposable
@Composable
fun AdaptiveTabletVariantPage(
    modifier: RemoteModifier = RemoteModifier,
) {
    RemoteRow(
        modifier,
    ) {
        RemoteColumn(
            RemoteModifier
                .fillMaxHeight()
                .widthIn(min = 250.rdp)
                .weight(1.rf)
                .padding(16.rf),
            verticalArrangement = RemoteArrangement.Center,
        ) {
            RemoteText(
                "[option to select]".rs,
                RemoteModifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
        RemoteBox(
            RemoteModifier
                .background(Color.Black.rc)
                .fillMaxHeight()
                .width(1.rdp),
        )
        RemoteColumn(
            RemoteModifier
                .fillMaxHeight()
                .widthIn(min = 250.rdp)
                .weight(1.rf)
                .padding(16.rf),
            verticalArrangement = RemoteArrangement.Center,
        ) {
            RemoteText(
                "Here are the details that will only be shown on bigger devices".rs,
                RemoteModifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
}


@RemoteComponentPreview
@Composable
private fun AdaptiveTabletVariantPage_Preview() {
    RemoteBox(
        RemoteModifier.background(MaterialTheme.colorScheme.background.rc),
    ) {
        AdaptiveTabletVariantPage(
            RemoteModifier.fillMaxSize(),
        )
    }
}