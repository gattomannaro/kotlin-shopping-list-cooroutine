package com.example.kotlin_shopping_list_cooroutine.ui.splashFragment

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.kotlin_shopping_list_cooroutine.R
import com.example.kotlin_shopping_list_cooroutine.activities.IMainActivity
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : Fragment() {

    private lateinit var navigator: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navigator = findNavController()
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animation_view.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
                // not needed
            }

            override fun onAnimationEnd(p0: Animator?) {
                navigator.navigate(R.id.splashFragment_to_shoppingListDashboardFragment)
            }

            override fun onAnimationCancel(p0: Animator?) {
                // not needed

            }

            override fun onAnimationStart(p0: Animator?) {
                // not needed
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (requireActivity() is IMainActivity) {
            (requireActivity() as IMainActivity).setToolbarVisibility(View.GONE)
        }
    }
}