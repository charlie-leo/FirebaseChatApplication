package com.firebase.chat.presenter.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.firebase.chat.presenter.ui.main.viewmodel.MainActivityViewModel
import com.firebase.chat.ui.theme.FirebaseChatApplicationTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mainActivityViewModel: MainActivityViewModel by viewModels<MainActivityViewModel>()

//            val loading = mainActivityViewModel

            var currentUser = FirebaseAuth.getInstance().currentUser

            val scope = rememberCoroutineScope()
            val snackBarHostState = remember {
                SnackbarHostState()
            }
            val snackBarState by mainActivityViewModel.snackBarState.collectAsState()

            FirebaseChatApplicationTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackBarHostState)
                    },
                ) { innerPadding ->
                    val it = innerPadding

                    MainActivityNavigation(mainActivityViewModel)

                    LaunchedEffect(key1 = snackBarState.show) {
                        scope.launch {
                            if (snackBarState.show){
                                snackBarHostState.showSnackbar(
                                    message = snackBarState.message,
                                    if(snackBarState.isError) "Error" else "Success",
                                    duration = SnackbarDuration.Short
                                )
                            }

                        }
                    }

                    if (mainActivityViewModel.isLoading.collectAsState().value){
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.4f)),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(200.dp),
                                strokeCap = StrokeCap.Round,
                                strokeWidth = 8.dp,
                                trackColor = Color.White
                            )
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirebaseChatApplicationTheme {

    }
}