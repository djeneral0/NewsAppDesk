package presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import data.NewsApiClient
import data.models.Article
import io.ktor.client.plugins.*
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    var article by remember { mutableStateOf( emptyList<Article>()) }
    var headTitle by remember { mutableStateOf("Headlines") }
    var searchText by remember { mutableStateOf("") }

    var scope = rememberCoroutineScope()

    LaunchedEffect(searchText){
        scope.launch {
            try {
                val newsData = if (searchText.isNotEmpty()){
                    NewsApiClient.getSearchNews(searchText)
                } else {
                    NewsApiClient.getTopHeadlines()
                }

                article = newsData.articles
            }catch (e: ClientRequestException){
                println("Error fetching data: ${e.message}")
            }
        }
    }

    Row {
//        side panel
        SidePanel(onMenuSelected = {
            headTitle = it
            searchText = ""
            article = emptyList()
        }, onSearched = {
            _searchText, _headTitle ->
            searchText = _searchText
            headTitle = _headTitle
            article = emptyList()
        })

//        Main Content
    }
}