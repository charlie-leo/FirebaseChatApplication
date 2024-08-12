package com.firebase.chat.onboarding.screen

import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout.LayoutParams
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.datasource.RawResourceDataSource
import androidx.navigation.compose.rememberNavController
import com.firebase.chat.R
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

/**
 * Created by Charles Raj I on 01/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
@Composable
fun VideoPlayerScreen() {

    val pagerState = rememberPagerState (initialPage = 0, pageCount = {
        4
    })
//    RawResourceDataSource.buildRawResourceUri(R.raw.video_1)
    Column {
//            VerticalPager(state = pagerState ) { page ->
//                Player(
//                    modifier = Modifier
//                    .fillMaxWidth(),
//                    com.google.android.exoplayer2.upstream.RawResourceDataSource.buildRawResourceUri(R.raw.)
//                )

//                Player(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    RawResourceDataSource.buildRawResourceUri(R.raw.video_2)
//                )
//
//                Player(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    RawResourceDataSource.buildRawResourceUri(R.raw.video_3)
//                )
//
//                Player(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    RawResourceDataSource.buildRawResourceUri(R.raw.video_1)
//                )
////            }
    }
}

@Composable
fun Player(modifier: Modifier, videoFile : Uri) {
    val context  = LocalContext.current
    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoFile))
            prepare()
            playWhenReady = true
        }
    }


    Column(
        modifier  = Modifier.fillMaxSize()
    ) {
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
            update = {

            }
        )

        Row (
            modifier = Modifier.fillMaxWidth()
                .size(100.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
//            IconButton(onClick = {
//                if (exoPlayer.isPlaying)  exoPlayer.pause() else exoPlayer.play()
//            }) {
//                if (exoPlayer.isPlaying) {
//                    Icon(
//                        imageVector =  Icons.Outlined.Refresh,
//                        contentDescription = "",
//                        modifier = Modifier
//                            .clickable {
//                                if (exoPlayer.isPlaying)  exoPlayer.pause() else exoPlayer.play()
//                            }
//                    )
//                } else {
//                    Icon(
//                        imageVector = Icons.Rounded.PlayArrow,
//                        contentDescription = "",
//                        modifier = Modifier
//                            .clickable {
//                                if (exoPlayer.isPlaying)  exoPlayer.pause() else exoPlayer.play()
//                            }
//                    )
//                }
//            }
        }

    }





//    DisposableEffect(
//        AndroidView(
//            factory = {
//                PlayerView(context).apply {
//                    player = exoPlayer
//                    useController = false
//                    layoutParams = android.widget.FrameLayout.LayoutParams(
//                        android.widget.FrameLayout.LayoutParams.MATCH_PARENT,
//                        android.widget.FrameLayout.LayoutParams.MATCH_PARENT
//                    )
//                }
//            },
//            modifier = modifier
//        )
//    ) {
//        onDispose {
//            exoPlayer.release()
//        }
//    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoPlayerScreenPreview() {
    val navController = rememberNavController() // Initialize NavController
    VideoPlayerScreen()
}