package com.example.kotlin_shopping_list_cooroutine.ui.splashFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlin_shopping_list_cooroutine.R
import com.example.kotlin_shopping_list_cooroutine.activities.IMainActivity

class SplashFragment: Fragment() {

//    private val navigator = findNavController()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(requireActivity() is IMainActivity) {
            (requireActivity() as IMainActivity).setToolbarTItle(getString(R.string.splashTitle))
        }
    }
}