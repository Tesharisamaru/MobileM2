package fr.mastersid.rio.stackoverflow.view
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.mastersid.rio.stackoverflow.R
import fr.mastersid.rio.stackoverflow.ViewModel.QuestionListViewModel
import fr.mastersid.rio.stackoverflow.composable.ThreadRow
import fr.mastersid.rio.stackoverflow.composable.UpdateStackButton
import fr.mastersid.rio.stackoverflow.data.Question
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun QuestionScreen(questionListViewModel:QuestionListViewModel = viewModel()) {
    val questionList by questionListViewModel.questionList.observeAsState ( initial = emptyList () )
    val refreshing by questionListViewModel.isUpdating.observeAsState ( initial = false )

    Box {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(questionList) { question ->
                ThreadRow(question)
            }
            item {
                Spacer ( modifier = Modifier.height(64.dp))
            }

        }
        if(refreshing) { // affichage d'un indicateur de progression si refreshing == true
            LinearProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
            UpdateStackButton(
                updateStack = questionListViewModel::updateQuestionList,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp)
            )
    }
}