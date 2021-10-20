package com.mayd.homework.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mayd.homework.model.api.ApiResult
import com.mayd.homework.model.api.model.response.Shorten
import com.mayd.homework.model.repository.MaydDbRepository
import com.mayd.homework.model.repository.MaydRepository
import com.mayd.homework.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel@ViewModelInject constructor(
        private var maydRepository: MaydRepository,
        private var maydDbRepository: MaydDbRepository
) : BaseViewModel() {

    private val _createShortenResult = MutableLiveData<ApiResult<Shorten>>() // All Contacts liveData
    val createShortenResult: LiveData<ApiResult<Shorten>> = _createShortenResult

    private val _getHistoryResult = MutableLiveData<List<Shorten>>() // Starred Contacts liveData
    val getHistoryResult: LiveData<List<Shorten>> = _getHistoryResult

    companion object{
        val TAG_EMPTY_INPUT:String = "empty input"
    }

    /**
     * create shorten with url
     *
     * @param url the url you want to make shorten url
     */
    fun getShorten(url:String) {
        if(url.isEmpty()){
            _createShortenResult.value = ApiResult.error(Throwable(TAG_EMPTY_INPUT))
            return
        }

        viewModelScope.launch {
            maydRepository.getShorten(url)
                    .onStart { _createShortenResult.value = ApiResult.loading() }
                    .catch { e -> _createShortenResult.value = ApiResult.error(e) }
                    .onCompletion { _createShortenResult.value = ApiResult.loaded() }
                    .collect { _createShortenResult.value = ApiResult.success(it) }
        }
    }

    /**
     * get whole shorten history
     */
    fun getHistory(){
        viewModelScope.launch {
            maydRepository.getShortenHistory()
                    .collect { _getHistoryResult.value = it }
        }
    }

    /**
     * delete shorten item from db
     */
    fun deleteItem(shorten: Shorten){
        viewModelScope.launch(Dispatchers.IO) {
            maydDbRepository.deleteShortenData(shorten)
        }
    }
}