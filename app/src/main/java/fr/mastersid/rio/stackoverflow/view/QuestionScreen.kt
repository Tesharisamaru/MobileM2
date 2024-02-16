package fr.mastersid.rio.stackoverflow.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.mastersid.rio.stackoverflow.R
import fr.mastersid.rio.stackoverflow.ViewModel.QuestionListViewModel
import fr.mastersid.rio.stackoverflow.composable.ThreadRow
import fr.mastersid.rio.stackoverflow.composable.UpdateStackButton


@Composable
fun QuestionScreen(questionListViewModel: QuestionListViewModel = viewModel()) {
    val questionList by questionListViewModel.questionList.observeAsState(initial = emptyList())
    val isNetFailing by questionListViewModel.isNetFailing.observeAsState(initial = false)
    val isHttpFailing by questionListViewModel.isHttpFailing.observeAsState(initial = false)
    val refreshing by questionListViewModel.isUpdating.observeAsState(initial = false)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(isNetFailing) {
        if (isNetFailing ) {
            snackbarHostState.showSnackbar(
                message = context.getString(R.string.net_error),
                duration = SnackbarDuration.Short,
                withDismissAction = true,
            )
        }
        if(isHttpFailing) {
            snackbarHostState.showSnackbar(
                message = context.getString(R.string.request_error),
                duration = SnackbarDuration.Short,
                withDismissAction = true,
            )
        }
    }

    Scaffold(
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(questionList) { question ->
                        ThreadRow(question)
                    }
                    item {
                        Spacer(modifier = Modifier.height(64.dp))
                    }
                }
                if (refreshing) { // affichage d'un indicateur de progression si refreshing == true
                    LinearProgressIndicator(modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth() )
                }
            }
        },
        floatingActionButton = {
            Column {
                UpdateStackButton(
                    updateStack = questionListViewModel::updateQuestionList,
                    modifier = Modifier
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    )
}
