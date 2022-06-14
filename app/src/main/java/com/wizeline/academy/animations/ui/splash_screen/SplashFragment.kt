package com.wizeline.academy.animations.ui.splash_screen

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wizeline.academy.animations.R
import com.wizeline.academy.animations.databinding.SplashFragmentBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private var _binding: SplashFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SplashFragmentBinding.inflate(inflater, container, false)
        setUpXmlAnimations()
        return binding.root
    }

    private fun setUpXmlAnimations() = with(binding) {
        runAnimationSet(R.animator.splash_animator)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            delay(3000)
            goToHomeScreen()
        }
    }

    private fun goToHomeScreen() {
        val directions = SplashFragmentDirections.toMainFragment()
        findNavController().navigate(directions)
    }

    private fun runAnimationSet(animator: Int) {
        (AnimatorInflater.loadAnimator(requireContext(), animator) as? AnimatorSet).apply {
            this?.setTarget(binding.ivWizelineLogo)
        }?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}