package com.example.quotes.utilities

import com.example.quotes.data.FakeDatabase
import com.example.quotes.data.QuoteRepository
import com.example.quotes.ui.quotes.QuotesViewModelFactory

object InjectorUtils {

    fun provideQuotesViewModelFactory(): QuotesViewModelFactory{
        val quoteRepository=
            QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)
    }
}