package com.mayd.homework.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mayd.homework.R
import com.mayd.homework.model.api.ApiResult
import com.mayd.homework.ui.base.BaseActivity
import com.mayd.homework.ui.main.MainViewModel.Companion.TAG_EMPTY_INPUT
import com.mayd.homework.utils.utility.GeneralUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
    private var adapter: HistoryAdapter? = null

    /**
     * Declare Closure for recyclerView's click event
     */
    private val contactsFuncListener by lazy {
        HistoryFunListener(
            onCopyClick = { data ->
                GeneralUtils.copyToClipboard(data.fullShortLink)
                adapter?.notifyDataSetChanged()
            },
            onDeleteClick = { data ->
                viewModel.deleteItem(data)
                viewModel.getHistory()
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        val backStackEntryCount = supportFragmentManager.backStackEntryCount
        takeIf { backStackEntryCount == 0 }?.run {
            takeIf { viewModel.needCloseApp }?.run { finish() }
                ?: run {
                    viewModel.startBackExitAppTimer()
                    GeneralUtils.showToast(this, getString(R.string.press_again_exit))
                }
        } ?: run { supportFragmentManager.popBackStack() }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initUi() {
        viewModel.getHistory()

        adapter = HistoryAdapter(contactsFuncListener)
        history_rv.adapter = adapter
        history_rv.layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)

        url_et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                switchInputUi(false)
            }
        })

        shorten_tv.setOnClickListener {
            loading_view.visibility = View.VISIBLE
            GeneralUtils.hideKeyboard(this)
            viewModel.getShorten(url_et.text.toString())    
        }
    }

    override fun setObserve() {
        viewModel.getHistoryResult.observe(this) {
            switchUiMode(it.isNotEmpty())
            adapter?.submitList(it)
        }

        viewModel.createShortenResult.observe(this) {
            when (it) {
                is ApiResult.Loading -> {
                    shorten_tv.isEnabled = false
                }
                is ApiResult.Loaded -> {
                    loading_view.visibility = View.GONE
                    shorten_tv.isEnabled = true
                    url_et.text.clear()
                }
                is ApiResult.Success -> {
                    viewModel.getHistory()
                }
                is ApiResult.Error -> {
                    loading_view.visibility = View.GONE
                    if (it.throwable.message == TAG_EMPTY_INPUT) {
                        switchInputUi(true)
                    } else {
                        GeneralUtils.showToast(this, getString(R.string.unexpected_error))
                    }
                }
            }
        }
    }

    /**
     * according to the history list has data
     *
     * @param hasHistory true: show the history list;
     *                   false: hide the history list
     */
    private fun switchUiMode(hasHistory: Boolean) {

        if (hasHistory) {
            main_group.visibility = View.GONE
            history_group.visibility = View.VISIBLE
        } else {
            main_group.visibility = View.VISIBLE
            history_group.visibility = View.GONE
        }
    }

    /**
     * switch input ui
     *
     * @param error true: display error ui;
     *              false: display normal ui
     */
    private fun switchInputUi(error: Boolean) {
        if (error) {
            url_et.apply {
                background =
                    ContextCompat.getDrawable(baseContext, R.drawable.bg_rectangle_red_stroke)
                setHintTextColor(ContextCompat.getColor(baseContext, R.color.red))
                hint = getString(R.string.main_shorten_empty_error_hint)
            }
        } else {
            url_et.apply {
                background = ContextCompat.getDrawable(baseContext, R.drawable.bg_rectangle_white)
                setHintTextColor(ContextCompat.getColor(baseContext, R.color.gray))
                hint = getString(R.string.main_shorten_hint)
            }
        }
    }
}