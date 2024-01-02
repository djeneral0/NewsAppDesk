package data

import commons.Constants.API_KEY
import commons.Constants.BASE_URL
import commons.Constants.HEADLINE_URL
import data.models.News
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

object NewsApiClient  {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getTopHeadlines(): News {
        return client.get(HEADLINE_URL).body()
    }

    suspend fun getSearchNews(search: String): News {
        val EVERYTHING_URL = "${BASE_URL}everything?q=$search&apiKey=${API_KEY}"
        return client.get(EVERYTHING_URL).body()
    }
}

