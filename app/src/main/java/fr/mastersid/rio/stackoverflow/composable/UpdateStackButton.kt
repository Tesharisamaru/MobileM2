package fr.mastersid.rio.stackoverflow.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.mastersid.rio.stackoverflow.ui.theme.StackOverflowTheme

@Composable
fun UpdateStackButton(updateStack : () -> Unit , modifier : Modifier ) {
    Button (
        onClick = updateStack ,
        modifier = modifier
    ) {
        Text (" Update list ")
    }
}

@Preview(showBackground=true)
@Composable
fun UpdateStackButtonPreview () {
    StackOverflowTheme{
        UpdateStackButton (
            updateStack = {} ,
            modifier = Modifier.size( width = 400. dp , height = 100. dp )
        )
    }
}