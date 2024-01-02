package commons

import org.jetbrains.skia.Image
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.input.pointer.PointerIcon
import java.awt.Cursor
import java.awt.Desktop
import java.net.URI
import java.net.URL

fun handleCursor() = PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))

fun openURL(url: URI){
    val desktop = Desktop.getDesktop()
    desktop.browse(url)
}

fun loadPicture(url: String) =
    Image.makeFromEncoded(URL(url).readBytes())
        .toComposeImageBitmap()