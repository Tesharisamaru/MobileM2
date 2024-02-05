package fr.mastersid.rio.stackoverflow.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import fr.mastersid.rio.stackoverflow.data.Question
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.mastersid.rio.stackoverflow.R
import fr.mastersid.rio.stackoverflow.ui.theme.StackOverflowTheme


@Composable
fun ThreadRow(question:Question) {
    Row {
        Text(
            text = question.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis ,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width (16.dp ))
        Text(text = stringResource(id = R.string.answerCount,question.answerCount))
    }
}
@Preview(showBackground = true )
@Composable
fun ThreadRowPreview () {
    StackOverflowTheme {
        ThreadRow(Question (1 ,".Net" , 7))
    }
}