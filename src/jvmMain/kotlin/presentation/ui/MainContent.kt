package presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import commons.formatDate
import commons.handleCursor
import commons.loadPicture
import commons.openURL
import data.models.Article
import java.net.URI

@Composable
fun MainContent(headerTitle: String, article: List<Article>){
    if(article.isNotEmpty()){
//        show news
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(8.dp)
        ) {
            Text(
                headerTitle,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(Modifier.padding(4.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                items(article) {
                    Card(
                        modifier = Modifier
                            .width(400.dp)
                            .height(200.dp)
                            .padding(4.dp)
                            .pointerHoverIcon(handleCursor())
                            .clickable {
                                if (!it.url.isNullOrEmpty()){
                                    openURL(url = URI(it.url))
                                }
                            }
                    ) {
                        Box {
                            val bitmap = useResource("no_image.png"){
                                loadImageBitmap(it)
                            }

                            if(it.urlToImage.isNullOrEmpty()){
                                Image(
                                    bitmap,
                                    contentDescription = "No Image available",
                                    modifier = Modifier.size(100.dp)
                                        .align(Alignment.TopCenter),
                                    contentScale = ContentScale.Crop
                                    )
                            }else{
                                Image(
                                    loadPicture(it.urlToImage),
                                    "News Thumbnail",
                                    contentScale = ContentScale.Crop
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .background(color = Color.White)
                                    .padding(4.dp)
                            ) {
                                Text(
                                    it.title ?: "",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.size(2.dp))
                                Text(
                                    it.content ?: "",
                                    color = Color.Black,
                                    fontWeight = FontWeight.ExtraLight,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.size(2.dp))
                                Text(
                                    formatDate(it.publishedAt),
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Loading...")
        }
    }
}