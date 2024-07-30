package com.example.users.widget

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sculptor.SculptorTheme
import com.example.sculptor.components.texts.IconText
import com.example.sculptor.components.texts.LinkText
import com.example.users.R

@Composable
fun LocationLink(
    modifier: Modifier = Modifier,
    location: String,
    link: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(SculptorTheme.space.half),
    ) {
        val context = LocalContext.current

        IconText(icon = painterResource(id = R.drawable.ic_location), text = location)
        LinkText(
            painter = painterResource(id = R.drawable.ic_link),
            link = link,
            onLinkClick = {
                Intent(Intent.ACTION_VIEW, Uri.parse(link)).also { intent ->
                    context.startActivity(intent)
                }
            },
        )
    }
}

@Preview
@Composable
private fun LocationLinkPreview() {
    SculptorTheme {
        LocationLink(
            location = "Lagos, Nigeria",
            link = "http://www.paige.com",
        )
    }
}
