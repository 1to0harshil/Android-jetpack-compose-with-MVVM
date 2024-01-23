package com.example.interk9compose.ui

import Component.ActionBar
import Component.PrimaryButton
import Component.SpacerDp
import Component.showToast
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.interk9compose.R
import com.example.interk9compose.dataClasses.LoginReq
import com.example.interk9compose.viewmodel.LoginViewModel
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity() : ComponentActivity() {
    private lateinit var c: Context
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyScreen()
            c = LocalContext.current
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MyScreen() {
        var isSecondComposableVisible by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            delay(2000)
            isSecondComposableVisible = true
        }

        AnimatedVisibility(
            visible = !isSecondComposableVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Previewing()
        }

        AnimatedVisibility(
            visible = isSecondComposableVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            LoginView()
        }
    }

    @Composable
    fun Previewing() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Project Logo",
                    modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = "welcome",
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.black
                    )
                )
            }
        }
    }

    @Composable
    fun LoginView() {
        var text_id by remember { mutableStateOf(TextFieldValue("")) }
        var text_pwd by remember { mutableStateOf(TextFieldValue("")) }
        var isLogin by remember { mutableStateOf(false) }
        var isLoding by remember { mutableStateOf(false) }

        viewModel.login.observe(this@SplashActivity) {

            if (it.success){

                isLogin = false
                isLoding = false
                viewModel.login.removeObservers(this@SplashActivity)
            }else
            {
                viewModel.login.removeObservers(this@SplashActivity)
                showToast(c,it.message)
                isLogin = false
                isLoding = false
            }


        }
        if (isLogin) {

            Btn_loginClick(text_id.text, text_pwd.text)


        }

        Scaffold(topBar = { ActionBar("Login") }) {

            Spacer(modifier = Modifier.padding(it))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 70.dp)
            ) {
                Row(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 25.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Project Logo",
                        modifier = Modifier
                            .size(150.dp)
                    )
                }

                SpacerDp(10)

                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "Easy K9",
                        style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(
                            id = R.color.black
                        )
                    )
                }
                SpacerDp(35)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    OutlinedTextField(
                        singleLine = true,
                        value = text_id,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(horizontal = 15.dp),
                        onValueChange = {
                            text_id = it
                        },
                        placeholder = { Text(text = "Enter email Address") },
                        label = { Text("Email id") })

                }
                SpacerDp(15)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    OutlinedTextField(
                        singleLine = true,
                        value = text_pwd,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(horizontal = 15.dp),
                        onValueChange = {
                            text_pwd = it
                        },
                        label = { Text(text = "Password") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation()
                    )

                }
                SpacerDp(5)
                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 15.dp)
                ) {
                    ClickableText(text = AnnotatedString("Forgot password?"), onClick = {
//TODO setClick
                    })
                }

                SpacerDp(15)
                Row {
                    PrimaryButton("Log in",isLoding) {
                        isLoding = true
                        isLogin = true
                    }
                }
                SpacerDp(5)
                Row {
                    PrimaryButton("Register", false) {
                        //ham kuch karenge
                        isLoding = false
                    }
                }
            }

        }
    }


    @Composable
    fun Btn_loginClick(text: String, text_pwd: String) {


        val reqLogin = LoginReq(
            UserName = text,
            DeviceId = "",
            DeviceModel = "",
            DeviceType = "android",
            FirebaseToken = "",
            Password = text_pwd,
            deviceToken = "",
            deviceVersion = ""
        )

        LaunchedEffect(Unit) {
            viewModel.doLogin(reqLogin)
        }
    }
}

