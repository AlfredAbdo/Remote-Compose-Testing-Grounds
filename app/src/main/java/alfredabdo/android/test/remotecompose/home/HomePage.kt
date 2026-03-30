package alfredabdo.android.test.remotecompose.home

import alfredabdo.android.test.remotecompose.annotations.DefaultPreview
import alfredabdo.android.test.remotecompose.examples.simple.SimpleRoute
import alfredabdo.android.test.remotecompose.ui.theme.AppTheme
import alfredabdo.android.test.remotecompose.ui.topbar.MainTopAppBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey

@Composable
fun HomePage(
    onRedirectTo: (route: NavKey) -> Unit,
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
    onRedirectTo: (route: NavKey) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        MainTopAppBar(
            "Remote Compose",
            Modifier.fillMaxWidth(),
        )

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            item {
                Item(
                    "Simple Example",
                    onClick = { onRedirectTo(SimpleRoute.RemoteCreator) },
                    Modifier.fillMaxWidth(),
                )
            }

            //...
        }
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
        Text(
            label,
            Modifier.weight(1f),
        )
        Icon(
            Icons.AutoMirrored.Default.ArrowForward,
            label,
        )
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