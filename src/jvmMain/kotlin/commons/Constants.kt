package commons

object Constants {
    val API_KEY = "7c2809da69a9497c8cc72cd3be5caad4"
    val BASE_URL = "https://newsapi.org/v2/"
    val HEADLINE_URL = "${BASE_URL}top-headlines?country=us&apiKey=${API_KEY}"
}