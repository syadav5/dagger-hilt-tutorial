package com.examples.dagger.hilt.viewmodel

import androidx.lifecycle.*
import com.examples.dagger.hilt.model.BlogDomainEntity
import com.examples.dagger.hilt.repository.BlogRepository
import com.examples.dagger.hilt.usecases.MainStateEvent
import com.examples.dagger.hilt.util.DataState
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: BlogRepository,
    private val savedStateHandle: SavedStateHandle
):ViewModel() {
   private val _dataStateLiveData: MutableLiveData<DataState<List<BlogDomainEntity>>> = MutableLiveData()
    val dataStateLiveData: LiveData<DataState<List<BlogDomainEntity>>> = _dataStateLiveData

    fun setStateEvent(stateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                is MainStateEvent.GetBlogEvents -> {
                    repository.getBlog().onEach {
                        _dataStateLiveData.postValue(it)
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                    //do nothing
                }
            }
        }
    }
}
