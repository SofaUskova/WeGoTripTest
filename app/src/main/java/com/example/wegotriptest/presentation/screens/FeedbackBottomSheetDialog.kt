package com.example.wegotriptest.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.wegotriptest.R
import com.example.wegotriptest.presentation.models.Feedback
import com.example.wegotriptest.presentation.viewmodels.BaseViewModel
import com.example.wegotriptest.utils.Const.FEEDBACK_BUNDLE_TAG
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_feedback.*
import kotlinx.android.synthetic.main.card_user_avatar.view.*

class FeedbackBottomSheetDialog : BottomSheetDialogFragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(BaseViewModel::class.java) }
    private var feedback: Feedback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)

        feedback = this.arguments?.getParcelable(FEEDBACK_BUNDLE_TAG)
        viewModel.getAvatarUserAvatar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_feedback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        initObserves()

        editTextLike.setText(feedback?.features)
        editTextUpgrade.setText(feedback?.wishes)

        buttonNext.setOnClickListener {
            feedback
                ?.copy(
                    features = editTextLike.text.toString(),
                    wishes = editTextUpgrade.text.toString()
                )
                ?.let { viewModel.setFeedback(it) }

            dismiss()
        }

        textNotAsked.setOnClickListener { dismiss() }
    }

    private fun initObserves() {
        viewModel.avatar.observe(this) { url ->
            com.example.wegotriptest.utils.downloadImage(
                context = this,
                url = url,
                avatarImageView = cardViewAvatar.imageViewAvatar
            )
        }
    }

    companion object {
        const val TAG = "feedback_fragment"
    }

}