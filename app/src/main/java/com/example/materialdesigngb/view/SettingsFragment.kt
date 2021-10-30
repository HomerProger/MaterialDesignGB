package com.example.materialdesigngb.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import com.example.materialdesigngb.R
import com.example.materialdesigngb.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() :FragmentSettingsBinding = _binding!!

    companion object {
        fun newInstance() = SettingsFragment()
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
            binding.chipGroup.setOnCheckedChangeListener { childGroup, position ->
            when (position) {
                1 -> {
                    changeTheme(R.style.GreenTheme)
                    Toast.makeText(context,"Выбрана зеленая тема", Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    changeTheme(R.style.RedTheme)
                    Toast.makeText(context,"Выбрана красная тема", Toast.LENGTH_SHORT).show()
                }
            }

        }
//        binding.switchTheme.setOnCheckedChangeListener{ buttonView, isChecked ->
//            if(isChecked){
//                changeTheme(R.style.GreenTheme)
//            }
//        }
//        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
//            val theme = when(checkedId){
//                R.id.radioButtonGreen -> R.style.GreenTheme
//                R.id.radioButtonRed   -> R.style.RedTheme
//                else -> R.style.Theme_MaterialDesignGB
//            }
//            changeTheme(theme)
//        }


    }
private fun changeTheme(themeID: Int){
    (requireActivity() as? MainActivity)?.let {
        it.changeTheme(themeID)
    }
}
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}