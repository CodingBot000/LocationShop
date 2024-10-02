package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.data.repository.RepositoryCommon
import com.codingbot.shop.domain.model.SurgeryData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class TreatmentDetailUiState(
    val detailData: SurgeryData? = null
)

sealed interface TreatmentDetailIntent {
    data class DetailData(val detailData: SurgeryData): TreatmentDetailIntent
}

@HiltViewModel
class TreatmentDetailDescViewModel @Inject constructor(
    private val repositoryCommon: RepositoryCommon
)
    : BaseViewModel<TreatmentDetailUiState, TreatmentDetailIntent>(TreatmentDetailUiState())
{
    val logger = Logger("TreatmentDetailDescViewModel")

    fun getDetailData(id: Int) {
        repositoryCommon.getSurgeryList()?.let {
            try {
                execute(TreatmentDetailIntent.DetailData(it[id]))
            } catch (e: IndexOutOfBoundsException) {
                execute(TreatmentDetailIntent.DetailData(it[it.size -1]))
            }
        }
    }

    override suspend fun TreatmentDetailUiState.reduce(intent: TreatmentDetailIntent): TreatmentDetailUiState =
        when (intent) {
            is TreatmentDetailIntent.DetailData -> copy(detailData = intent.detailData)

        }
}
