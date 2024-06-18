package com.josu98.twitter_post_challenge

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TwitterColumn(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(enabled = true, state = ScrollState(0))
            .background(color = Color(0xFF12141D)),
        verticalArrangement = Arrangement.Center
    ) {
        TwitterPost()
        TwitterPost()
        TwitterPost()
    }
}

@Composable
private fun TwitterPost() {
    val time by rememberSaveable { mutableStateOf("14H") }
    var comments by rememberSaveable { mutableIntStateOf(1) }
    var retweets by rememberSaveable { mutableIntStateOf(1) }
    var likes by rememberSaveable { mutableIntStateOf(3) }
    Row(
        Modifier
            .background(color = Color(0xFF12141D))
            .padding(14.dp)
            .fillMaxWidth()
    ) {
        //Profile image
        ProfilePicture()

        //Post
        Column {
            PostHeader(time)

            Text(
                text = "A canvas for strange thoughts; for what necessity is there to house every idle thought of those who inhabit the other, stranger world?",
                color = Color(0xFFE7E7E7),
                fontSize = 14.sp,
                letterSpacing = 0.1.sp,
                lineHeight = 18.sp
            )
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "AristiDevs Logo",
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5))
                    .height(200.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InteractionsButton(
                    R.drawable.ic_chat,
                    R.drawable.ic_chat_filled,
                    "Comment",
                    comments,
                    Color(0xFF3FE2F7)
                ) { comments += it }
                InteractionsButton(
                    R.drawable.ic_rt,
                    R.drawable.ic_rt,
                    "Retweet",
                    retweets,
                    Color(0xFF3CE01F)
                ) { retweets += it }
                InteractionsButton(
                    R.drawable.ic_like,
                    R.drawable.ic_like_filled,
                    "Like",
                    likes,
                    Color(0xFFE41837)
                ) { likes += it }
            }


        }
    }

    HorizontalDivider(color = Color(0xFF32343A))
}

@Composable
private fun PostHeader(time: String) {
    Row(Modifier.fillMaxWidth()) {
        Text(
            text = "UserName_",
            color = Color(0xFFE7E7E7),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "@User_handle",
            color = Color(0xFF65676D),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
        Text(
            text = time,
            color = Color(0xFF65676D),
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 4.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.ic_dots),
            contentDescription = "Context menu",
            colorFilter = ColorFilter.tint(
                Color(0xFFE7E7E7)
            ),
            alignment = Alignment.TopEnd,
        )
    }
}

@Composable
private fun ProfilePicture() {
    Column(Modifier.padding(end = 14.dp)) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun InteractionsButton(
    @DrawableRes iconIdActive: Int,
    @DrawableRes iconIdInactive: Int,
    description: String,
    interactions: Int,
    activeColor: Color,
    onIconPressed: (Int) -> Unit
) {
    var isPressed by rememberSaveable { mutableStateOf(false) }

    Row (verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(
                id = if (isPressed) iconIdInactive else iconIdActive
            ),
            contentDescription = description,
            colorFilter = ColorFilter.tint(
                if (isPressed) {
                    activeColor
                } else {
                    Color(0xFF65676D)
                }
            ),
            modifier = Modifier
                .size(22.dp)
                .clickable {
                    isPressed = !isPressed
                    if (isPressed) onIconPressed(1) else onIconPressed(-1)
                })
        Text(
            text = interactions.toString(),
            color = Color(0xFF65676D),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
