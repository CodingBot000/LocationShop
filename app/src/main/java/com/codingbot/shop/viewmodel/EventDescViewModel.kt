package com.codingbot.shop.viewmodel

import androidx.lifecycle.viewModelScope
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.data.repository.RepositoryCommon
import com.codingbot.shop.data.repository.RepositoryEvent
import com.codingbot.shop.data.repository.RepositoryProductData
import com.codingbot.shop.domain.model.SurgeryData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class EventUiState(
    val detailData: EventData? = null,
    val productData: ProductData? = null,
    val surgeryNames: List<String> = emptyList()
)

sealed interface EventIntent {
    data class DetailData(val detailData: EventData): EventIntent
    data class HospitalInfo(val productData: ProductData): EventIntent
    data class SurgeryDataList(val surgeryNames: List<String>): EventIntent
}

@HiltViewModel
class EventDescViewModel @Inject constructor(
    private val repositoryProductData: RepositoryProductData,
    private val repositoryEvent: RepositoryEvent,
    private val repositoryCommon: RepositoryCommon
)
    : BaseViewModel<EventUiState, EventIntent>(EventUiState())
{
    val logger = Logger("EventDescViewModel")

    fun getEventData(id: Int) {
        viewModelScope.launch {
            val eventData = repositoryEvent.getEventDataById(id)
            eventData?.let {
                execute(EventIntent.DetailData(eventData))
                getSurgeryNames(eventData.surgeryIds)

                val productData = repositoryProductData.getProductData(eventData.hospital_id)
                productData?.let {
                    execute(EventIntent.HospitalInfo(productData))
                }
            }
        }
    }

//    fun getSurgeryNames(surgeryIds: List<Int>): List<String> {
    private suspend fun getSurgeryNames(surgeryIds: List<Int>) {
        val surgeryNames: List<String> = surgeryIds.mapNotNull { surgeryId ->
            repositoryCommon.getSurgeryList().find { data -> data.id == surgeryId }?.surgeryName
        }
//        return surgeryNames
        execute(EventIntent.SurgeryDataList(surgeryNames))
    }

    override suspend fun EventUiState.reduce(intent: EventIntent): EventUiState =
        when (intent) {
            is EventIntent.DetailData -> copy(detailData = intent.detailData)
            is EventIntent.HospitalInfo -> copy(productData = intent.productData)
            is EventIntent.SurgeryDataList -> copy(surgeryNames = intent.surgeryNames)
        }
}
