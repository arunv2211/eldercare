package com.eldercare.ui.home
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.eldercare.R
import com.eldercare.databinding.FragmentHomeBinding
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.layoutProfile.setOnClickListener {
            findNavController().navigate(R.id.navigation_profile)
        }
        binding.layoutHistory.setOnClickListener {
            findNavController().navigate(R.id.navigation_history)
        }
        binding.layoutRemainders.setOnClickListener {
            findNavController().navigate(R.id.navigation_remainder)
        }
        binding.layoutSettings.setOnClickListener {
            findNavController().navigate(R.id.navigation_settings)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}