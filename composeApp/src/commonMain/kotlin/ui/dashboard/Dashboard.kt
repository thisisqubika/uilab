package ui.dashboard

import QButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import model.UiElement
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.dashboard.screens.QButtonScreen
import uilab.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import uilab.composeapp.generated.resources.ic_science
import uilab.composeapp.generated.resources.ic_workspaces


@Composable
fun Dashboard(viewModel: DashboardViewModel) {
    val uiElements by viewModel.uiElements.collectAsState()
    val selectedElement = uiElements.firstOrNull { it.isSelected }?.uiElement

    Row(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxHeight().width(200.dp)) {
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
                val itemFontWeight = if (item.isSelected) {
                    FontWeight.SemiBold
                } else {
                    null
                }
                Text(
                    modifier = Modifier.clickable {
                        viewModel.onElementClick(item)
                    }
                        .background(itemBackgroundColor)
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = item.uiElement.name.lowercase().replaceFirstChar(Char::uppercase),
                    color = itemTextColor,
                    fontWeight = itemFontWeight
                )
            }
        }
        Box(modifier = Modifier.fillMaxHeight().weight(1f).background(Color.Blue.copy(alpha = 0.1f))) {
            when (selectedElement) {
                UiElement.BUTTON -> {
                    QButtonScreen()
                }

                null -> {
                    EmptyScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EmptyScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(Res.drawable.ic_workspaces),
                contentDescription = null
            )
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(Res.drawable.ic_science),
                contentDescription = null
            )
        }
    }
}