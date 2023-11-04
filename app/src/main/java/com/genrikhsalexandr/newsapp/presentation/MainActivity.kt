package com.genrikhsalexandr.newsapp.presentation

import android.animation.Animator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import com.airbnb.lottie.LottieAnimationView
import com.genrikhsalexandr.newsapp.R
import com.genrikhsalexandr.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animationView: LottieAnimationView = binding.lottieAnimationView
        animationView.playAnimation()

        animationView.addAnimatorListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                supportFragmentManager.commit {
                    replace(R.id.container, MainFragment.newInstance())
                }
                binding.lottieAnimationView.isVisible = false
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }
        })
    }

}