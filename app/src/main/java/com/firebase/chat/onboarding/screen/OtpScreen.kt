package com.firebase.chat.onboarding.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * Created by Charles Raj I on 30/07/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
@Composable
fun OtpScreen(navController: NavHostController) {

    val otpNumber = remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {


        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Text(
                text = "Hey!!",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Enter you OTP received in you mobile number",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = otpNumber.value,
                onValueChange = {
                    otpNumber.value = it
                },
                label = {
                    Text(text = "OTP")
                },
                placeholder = {
                    Text(text = "OTP")
                },
                leadingIcon = {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(imageVector = Icons.Rounded.Info, contentDescription = "")
//                        Text(text = "+91")
                    }
                },
                maxLines = 1,
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(

                ),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .testTag("otpTextFieldTag")
            )

            Button(onClick = {
                navController.navigate(Navigation.LOGIN_SCREEN)
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .testTag("otpButtonTag")
            ) {
                Text(text = "Verify OTP")
            }

        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OtpScreen() {
    val navController = rememberNavController() // Initialize NavController
    OtpScreen(navController)
}