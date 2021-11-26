package com.fernandoherrera.hackernewsapp.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fernandoherrera.hackernewsapp.R
import com.fernandoherrera.hackernewsapp.databinding.FragmentSplashBinding



class SplashFragment : Fragment() {

    lateinit var bottomAnim: Animation
    lateinit var topAnim: Animation
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        topAnim = AnimationUtils.loadAnimation(context, R.anim.top_animation)
        bottomAnim= AnimationUtils.loadAnimation(context, R.anim.bottom_anim)


       binding.imageView.setAnimation(topAnim)
       binding.textView.setAnimation(bottomAnim)
        Handler(Looper.myLooper()!!).postDelayed(
            {
                findNavController().navigate(R.id.action_nav_splash_to_nav_main)
            },
            2000
        )
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

