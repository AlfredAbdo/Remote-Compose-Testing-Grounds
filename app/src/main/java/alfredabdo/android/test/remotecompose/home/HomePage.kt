package alfredabdo.android.test.remotecompose.home

import alfredabdo.android.test.remotecompose.MainRoute
import alfredabdo.android.test.remotecompose.annotations.DefaultPreview
import alfredabdo.android.test.remotecompose.ui.theme.AppTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomePage(
    onRedirectTo: (route: MainRoute) -> Unit,
) {
    HomeUI(
        onRedirectTo,
        Modifier
            .safeDrawingPadding()
            .fillMaxSize(),
    )
}


@Composable
private fun HomeUI(
    onRedirectTo: (route: MainRoute) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        item {
            Item(
                "Remote Compose",
                onClick = { onRedirectTo(MainRoute.RemoteCreator) },
                Modifier.fillMaxWidth(),
            )
        }

        //...
    }
}

@Composable
private fun Item(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(label)
    }
}


@DefaultPreview
@Composable
private fun HomeUI_Preview() {
    AppTheme {
        HomeUI(
            onRedirectTo = {},
            Modifier.fillMaxSize(),
        )
    }
}