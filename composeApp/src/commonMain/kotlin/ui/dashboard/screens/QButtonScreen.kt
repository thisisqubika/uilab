package ui.dashboard.screens

import QButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun QButtonScreen() {
    Row(modifier = Modifier.background(Color.Green).fillMaxSize()) {
        Box(modifier = Modifier.background(Color.Blue).fillMaxHeight().weight(1f)) {
            QButton(
                text = "QButton",
                onClick = {  },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff6200EE) //colorResource(id = R.color.purple_500)
                ),
                shape = RoundedCornerShape(16.dp, 1.dp, 16.dp, 1.dp)
            )
        }
        Column(modifier = Modifier.fillMaxHeight().width(200.dp)) {  }
    }
}