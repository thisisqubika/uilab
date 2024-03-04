import androidx.compose.runtime.Composable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun QButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    backgroundGradientBrush: Brush? = null,
    disabledBackgroundGradientBrush: Brush? = backgroundGradientBrush
) {
    // First, decide if in case of having the default content padding, we'll be using in fact the
    // default content padding or the button-with-icon content padding.
    val actualContentPadding =
        if (contentPadding == ButtonDefaults.ContentPadding && (leadingIcon != null || trailingIcon != null)) {
            ButtonDefaults.ButtonWithIconContentPadding
        } else {
            contentPadding
        }

    // Second, see if we'll be using a regular button or a gradient button. In the case of a regular
    // button, the padding of the Button composable will actually be the content padding which was
    // passed in. In the case of a gradient button, we'll set the Button composable content padding
    // to zero and pass the content padding down to the GradientButtonContent.
    val buttonContentPadding = if (backgroundGradientBrush != null) {
        PaddingValues(0.dp)
    } else {
        actualContentPadding
    }

    val buttonModifier = if (backgroundGradientBrush != null) {
        Modifier
    } else {
        modifier
    }

    val gradientContentModifier = if (backgroundGradientBrush != null) {
        modifier
    } else {
        Modifier
    }

    // Prepare the content to be invoked whenever is needed.
    val buttonContent: @Composable () -> Unit = {
        ButtonContent(
            text = text, leadingIcon = leadingIcon, trailingIcon = trailingIcon
        )
    }

    Button(
        onClick = onClick,
        modifier = buttonModifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = buttonContentPadding,
        interactionSource = interactionSource
    ) {
        if (backgroundGradientBrush == null) {
            buttonContent.invoke()
        } else {
            val brush = if (enabled) {
                backgroundGradientBrush
            } else {
                disabledBackgroundGradientBrush ?: backgroundGradientBrush
            }
            GradientButtonContent(
                brush = brush,
                shape = shape,
                contentPadding = actualContentPadding,
                modifier = gradientContentModifier
            ) {
                buttonContent.invoke()
            }
        }
    }
}

@Composable
private fun GradientButtonContent(
    brush: Brush,
    shape: Shape,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Box(
        modifier = modifier
            .defaultMinSize(
                minWidth = ButtonDefaults.MinWidth, minHeight = ButtonDefaults.MinHeight
            )
            .fillMaxHeight()
            .background(brush, shape)
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        Row {
            content()
        }
    }
}

@Composable
fun ButtonContent(
    text: String, leadingIcon: Painter? = null, trailingIcon: Painter? = null
) {
    leadingIcon?.let {
        Icon(
            it,
            contentDescription = "description",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
    }
    Text(text = text)
    trailingIcon?.let {
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Icon(
            it,
            contentDescription = "description",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
    }
}