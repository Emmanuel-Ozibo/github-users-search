package com.example.sculptor.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sculptor.R
import com.example.sculptor.SculptorTheme

@Composable
fun CircularImage(
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    type: Type = Type.Local,
    imageUrl: String? = null,
    size: Dp,
) {
    if (type == Type.Local && painter != null) {
        Image(
            modifier =
                modifier
                    .size(size)
                    .clip(CircleShape),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    } else {
        AsyncImage(
            modifier =
                modifier
                    .size(size)
                    .clip(CircleShape),
            model =
                ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
            placeholder = painterResource(R.drawable.random),
            contentDescription = stringResource(R.string.sculptor_default_image_description),
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview
@Composable
private fun CircularImagePreview() {
    SculptorTheme {
        CircularImage(
            painter = painterResource(id = R.drawable.random),
            size = 100.dp,
        )
    }
}
