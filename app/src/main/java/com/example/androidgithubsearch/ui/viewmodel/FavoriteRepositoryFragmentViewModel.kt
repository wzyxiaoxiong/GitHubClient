package com.example.androidgithubsearch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.androidgithubsearch.data.database.entity.FavoriteRepositoryEntity
import com.example.androidgithubsearch.data.repository.GitHubRepository
import com.example.androidgithubsearch.ui.adapter.favoriterpositoryadapter.FavoriteRepositoryItem
import com.example.androidgithubsearch.utils.dateStringToDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteRepositoryFragmentViewModel @Inject constructor(
    private val gitHubRepository: GitHubRepository,
) : ViewModel() {
    val favoriteRepositoryList: LiveData<List<FavoriteRepositoryItem>> = liveData {
        val result = gitHubRepository.getFavoriteRepositories()
        if (result.isSuccess) {
            result.getOrNull()?.collect {
                val favoriteRepositoryItems = it.map { favoriteRepositoryEntity ->
                    createFavoriteRepositoryItem(favoriteRepositoryEntity)
                }
                emit(favoriteRepositoryItems)
            }
        }
    }

    private val _moveUrlPage = MutableLiveData<String?>()
    val moveUrlPage: LiveData<String?> = _moveUrlPage

    fun moveDonePage() {
        _moveUrlPage.value = null
    }

    private fun createFavoriteRepositoryItem(favoriteRepositoryEntity: FavoriteRepositoryEntity): FavoriteRepositoryItem {
        return FavoriteRepositoryItem(
            id = favoriteRepositoryEntity.id,
            name = favoriteRepositoryEntity.name,
            url = favoriteRepositoryEntity.url,
            created = favoriteRepositoryEntity.created.dateStringToDate(),
            updated = favoriteRepositoryEntity.updated.dateStringToDate(),
            language = favoriteRepositoryEntity.language,
            star = favoriteRepositoryEntity.star,
            avatar = favoriteRepositoryEntity.avatar,
            isFavorite = true,
            clickAddFavoriteAction = {
                viewModelScope.launch {
                    gitHubRepository.addFavoriteRepository(
                        favoriteRepositoryEntity
                    )
                }
            },
            clickRemoveFavoriteAction = {
                viewModelScope.launch {
                    gitHubRepository.deleteFavoriteRepository(
                        favoriteRepositoryEntity
                    )
                }
            },
            clickItemAction = {
                _moveUrlPage.value = favoriteRepositoryEntity.url
            }
        )
    }
}
