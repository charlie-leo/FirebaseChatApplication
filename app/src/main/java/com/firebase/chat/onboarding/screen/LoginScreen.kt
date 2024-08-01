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
import com.firebase.chat.onboarding.screen.Navigation.OTP_SCREEN

/**
 * Created by Charles Raj I on 30/07/24
 * @project FirebaseChatApplication
 * @author Charles Raj
 */
@Composable
fun LoginScreen(navController: NavHostController) {

    val mobileNumber = remember {
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
                text = "Enter you mobile number to login.",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = mobileNumber.value,
                onValueChange = {
                    mobileNumber.value = it
                },
                label = {
                    Text(text = "Mobile Number")
                },
                placeholder = {
                    Text(text = "Mobile Number")
                },
                leadingIcon = {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = "")
                        Text(text = "+91")
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
                    .testTag("mobileNumberTextFieldTag")
            )
            
            Button(onClick = {
                navController.navigate(OTP_SCREEN)
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .testTag("sendOtpBtnTag")
                ) {
                Text(text = "Send OTP")
            }
            
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController() // Initialize NavController
    LoginScreen(navController)
}