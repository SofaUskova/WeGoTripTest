package com.example.wegotriptest.presentation.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wegotriptest.R
import com.example.wegotriptest.StubData.stubFeedback
import com.example.wegotriptest.presentation.viewmodels.BaseViewModel
import com.example.wegotriptest.utils.Const.FEEDBACK_BUNDLE_TAG
import com.example.wegotriptest.utils.errorMessage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(BaseViewModel::class.java) }
    private val feedback = stubFeedback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.errorMessage.observe(this) { message ->
            errorMessage(this, message ?: getString(R.string.unidentified_error))
        }

        setListeners()
    }

    private fun setListeners() {
        button.setOnClickListener {
            showEstimationDialog()
        }
    }

    private fun showEstimationDialog() {
        val bundle = Bundle()
        bundle.putParcelable(FEEDBACK_BUNDLE_TAG, feedback)

        EstimationBottomSheetDialog().run {
            arguments = bundle
            show(supportFragmentManager, EstimationBottomSheetDialog.TAG)
        }
    }
}