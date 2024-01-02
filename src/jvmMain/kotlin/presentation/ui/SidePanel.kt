package presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import commons.handleCursor

@Composable
fun SidePanel(onMenuSelected: (header: String) -> Unit, onSearched: (search: String, header: String) -> Unit){
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(0.15f)
            .fillMaxHeight()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val logoBitmap = useResource("logo_image.png"){
            loadImageBitmap(it)
        }

        Image(
            logoBitmap,
            "logo",
            modifier = Modifier.width(100.dp)
        )
        Spacer(modifier = Modifier.padding(18.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            singleLine = true,
            placeholder = {
                Text("Search")
            },
            value = searchText,
            onValueChange = {
                searchText = it
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onSearched(searchText, "Results for $searchText")
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .pointerHoverIcon(handleCursor())
                ){
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search button",
                        tint = Color.Black
                    )
                }
            }
        )
        TextButton(
            onClick = {
                searchText = ""
                onMenuSelected("Headlines")
            }
        ){
            Text(
                "Headlines",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .pointerHoverIcon(handleCursor())
            )
        }
    }
}