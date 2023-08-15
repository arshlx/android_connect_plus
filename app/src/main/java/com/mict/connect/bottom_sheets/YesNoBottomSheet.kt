package com.mict.connect.bottom_sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mict.connect.databinding.BottomSheetYesNoBinding
import com.mict.connect.interfaces.TagPositionInterface
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class YesNoBottomSheet(
    private val clickInterface: TagPositionInterface,
    private val title: String,
    private val desc: String,
    private val negativeClick: String,
    private val positiveClick: String
) :
    BottomSheetDialogFragment() {

    private var _binding: BottomSheetYesNoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetYesNoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            titleTxt.text = title
            descriptionTxt.text = desc
            closeImg.setOnClickListener { dismiss() }
            noTxt.apply {
                text = negativeClick
                setOnClickListener { dismiss() }
            }
            yesTxt.apply {
                text = positiveClick
                setOnClickListener {
                    clickInterface.onClick("", 1)
                    dismiss()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}