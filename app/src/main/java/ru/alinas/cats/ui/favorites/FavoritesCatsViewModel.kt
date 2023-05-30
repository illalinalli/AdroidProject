package ru.alinas.cats.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.alinas.cats.db.CatEntity
import ru.alinas.cats.db.CatRepository
import javax.inject.Inject

@HiltViewModel
class FavoritesCatViewModel @Inject constructor(private val catRepository: CatRepository) : ViewModel() {
    fun getAllCats(onSuccess: (List<CatEntity>) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cats = catRepository.getAllCats()
                withContext(Dispatchers.Main) {
                    onSuccess(cats)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError(e.message ?: "Error occurred while getting cats")
                }
            }
        }

    }
}
