package com.examples.dagger.hilt.usecases

sealed class MainStateEvent {
    object GetBlogEvents : MainStateEvent()

    object None : MainStateEvent()
}