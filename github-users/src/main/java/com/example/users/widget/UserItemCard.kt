package com.example.users.widget


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import coil.load
import coil.transform.CircleCropTransformation
import com.example.sculptor.SculptorTheme
import com.example.users.R
import com.example.users.databinding.UserItemCardBinding



@Composable
fun UserItemCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    fullName: String,
    profileDescription: String,
    location: String,
    email: String,
    tag: String,
    onCardClick: () -> Unit = {},
) {
    AndroidViewBinding(
        modifier = modifier,
        factory = UserItemCardBinding::inflate
    ) {
        txtFullName.text = fullName
        txtUserTag.text = tag
        txtUserBio.text = profileDescription
        txtUserLocation.text = location
        txtUserEmail.text = email
        userCard.setOnClickListener { onCardClick() }
        profilePicture.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_people)
            transformations(CircleCropTransformation())
        }
    }
}


@Preview
@Composable
private fun UserItemCardPreview() {
    SculptorTheme {
        UserItemCard(
            imageUrl = "https://placehold.co/600x400",
            fullName = "Paige Brown",
            profileDescription = "This is a random bio, which will be replace with actual content",
            location = "New York",
            email = "paige@gmail.com",
            tag = "paige"
        )
    }
}
