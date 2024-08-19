package com.firebase.chat.presenter.ui.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.firebase.chat.R
import com.firebase.chat.presenter.ui.main.event.MainScreenAction
import com.firebase.chat.presenter.ui.main.event.MainScreenEvent
import com.firebase.chat.ui.theme.White
import com.firebase.chat.ui.util.HeightSpacer
import com.firebase.chat.ui.util.ImageCircle
import com.firebase.chat.ui.util.WidthSpacer

/**
 * Created by Charles Raj I on 04/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
@Composable
fun ChatScreen(
    navController: NavHostController,
    mainScreenEvent: State<MainScreenEvent>,
    action: (MainScreenAction) -> Unit
) {


    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column (
            modifier = Modifier
//                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row (
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        tint = White
                    )
                    WidthSpacer()
                    ImageCircle(size = 45.dp)
                    WidthSpacer(width = 15.dp)
                    Text(text = mainScreenEvent.value.selectedUser?.userName ?: "", color = White, fontSize = 20.sp)
                }

                Icon(
                    painter = painterResource(id = R.drawable.dot_menu_icon),
                    contentDescription = "",
                    tint = White
                )
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Gray
            )
        }

        LazyColumn(
            reverseLayout = true,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            item {
                SendChatItem()
            }
            item { 
                ReceiveChatItem()
            }
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
        ){
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = White,
                        focusedBorderColor = White
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                    ,
                    shape = RoundedCornerShape(25.dp),
                    label = {
                        Text(text = "Type message", color = Color.Gray)
                    }
                )

                WidthSpacer()

                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = White,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .size(50.dp),
                    contentPadding = PaddingValues(10.dp),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.send_icon),
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier
                            .fillMaxSize()
                        )
                }

            }
//            HeightSpacer(height = 20.dp)

        }

    }


}

@Composable
fun SendChatItem(){
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 15.dp)
    ) { constraints
        
        Column(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.8f)
                .align(Alignment.CenterEnd)
        ) {
            Text(text = "Hey, I’m good. How about you",
                color = Color.Black,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = White, shape = RoundedCornerShape(10.dp))
                    .padding(vertical = 15.dp, horizontal = 10.dp)
                )
            HeightSpacer(height = 5.dp)
            Text(text = "3:09 pm",
                color = Color.Gray,
                fontSize = 8.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 5.dp)
                )
        }
        
    }   
}

@Composable
fun ReceiveChatItem(){
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 15.dp)
    ) { constraints

        Column(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.8f)
                .align(Alignment.CenterStart)
        ) {
            Text(text = "Hey, What’s Up?",
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
                    .padding(vertical = 15.dp, horizontal = 10.dp)
            )
            HeightSpacer(height = 5.dp)
            Text(text = "3:09 pm",
                color = Color.Gray,
                fontSize = 8.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(end = 5.dp)
                    .fillMaxWidth()
            )
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatScreenPreview() {
//    ChatScreen()
}