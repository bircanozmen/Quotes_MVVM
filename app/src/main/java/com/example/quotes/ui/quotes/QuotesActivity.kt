package com.example.quotes.ui.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quotes.R
import com.example.quotes.data.Quote
import com.example.quotes.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_quotes.*
import java.util.*


@Suppress("DEPRECATION")
class QuotesActivity : AppCompatActivity() {
    var alıntı:Int?=null
    var yazar: Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        initializeUi()

       /* var quote: String? = " "
        quote = null // Hata vermez*/

    }

    private fun initializeUi() {
        // Get the QuotesViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.provideQuotesViewModelFactory()
        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        val viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)

        // Observing LiveData from the QuotesViewModel which in turn observes
        // LiveData from the repository, which observes LiveData from the DAO ☺
        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach {
                stringBuilder.append("$it\n\n")
            }
            textView_quotes.text = stringBuilder.toString()
        })

        // When button is clicked, instantiate a Quote and add it to DB through the ViewModel
        button_add_quote.setOnClickListener {
            alıntı= editText_quote.text.toString().toIntOrNull()
            yazar=editText_author.text.toString().toIntOrNull()
            val quote = Quote(editText_quote.text.toString(), editText_author.text.toString())
            if((alıntı == null) || (yazar == null)) {
                Toast.makeText(applicationContext,"All fields must be filled",Toast.LENGTH_SHORT).show()

            }
            else{
            viewModel.addQuote(quote)
            editText_quote.setText("")
            editText_author.setText("")
        }}
    }}


