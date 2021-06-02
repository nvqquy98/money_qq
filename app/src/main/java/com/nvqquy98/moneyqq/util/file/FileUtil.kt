import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.core.content.FileProvider
import com.nvqquy98.moneyqq.BuildConfig
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

fun Bitmap.toFile(context: Context): File? {
    var stream: OutputStream? = null
    return try {
        val photoFile = generateTempImageFile(context, PNG_EXTENSION)
        stream = FileOutputStream(photoFile)
        compress(Bitmap.CompressFormat.PNG, 100, stream)
        photoFile
    } catch (e: Throwable) {
        null
    } finally {
        stream?.flush()
        stream?.close()
    }

}

fun getMimeType(context: Context, uri: Uri): String? {
    return if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
        MimeTypeMap.getSingleton().getExtensionFromMimeType(context.contentResolver.getType(uri))
    } else {
        MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(File(uri.path!!)).toString())
    }
}

fun createImageFileUri(context: Context): Pair<File, Uri>? {
    val photoFile = generateTempImageFile(context, JPG_EXTENSION)
    return try {
        photoFile to FileProvider.getUriForFile(context, APP_FILE_PROVIDER, photoFile)
    } catch (e: Throwable) {
        photoFile.deleteIfExist()
        null
    }
}

fun clearImageCached(parentDir: File?) {
    parentDir?.let { dir ->
        dir.listFiles()?.forEach {
            if (it != null && it.isDirectory && it.name == PRE_FILE_NAME) {
                it.listFiles()?.forEach { child -> child.deleteRecursively() }
            }
        }
    }
}

fun clearTempFileDirectory(context: Context) {
    clearImageCached(context.cacheDir)
}

fun File.deleteIfExist() {
    if (exists()) delete()
}

private fun generateTempImageFile(context: Context, extension: String): File {
    val privateTempDir = File(context.cacheDir, PRE_FILE_NAME)
    if (!privateTempDir.exists()) privateTempDir.mkdir()
    return File(privateTempDir, PRE_FILE_NAME + System.currentTimeMillis() + extension)
}

private const val PNG_EXTENSION = ".png"
private const val PRE_FILE_NAME = "moneyqq"
private const val JPG_EXTENSION = ".jpg"
const val IMAGE_TYPE = "image/*"
const val APP_FILE_PROVIDER = BuildConfig.APPLICATION_ID.plus(".provider")