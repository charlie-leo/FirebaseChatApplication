package com.firebase.chat.presenter.ui.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebase.chat.ui.theme.White
import com.firebase.chat.ui.util.HeightSpacer
import com.firebase.chat.ui.util.WidthSpacer

/**
 * Created by Charles Raj I on 04/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
@Composable
fun SearchScreen() {

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
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    tint = White
                )
                WidthSpacer()
                Text(text = "Search", color = White, fontSize = 20.sp)
            }


        }

        OutlinedTextField(
            value = "",
            onValueChange = {},
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = White,
                focusedBorderColor = White
            ),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)

            ,
            shape = RoundedCornerShape(25.dp),
            label = {
                Text(text = "Search User", color = Color.Gray)
            }
        )

        HeightSpacer(height = 20.dp)

        LazyColumn(
            reverseLayout = false,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(3){
//                ChatItem(userItem)
            }
        }

    }




}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}