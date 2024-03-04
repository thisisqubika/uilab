import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import model.UiElementRepo
import ui.dashboard.Dashboard
import ui.dashboard.DashboardViewModel

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow(canvasElementId = "ComposeTarget") {
        Dashboard(
            DashboardViewModel(
                UiElementRepo
            )
        )
    }
}