package com.examples.dagger.hilt.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.examples.dagger.hilt.R
import com.examples.dagger.hilt.model.BlogDomainEntity
import com.examples.dagger.hilt.usecases.MainStateEvent
import com.examples.dagger.hilt.util.DataState
import com.examples.dagger.hilt.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewmodel: MainViewModel by viewModels()
    lateinit var progressIndicator: ProgressBar
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressIndicator = findViewById(R.id.progress_bar)
        textView = findViewById(R.id.text)
        initObservers()
        viewmodel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun initObservers() {
        viewmodel.dataStateLiveData.observe(this, Observer {
            when (it) {
                is DataState.Loading -> displayProgress(true)
                is DataState.Success -> {
                    displayProgress(false)
                    displayData(it.data)
                }
                is DataState.Error -> {
                    displayProgress(false)
                    displayError(it.message)
                }
            }
        })
    }

    fun displayError(message: String) {
        textView.text = message
    }

    fun displayProgress(visibility: Boolean) {
        progressIndicator.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    fun displayData(data: List<BlogDomainEntity>) {
        var msg = ""
        data.forEach {
            msg = msg + it.title + "\n"
        }
        textView.text = msg
    }
}