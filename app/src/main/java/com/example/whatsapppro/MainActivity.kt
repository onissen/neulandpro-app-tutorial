package com.example.whatsapppro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
            val chatBubbleList = remember { mutableStateListOf(question, answer)}
            val textInput = remember { mutableStateOf("") }

            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn {
                    items(chatBubbleList) {
                        ChatBubble(chatBubbleData = it)
                    }
                }
                Row (modifier = Modifier.align(Alignment.BottomCenter)) {
                    TextField(value = textInput.value, onValueChange = {
                        textInput.value = it
                    })
                    Button(onClick = {
                        val newBubble = ChatBubbleData (
                            date = "Jetzt",
                            isSender = true,
                            message = textInput.value
                                )
                        chatBubbleList.add(newBubble)
                        textInput.value = ""
                    }) {
                        Text(text = "Senden")
                    } // Button
                } // Row
            } // Box
        } // Main (setContent)
    }

    @Composable
    fun ChatBubble(chatBubbleData: ChatBubbleData) {
        Surface(
            color = if (chatBubbleData.isSender) Color.Yellow else Color.LightGray,
            shape = RoundedCornerShape(8.dp),
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

                Text(text = chatBubbleData.message)
                Text(
                    text = chatBubbleData.date,
                    modifier = Modifier.align(Alignment.End),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}