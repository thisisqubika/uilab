package ui.dashboard

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import model.SelectableUiElement
import model.UiElement
import model.UiElementRepo
import model.toSelectableUiElements

class DashboardViewModel(
    private val uiElementRepo: UiElementRepo
) {

    private val _uiElements: MutableStateFlow<List<SelectableUiElement>> =
        MutableStateFlow(uiElementRepo.getUiElements().toSelectableUiElements())
    val uiElements: StateFlow<List<SelectableUiElement>> get() = _uiElements

    fun onElementClick(uiElement: UiElement) {
        _uiElements.value = uiElementRepo.getUiElements().toSelectableUiElements().map {
            if(it.uiElement == uiElement) it.copy(isSelected = it.isSelected.not()) else it
        }
    }
}