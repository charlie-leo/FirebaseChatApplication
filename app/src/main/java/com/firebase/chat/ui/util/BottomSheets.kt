package com.firebase.chat.ui.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.firebase.chat.ui.theme.White

/**
 * Created by Charles Raj I on 04/08/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditBottomSheet() {

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { },
        properties = ModalBottomSheetDefaults.properties(
            shouldDismissOnBackPress = true
        ),
        containerColor = Color.Black,
        contentColor = White
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    ImageCircle()
                }

                ThemeSolidButton(text = "Gallery") {

                }

            }

            HeightSpacer(height = 60.dp)
        }
    }

}