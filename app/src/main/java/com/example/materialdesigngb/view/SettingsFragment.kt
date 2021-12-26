package com.example.materialdesigngb.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import com.example.materialdesigngb.R
import com.example.materialdesigngb.databinding.FragmentSettingsBinding
import kotlin.math.log
import kotlin.properties.Delegates

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() :FragmentSettingsBinding = _binding!!

    private var i = 0
    private var m = 0

    companion object {
        fun newInstance() = SettingsFragment()
        var currentTheme = R.style.Theme_MaterialDesignGB
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("myLogs", "onViewCreated: ")
        binding.chipGroup.setOnCheckedChangeListener { childGroup, position ->

            currentTheme = when (position) {
                R.id.chip_green -> {
                    Toast.makeText(context, "Выбрана зеленая тема", Toast.LENGTH_SHORT).show()
                    R.style.GreenTheme
                    Log.d("myLogs", "Итерация m ${m++}")
                }
                R.id.chip_red -> {
                    Toast.makeText(context, "Выбрана красная тема", Toast.LENGTH_SHORT).show()
                    R.style.RedTheme
                }
                else -> R.style.Theme_MaterialDesignGB
            }

          (requireActivity() as MainActivity).recreate()
            Log.d("myLogs", "Итерация i ${i++}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("myLogs", "Фрагмент onDestroy: ")
        _binding = null
    }

}