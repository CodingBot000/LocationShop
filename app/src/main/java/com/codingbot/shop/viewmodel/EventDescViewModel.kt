package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.ProductData
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
class EventDescViewModel @Inject constructor()
    : BaseViewModel<EventUiState, EventIntent>(EventUiState())
{
    val logger = Logger("EventDescViewModel")

    fun getEventData(id: Int) {
        val eventData = DumpServer.getEventDataById(id)
        eventData?.let {
            execute(EventIntent.DetailData(eventData))
            val productData = DumpServer.getProductData(eventData.hospital_id)
            productData?.let {
                execute(EventIntent.HospitalInfo(productData))
            }
        }



//        val matchingEvents: List<EventData> = eventDataList?.filter { it.id == id } ?: emptyList()

//        eventDataList?.let { eventList ->
//            eventList.find { eventData -> eventData.id == id }?.let { eventData ->
//                execute(EventIntent.DetailData(eventData))
//
//                val productData = DumpServer.getProductData(eventData.hospital_id)
//                productData?.let {
//                    execute(EventIntent.HospitalInfo(productData))
//                }
//
//            }
//        }
    }

    fun getSurgeryNames(surgeryIds: List<Int>): List<String> {
        val surgeryNames = mutableListOf<String>()
        surgeryIds.forEach { surgeryId ->
            DumpServer.surgeryDataList?.let { surgeryDataList ->
                surgeryDataList.find { data -> data.id == surgeryId }?.let {
                    surgeryNames.add(it.surgeryName)
                }
            }
        }
        return surgeryNames
    }

    override suspend fun EventUiState.reduce(intent: EventIntent): EventUiState =
        when (intent) {
            is EventIntent.DetailData -> copy(detailData = intent.detailData)
            is EventIntent.HospitalInfo -> copy(productData = intent.productData)
        }
}
