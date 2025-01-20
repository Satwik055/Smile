package com.satwik.smile.features.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

        ConnectionInfoBox(
            modifier = Modifier,
            status = "CONNECTED",
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

        Text(text = "Status: $status",fontFamily = fontFamily, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Normal)
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
        ){
            Text(text = "Attempting to connect",fontFamily = fontFamily, fontSize = 14.sp, color = Color.White, fontWeight = FontWeight.Normal)
        }

    }


}