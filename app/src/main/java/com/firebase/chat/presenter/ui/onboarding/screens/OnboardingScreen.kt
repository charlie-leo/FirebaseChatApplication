package com.firebase.chat.presenter.ui.onboarding.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.firebase.chat.R
import com.firebase.chat.presenter.ui.onboarding.OnboardingNavigationObject
import com.firebase.chat.ui.util.HeightSpacer
import com.firebase.chat.ui.util.ThemeButton
import com.firebase.chat.ui.util.ThemeSolidButton
import com.firebase.chat.ui.util.WidthSpacer

/**
 * Created by Charles Raj I on 03/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
@Composable
fun OnboardingScreen(navController: NavHostController) {


//    CustomPlayerView(
//        videoFile = RawResourceDataSource.buildRawResourceUri(R.raw.globe_video),
//        modifier = Modifier.fillMaxWidth())

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        constraints

        Image(
            painter = painterResource(id = R.mipmap.globe_img),
            contentDescription = "Globe Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.9f)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(color = Color.Gray.copy(alpha = 0.0f))
        ) {
            Column(
                    modifier = Modifier

                            .weight(1f)
                    ,
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Discover New Chats",
                    color = Color.White,
                    fontSize = 24.sp,
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                )
                Text(
                    text = "Stay Connected Anytime, Anywhere",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }
            Column(
                    modifier = Modifier
                            .weight(1f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chat_u_logo),
                    contentDescription = "chat u logo",
                    modifier = Modifier
                        .width(150.dp)
                        .offset(x = -(20.dp))
                )
            HeightSpacer()
                Text(
                    text = "Welcome to Chat-U!",
                    color = Color.White,
                    fontSize = 24.sp
                )
                HeightSpacer()
                Text(
                    text = "Chat securely with anyone, anywhere, and enjoy seamless conversations.",
                    color = Color.White,
                    fontSize = 12.sp
                )
                HeightSpacer(height = 80.dp)
                
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    ThemeButton(
                        text = "LOGIN",
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        navController.navigate(OnboardingNavigationObject.LOGIN_SCREEN)
                    }
                    WidthSpacer(width = 20.dp)
                    ThemeSolidButton(
                        text = "SIGNUP",
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        navController.navigate(OnboardingNavigationObject.SIGNUP_SCREEN)
                    }
                }
                HeightSpacer(height = 20.dp)
            }
        }

    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingScreenPreview() {
    val navController = rememberNavController()
    OnboardingScreen(navController)
}