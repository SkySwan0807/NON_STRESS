package com.example.profedata.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.profedata.ui.theme.ColorBase


@Composable
fun HomeScreen() {

    var messageText by remember { mutableStateOf("") }
    var messages by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {

        // Lista de mensajes
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                ChatBubble(message = message)
            }
        }

        // Barra inferior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = messageText,
                onValueChange = { messageText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Escribe un mensaje...") },
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ColorBase,
                    cursorColor = ColorBase
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    if (messageText.isNotBlank()) {
                        messages = messages + messageText
                        messageText = ""
                    }
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = ColorBase,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Enviar"
                )
            }
        }
    }
}

@Composable
fun ChatBubble(message: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Surface(
            color = ColorBase,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(12.dp),
                color = Color.White,
                textAlign = TextAlign.Start
            )
        }
    }
}