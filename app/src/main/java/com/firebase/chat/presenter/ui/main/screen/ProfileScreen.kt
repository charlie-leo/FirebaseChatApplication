package com.firebase.chat.presenter.ui.main.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebase.chat.ui.theme.Gray
import com.firebase.chat.ui.theme.White
import com.firebase.chat.ui.util.HeightSpacer
import com.firebase.chat.ui.util.ImageCircle
import com.firebase.chat.ui.util.ProfileEditBottomSheet
import com.firebase.chat.ui.util.ThemeSolidButton
import com.firebase.chat.ui.util.WidthSpacer

/**
 * Created by Charles Raj I on 04/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
@Composable
fun ProfileScreen() {

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) { constraints

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 20.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    tint = White
                )
                WidthSpacer()
                Text(text = "Profile", color = White, fontSize = 20.sp)
            }


            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                ImageCircle(
                    size = 120.dp
                )
                WidthSpacer(width = 20.dp)
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(text = "Julie Mozal", fontSize = 20.sp, color = White)
                    HeightSpacer(height = 5.dp)
                    Text(text = "+145634562", fontSize = 15.sp, color = Color.Gray)
                    HeightSpacer(height = 5.dp)
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .height(30.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = White,
                            containerColor = Gray,
                            disabledContentColor = Color.Gray,
                            disabledContainerColor = Color.Gray
                        ),
                        border = BorderStroke(width = 1.dp, color = Color.Transparent),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        Text(text = "Edit")
                    }
                }
            }
        }

//        ProfileEditBottomSheet()

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}