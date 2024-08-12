package com.firebase.chat

import android.os.Bundle
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsCompat.Type
import kotlin.properties.Delegates

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

//            val windowInsertConroller = WindowCompat.getInsetsController(window,window.decorView)
//            windowInsertConroller.hide (Type.systemBars())
//            windowInsertConroller.show()


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Green)
                    .windowInsetsPadding(WindowInsets.safeContent)
                ,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Top Text")
                TextField(value = "", onValueChange = {}, )
                Text(text = "Bottom Text Text")

            }

        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedContent() {

    var showDetails by remember {
        mutableStateOf(false)
    }


    SharedTransitionLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Green)
    ) {

        AnimatedContent(
            targetState = showDetails,
            label = "basic"
        ) { targetState ->

            if (!targetState){
                MainContent(onShowDetails = {showDetails = true}, sharedTransitionScope = this@SharedTransitionLayout , animatedVisibilityScope = this@AnimatedContent )
            } else {
                DetailsContent(
                    onShowDetails = {showDetails = false }, sharedTransitionScope = this@SharedTransitionLayout , animatedVisibilityScope = this@AnimatedContent
                )
            }

        }


    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainContent(
    onShowDetails: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Row (
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
            .clickable { onShowDetails() }
            .padding(20.dp)
    ) {
        with(sharedTransitionScope){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.mipmap.dummy_profile),
                    contentDescription = "",
                    modifier = Modifier
                        .sharedElement(
                            rememberSharedContentState(key = "image"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.FillBounds
                    )

                Text(text = "Jenifer Blezzz", fontSize = 16.sp,
                    modifier = Modifier
                        .sharedElement(
                            rememberSharedContentState(key = "text"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsContent(
    onShowDetails: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column (
        modifier = Modifier
            .padding(vertical = 50.dp)
            .fillMaxSize()
            .background(Color.Gray)
            .clickable { onShowDetails() }
    ) {
        with(sharedTransitionScope){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.mipmap.dummy_profile),
                    contentDescription = "",
                    modifier = Modifier
                        .sharedElement(
                            rememberSharedContentState(key = "image"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .size(300.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.FillBounds
                )

                Text(text = "Jenifer Blezzz", fontSize = 20.sp,
                    modifier = Modifier
                        .sharedElement(
                            rememberSharedContentState(key = "text"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                )
            }
        }
    }
}





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    SharedContent()
}