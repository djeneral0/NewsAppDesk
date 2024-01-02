package commons

import org.jetbrains.skia.Image
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.input.pointer.PointerIcon
import java.awt.Cursor
import java.awt.Desktop
import java.net.URI
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

fun handleCursor() = PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))

fun openURL(url: URI){
    val desktop = Desktop.getDesktop()
    desktop.browse(url)
}

fun loadPicture(url: String) =
    Image.makeFromEncoded(URL(url).readBytes())
        .toComposeImageBitmap()

fun formatDate(date: String): String{
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    val date = inputFormat.parse(date)

    val outputFormat = SimpleDateFormat("EEEE d MMMM yyyy", Locale.US)
    return outputFormat.format(date)
}