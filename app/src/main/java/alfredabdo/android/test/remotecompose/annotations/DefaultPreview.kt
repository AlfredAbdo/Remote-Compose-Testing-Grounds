package alfredabdo.android.test.remotecompose.annotations

import androidx.compose.ui.tooling.preview.Preview

@Preview(
    locale = "en", //fixme workaround for Remote Compose's tooling messing up locales
    showBackground = true,
)
annotation class DefaultPreview
