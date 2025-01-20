package com.satwik.smile.features.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satwik.smile.R
import com.satwik.smile.core.designsystem.theme.fontFamily
import com.satwik.smile.features.reverse_shell.RemoteServer
import java.io.OutputStream
import java.io.PrintStream

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Content()
}

@Composable
private fun Content(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Smile.",modifier = Modifier.align(Alignment.CenterHorizontally),fontFamily = fontFamily, fontSize = 31.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(28.dp))
        
        Icon(painter = painterResource(id = R.drawable.ic_smile), contentDescription = null, tint = Color.White)

        Spacer(modifier = Modifier.height(28.dp))


        val messages = remember { mutableStateOf("") }

        LaunchedEffect(Unit) {
            val stream = PrintlnCaptureStream()
            System.setOut(PrintStream(stream))
            stream.addListener { newMessage ->
                messages.value += newMessage
            }
        }

        ConnectionInfoBox(
            modifier = Modifier,
            status = if (messages.value== "Connected"){"CONNECTED"}else{"DISCONNECTED"},
            remoteIP = RemoteServer.IPADDRESS,
            port = RemoteServer.PORT.toString()
        )

        Spacer(modifier = Modifier.height(28.dp))


        LogsBox()

    }

}

@Composable
fun ConnectionInfoBox(modifier: Modifier = Modifier, status:String, remoteIP:String, port:String) {

    Column (modifier = modifier){

        Text(text = "Status: $status",fontFamily = fontFamily, fontSize = 16.sp, color = if(status=="CONNECTED") { Color.Green }else{
            Color.Red}, fontWeight = FontWeight.Normal)
        Text(text = "RemoteIP: $remoteIP", fontFamily = fontFamily, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Normal)
        Text(text = "Port: $port", fontFamily = fontFamily, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Normal)
    }
}


@Composable
fun LogsBox(modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier.border(width = 1.dp, color = Color.Gray)
    ){
        Box (modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 6.dp)
        ){
            Text(text = "LOGS",fontFamily = fontFamily, fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)
        }

        Box (modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
        )
        PrintlnMessagesView()

    }


}

class PrintlnCaptureStream : OutputStream() {
    private val stringBuilder = StringBuilder()
    private val listeners = mutableListOf<(String) -> Unit>()

    override fun write(b: Int) {
        val char = b.toChar()
        stringBuilder.append(char)
        if (char == '\n') {
            val message = stringBuilder.toString()
            listeners.forEach { it(message) }
            stringBuilder.clear()
        }
    }

    fun addListener(listener: (String) -> Unit) {
        listeners.add(listener)
    }
}

@Composable
fun PrintlnMessagesView() {
    val messages = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val stream = PrintlnCaptureStream()
        System.setOut(PrintStream(stream))
        stream.addListener { newMessage ->
            messages.value += newMessage
        }
    }

    val scrollState = rememberScrollState(0)

    LaunchedEffect(messages.value) {
        scrollState.animateScrollTo(scrollState.maxValue) // Scroll to the bottom
    }
    Text(
        text = messages.value,fontFamily = fontFamily, fontSize = 14.sp, color = Color.White, fontWeight = FontWeight.Normal,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .verticalScroll(scrollState)

    )
}


