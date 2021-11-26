package com.fernandoherrera.hackernewsapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.fernandoherrera.hackernewsapp.domain.usecase.LocalListHitUseCase
import com.fernandoherrera.hackernewsapp.domain.usecase.RemoveHitUseCase
import com.fernandoherrera.hackernewsapp.ui.main.mapper.MapHitToItem
import com.fernandoherrera.hackernewsapp.ui.main.model.HitItem
import com.fernandoherrera.hackernewsapp.utils.DEFAULT_QUERY
import com.fernandoherrera.hackernewsapp.utils.SingleLiveEvent
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(
    private val localListHitUseCase: LocalListHitUseCase,
    private val removeHitUseCase: RemoveHitUseCase,
    private val mapHitToItem: MapHitToItem
) : ViewModel() {

    private val _itemRemoved: SingleLiveEvent<Boolean> by lazy { SingleLiveEvent() }
    val itemRemoved get() = _itemRemoved

    val allHits: LiveData<PagingData<HitItem>> by lazy {
        localListHitUseCase.invoke(DEFAULT_QUERY).map { pagingData ->
            pagingData.map {
                mapHitToItem.map(it)
            }
        }.cachedIn(viewModelScope).asLiveData()
    }

    fun removeHitItem(hitObjectId: String) {
        viewModelScope.launch {
            _itemRemoved.postValue(removeHitUseCase.invoke(hitObjectId))
        }
    }
}
