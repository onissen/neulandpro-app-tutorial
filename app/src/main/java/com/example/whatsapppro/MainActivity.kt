package com.example.whatsapppro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.whatsapppro.ui.theme.WhatsAppProTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val question = ChatBubbleData(
            message = "Wie gehts?",
            date = "Gestern",
            isSender = true
        )

        val answer = ChatBubbleData(
            message = "Gut",
            date = "Gestern",
            isSender = false
        )


        setContent {
            val systemUiController = rememberSystemUiController()
            val chatBubbleList = remember { mutableStateListOf(question, answer)}
            val textInput = remember { mutableStateOf("") }
            val darkModeEnabled = remember { mutableStateOf(false)}


            MaterialTheme(
                colors = if (darkModeEnabled.value) DarkColors else LightColors
            ) {
                systemUiController.setStatusBarColor(MaterialTheme.colors.primary)

                Box(modifier = Modifier
                    .background(color = MaterialTheme.colors.background)
                    .fillMaxSize()) {
                    Button(
                        onClick = {darkModeEnabled.value = !darkModeEnabled.value},
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(all = 5.dp)
                    ) {
                        if (darkModeEnabled.value) Text(text = "LightMode") else Text(text = "DarkMode")
                    }
                    
                    LazyColumn (modifier = Modifier.padding(top = 35.dp)){
                        items(chatBubbleList) {
                            ChatBubble(chatBubbleData = it)
                        }
                    }
                    Row (modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp)
                    ) {
                        TextField(value = textInput.value, onValueChange = {
                            textInput.value = it
                        })
                        FloatingActionButton(onClick = {
                            val newBubble = ChatBubbleData (
                                date = "Jetzt",
                                isSender = true,
                                message = textInput.value
                            )
                            chatBubbleList.add(newBubble)
                            textInput.value = ""
                        },
                            modifier = Modifier.padding(start = 16.dp),
                            backgroundColor = MaterialTheme.colors.primary
                        ) {
                            Icon(imageVector = Icons.Filled.Send, contentDescription = "Senden" )
                        } // Button
                    } // Row
                } // Box
            } //MaterialTheme
        } // Main (setContent)
    }

    }

    @Composable
    fun ChatBubble(chatBubbleData: ChatBubbleData) {
        Surface(
            color = if (chatBubbleData.isSender) MaterialTheme.colors.secondary else MaterialTheme.colors.surface,
            shape = if (chatBubbleData.isSender) senderShape() else receiverShape(),
            elevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    start = if (chatBubbleData.isSender) 64.dp else 16.dp,
                    end = if (chatBubbleData.isSender) 16.dp else 64.dp
                )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = chatBubbleData.message,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = chatBubbleData.date,
                    modifier = Modifier.align(Alignment.End),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }

fun receiverShape(): RoundedCornerShape {
    return RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 8.dp,
        bottomStart = 8.dp,
        bottomEnd = 8.dp
    )
}

fun senderShape(): RoundedCornerShape {
    return RoundedCornerShape(
        topStart = 8.dp,
        topEnd = 0.dp,
        bottomStart = 8.dp,
        bottomEnd = 8.dp
    )
}
