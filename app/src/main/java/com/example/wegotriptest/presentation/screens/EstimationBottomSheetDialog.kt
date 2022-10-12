package com.example.wegotriptest.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.wegotriptest.R
import com.example.wegotriptest.presentation.models.Feedback
import com.example.wegotriptest.presentation.SeekBarChangeListener
import com.example.wegotriptest.presentation.viewmodels.BaseViewModel
import com.example.wegotriptest.utils.Const.FEEDBACK_BUNDLE_TAG
import com.example.wegotriptest.utils.downloadImage
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_estimation.*
import kotlinx.android.synthetic.main.card_user_avatar.view.*

private const val DEFAULT_SEEKBAR_VALUE = 2

class EstimationBottomSheetDialog : BottomSheetDialogFragment() {

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
        return inflater.inflate(R.layout.bottom_sheet_estimation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        viewModel.avatar.observe(this) { url ->
            downloadImage(
                context = this,
                url = url,
                avatarImageView = cardViewAvatar.imageViewAvatar
            )
        }

        setSeekBarListeners()
        setNextButtonListener()
        textNotAsked.setOnClickListener { dismiss() }
    }

    private fun setSeekBarListeners() {
        seekBarTour.run {
            setOnSeekBarChangeListener(SeekBarChangeListener(emojiTour))
            progress = feedback?.tourRating ?: DEFAULT_SEEKBAR_VALUE
        }
        seekBarGuide.run {
            setOnSeekBarChangeListener(SeekBarChangeListener(emojiGuide))
            progress = feedback?.guideRating ?: DEFAULT_SEEKBAR_VALUE
        }
        seekBarInfo.run {
            setOnSeekBarChangeListener(SeekBarChangeListener(emojiInfo))
            progress = feedback?.infoRating ?: DEFAULT_SEEKBAR_VALUE
        }
        seekBarNavigation.run {
            setOnSeekBarChangeListener(SeekBarChangeListener(emojiNavigation))
            progress = feedback?.navRating ?: DEFAULT_SEEKBAR_VALUE
        }
    }

    private fun setNextButtonListener() {
        buttonNext.setOnClickListener {
            feedback
                ?.copy(
                    tourRating = seekBarTour.progress,
                    guideRating = seekBarGuide.progress,
                    infoRating = seekBarInfo.progress,
                    navRating = seekBarNavigation.progress
                )
                ?.let { viewModel.setFeedback(it) }

            val bundle = Bundle()
            bundle.putParcelable(FEEDBACK_BUNDLE_TAG, feedback)

            FeedbackBottomSheetDialog().let {
                it.arguments = bundle
                it.show(requireActivity().supportFragmentManager, FeedbackBottomSheetDialog.TAG)
            }

            dismiss()
        }
    }

    companion object {
        const val TAG = "estimation_fragment"
    }

}