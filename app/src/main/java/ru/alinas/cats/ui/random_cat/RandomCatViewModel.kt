package ru.alinas.cats.ui.random_cat

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.alinas.cats.db.CatEntity
import ru.alinas.cats.db.CatRepository
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class RandomCatViewModel @Inject constructor(private val catRepository: CatRepository) :
    ViewModel() {
    fun saveCat(img: Drawable, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val bitmap = (img as BitmapDrawable).bitmap
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        val imageBytes = outputStream.toByteArray()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val catEntity = CatEntity(img = imageBytes)
                catRepository.insertCat(catEntity)
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "Error occurred while saving cat")
            }
        }
    }
}
