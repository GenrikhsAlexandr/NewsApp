package com.genrikhsalexandr.newsapp.presentation

import android.animation.Animator
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.LottieAnimationView
import com.genrikhsaleksandr.core.data.database.AppDatabase
import com.genrikhsalexandr.newsapp.R
import com.genrikhsalexandr.newsapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.days

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animationView: LottieAnimationView = binding.lottieAnimationView
        animationView.playAnimation()

        animationView.addAnimatorListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                database = AppDatabase.getInstance(applicationContext)
                database.articleRequestDao()
                lifecycleScope.launch {
                    checkAndDeleteOldArticles()
                }

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

    private fun checkAndDeleteOldArticles() {
        lifecycleScope.launch {
            database.articleRequestDao().getArticleFromDb().collect { articles ->
                val oldArticles = articles.filter {
                    it.createdAt < System.currentTimeMillis() - 14.days.inWholeMilliseconds
                }
                println("oldArticles = $oldArticles")
                println("System.currentTimeMillis = ${System.currentTimeMillis() - 14.days.inWholeMilliseconds}")

                if (oldArticles.isNotEmpty()) {
                    withContext(Dispatchers.IO) {
                        database.articleRequestDao().deleteArticle(oldArticles.first())
                    }
                }
            }
        }
    }
}