package ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.UiElement
import ui.dashboard.screens.QButtonScreen

@Composable
fun Dashboard(viewModel: DashboardViewModel) {
    val uiElements by viewModel.uiElements.collectAsState()
    val selectedElement = uiElements.firstOrNull { it.isSelected }?.uiElement

    Row(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxHeight().width(100.dp)) {
            items(items = uiElements, key = { it.uiElement.name }) { item ->
                val itemBackgroundColor = if (item.isSelected) {
                    Color.Magenta
                } else {
                    Color.White
                }
                val itemTextColor = if (item.isSelected) {
                    Color.White
                } else {
                    Color.Black
                }
                Text(
                    modifier = Modifier.clickable {
                        viewModel.onElementClick(item.uiElement)
                    }.padding(16.dp).background(itemBackgroundColor),
                    text = item.uiElement.name.lowercase().replaceFirstChar(Char::uppercase),
                    color = itemTextColor
                )
            }
        }
        Box(modifier = Modifier.fillMaxHeight().weight(1f).background(Color.Red)) {
//            when(selectedElement) {
//                UiElement.BUTTON -> QButtonScreen()
//                null -> TODO()
//            }
        }
    }
}