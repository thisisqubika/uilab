package ui.dashboard

import kotlinx.collections.immutable.toImmutableList
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
        MutableStateFlow(uiElementRepo.getUiElements().toSelectableUiElements().toImmutableList())
    val uiElements: StateFlow<List<SelectableUiElement>> get() = _uiElements

    fun onElementClick(clickedItem: SelectableUiElement) {
        _uiElements.value = uiElementRepo.getUiElements().toSelectableUiElements().map {
            if(it.uiElement == clickedItem.uiElement) it.copy(isSelected = clickedItem.isSelected.not()) else it
        }.toImmutableList()
    }
}