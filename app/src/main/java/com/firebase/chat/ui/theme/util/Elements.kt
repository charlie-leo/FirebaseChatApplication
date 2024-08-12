package com.firebase.chat.ui.theme.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by Charles Raj I on 04/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */


@Composable
fun HeightSpacer(height : Dp = 10.dp){
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun WidthSpacer(width : Dp = 10.dp){
    Spacer(modifier = Modifier.width(width))
}