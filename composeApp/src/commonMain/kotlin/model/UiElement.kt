package model

enum class UiElement {
    BUTTON;

    fun toSelectableUiElement(): SelectableUiElement {
        return SelectableUiElement(
            this,
            false
        )
    }
}

fun List<UiElement>.toSelectableUiElements(): List<SelectableUiElement> {
    return this.map {
        it.toSelectableUiElement()
    }
}

data class SelectableUiElement(
    val uiElement: UiElement,
    val isSelected: Boolean
)