package com.example.quotes.ui.quotes

import androidx.lifecycle.ViewModel
import com.example.quotes.data.Quote
import com.example.quotes.data.QuoteRepository

class QuotesViewModel(private val quoteRepository: QuoteRepository)
    : ViewModel() {
    fun getQuotes()=quoteRepository.getQuotes()
    fun addQuote(quote: Quote)=quoteRepository.addQuote(quote)

}