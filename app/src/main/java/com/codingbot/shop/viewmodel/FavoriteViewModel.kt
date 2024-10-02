package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.repository.RepositoryCommon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class FavoriteUiState(
    val favoriteDatas: MutableList<ProductData> = mutableListOf<ProductData>(),
    val recommendDatas: MutableList<ProductData> = mutableListOf<ProductData>(),
)

sealed interface FavoriteIntent {
    data class FavoriteDatas(val favoriteDatas: MutableList<ProductData>): FavoriteIntent
    data class RecommendDatas(val recommendDatas: MutableList<ProductData>): FavoriteIntent
}

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repositoryCommon: RepositoryCommon
)
    : BaseViewModel<FavoriteUiState, FavoriteIntent>(FavoriteUiState())
{
    val logger = Logger("SortingViewModel")

    init {
        initFavorite()
    }

    private fun initFavorite() {
        val favoriteDatas = repositoryCommon.getFavoriteStoredDatas()
        execute(FavoriteIntent.FavoriteDatas(favoriteDatas.toMutableList()))
    }

    override suspend fun FavoriteUiState.reduce(intent: FavoriteIntent): FavoriteUiState =
        when (intent) {
            is FavoriteIntent.FavoriteDatas -> copy(favoriteDatas = intent.favoriteDatas)
            is FavoriteIntent.RecommendDatas -> copy(recommendDatas = intent.recommendDatas)

        }
}