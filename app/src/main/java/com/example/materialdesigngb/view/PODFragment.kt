package com.example.materialdesigngb.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.materialdesigngb.R
import com.example.materialdesigngb.databinding.FragmentMainBinding
import com.example.materialdesigngb.viewmodel.PODDataState
import com.example.materialdesigngb.viewmodel.PODViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PODFragment : Fragment() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() :FragmentMainBinding = _binding!!

    companion object {
        fun newInstance() = PODFragment()
    }

    private val viewModel: PODViewModel by lazy {
        ViewModelProvider(this).get(PODViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.sendServerRequest()
        binding.inputLayout.setEndIconOnClickListener {
            val i = Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.textInputEditText.text.toString()}")
            }
            startActivity(i)
        }
        view.setOnClickListener { binding.textInputEditText.clearFocus() }
        bottomSheetBehavior = BottomSheetBehavior.from(binding.includeLayout.bottomSheetContainer)
        binding.includeLayout.bottomSheetDescriptionHeader.text = "Описание"

    }

    private fun renderData(podDataState: PODDataState) {
        when (podDataState) {
            is PODDataState.Error -> {

            }
            is PODDataState.Loading -> {
            }
            is PODDataState.Success -> {
                if (podDataState.serverResponseData.mediaType == "video") {
                    Toast.makeText(requireContext(), R.string.type_video, Toast.LENGTH_LONG).show()
                } else {
                    binding.imageView.load(podDataState.serverResponseData.url) {
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                    }
                }
                binding.includeLayout.bottomSheetDescription.text =
                    podDataState.serverResponseData.explanation
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}