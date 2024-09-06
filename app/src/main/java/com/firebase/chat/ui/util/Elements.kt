package com.firebase.chat.ui.util

import android.net.Uri
import android.widget.FrameLayout.LayoutParams
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.firebase.chat.R
import com.firebase.chat.ui.theme.White
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by Charles Raj I on 03/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */



@Composable
fun CustomPlayerView(
    videoFile : Uri,
    modifier: Modifier
) {

    val context = LocalContext.current
    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoFile))
            prepare()
            playWhenReady = true
        }
    }

    AndroidView(
        factory = {
            PlayerView(context).apply {
                player = exoPlayer
                useController = true
                layoutParams = LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )
            }
        },
        update = { view ->

        }
    )
}

@Composable
fun HeightSpacer(height : Dp = 10.dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun WidthSpacer(width : Dp = 10.dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun ThemeButton(
    text: String,
    modifier: Modifier = Modifier,
    contentColor: Color = White,
    containerColor: Color = Color.Transparent,
    borderColor: Color = White,
    borderRadius: Dp = 30.dp,
    onClick : () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(borderRadius),
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor,
            disabledContentColor = Color.Gray,
            disabledContainerColor = Color.Gray
        ),
        border = BorderStroke(width = 1.dp, color = borderColor)
    ) {
        Text(text = text)
    }
}

@Composable
fun ThemeSolidButton(
    text: String,
    modifier: Modifier = Modifier,
    contentColor: Color = Color.Black,
    containerColor: Color = White,
    borderColor: Color = White,
    borderRadius: Dp = 30.dp,
    onClick : () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(borderRadius),
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor,
            disabledContentColor = Color.Gray,
            disabledContainerColor = Color.Gray
        ),
        border = BorderStroke(width = 1.dp, color = borderColor)
    ) {
        Text(text = text)
    }
}

@Composable
fun MobileNumberTextField(
    label : String,
    value : String,
    valueChange : (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = valueChange,
        label = {
            Text(text = label)
        },
        leadingIcon = {
            Row (
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(painter = painterResource(id = R.drawable.contact_icon), contentDescription = "")
//                WidthSpacer(width = 3.dp)
//
            }
        },
        prefix = {
            Text(text = "+1", fontSize = 16.sp)
        },
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedLeadingIconColor = Color.White,
            unfocusedLeadingIconColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color.White
        )
    )
}

@Composable
fun LeadingIconTextField(
    label : String,
    value : String,
    valueChange : (String) -> Unit,
    leadingIcon : Int = R.drawable.person_icon
) {
    TextField(
        value = value,
        onValueChange = valueChange,
        label = {
            Text(text = label)
        },
        leadingIcon = {
            Row (
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(painter = painterResource(id = leadingIcon), contentDescription = "")
//                WidthSpacer(width = 3.dp)
//                Text(text = "+1", fontSize = 16.sp)
            }
        },
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedLeadingIconColor = Color.White,
            unfocusedLeadingIconColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color.White
        )
    )
}


@Composable
fun ImageCircle(size: Dp = 80.dp, image: Int = R.mipmap.dummy_profile) {

    Box (
        modifier = Modifier
            .size(size)
            .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
            .clip(shape = CircleShape)
    ) {
        Image(
            painter = painterResource(id = R.mipmap.dummy_profile),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

fun formatTimestamp(timestamp: Long) : String {
    val dataFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)
    val data = Date(timestamp)
    return dataFormat.format(data)
}