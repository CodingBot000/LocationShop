package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.repository.RepositoryCommon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class EventUiState(
    val detailData: EventData? = null,
    val productData: ProductData? = null
)

sealed interface EventIntent {
    data class DetailData(val detailData: EventData): EventIntent
    data class HospitalInfo(val productData: ProductData): EventIntent
}

@HiltViewModel
class EventDescViewModel @Inject constructor(
    private val repositoryCommon: RepositoryCommon
)
    : BaseViewModel<EventUiState, EventIntent>(EventUiState())
{
    val logger = Logger("EventDescViewModel")

    fun getEventData(id: Int) {
        val eventData = repositoryCommon.getEventDataById(id)
        eventData?.let {
            execute(EventIntent.DetailData(eventData))
            val productData = repositoryCommon.getProductData(eventData.hospital_id)
            productData?.let {
                execute(EventIntent.HospitalInfo(productData))
            }
        }

    }

    fun getSurgeryNames(surgeryIds: List<Int>): List<String> {
        val surgeryNames: List<String> = surgeryIds.mapNotNull { surgeryId ->
            repositoryCommon.getSurgeryList().find { data -> data.id == surgeryId }?.surgeryName
        }
        return surgeryNames
    }

    override suspend fun EventUiState.reduce(intent: EventIntent): EventUiState =
        when (intent) {
            is EventIntent.DetailData -> copy(detailData = intent.detailData)
            is EventIntent.HospitalInfo -> copy(productData = intent.productData)
        }
}
