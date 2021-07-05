package com.task.nycnewsarticles.foundatiion.utilz

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.task.nycnewsarticles.R


fun FragmentManager.replaceFragment(@IdRes containerId: Int, fragment: Fragment,
                                    isAddToBackStack: Boolean = true) {
    beginTransaction()
            .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out)
        .apply {
        add(containerId, fragment)
        if(isAddToBackStack) addToBackStack(fragment::class.java.simpleName)
    }.
    commitAllowingStateLoss()
}

fun FragmentManager.replaceFragmentWithoutAnimation(@IdRes containerId: Int, fragment: Fragment,
                                    isAddToBackStack: Boolean = true) {
    beginTransaction()
            .apply {
                add(containerId, fragment)
                if(isAddToBackStack) addToBackStack(fragment::class.java.simpleName)
            }.
            commitAllowingStateLoss()
}

fun FragmentManager.replaceAsBotomSheet(@IdRes containerId: Int, fragment: Fragment,
                                        isAddToBackStack: Boolean = true) {
    beginTransaction()
            .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
            .apply {
                add(containerId, fragment)
                if(isAddToBackStack) addToBackStack(fragment::class.java.simpleName)
            }.
            commitAllowingStateLoss()
}

fun FragmentManager.replaceFragmentandDeleteold(@IdRes containerId: Int, fragment: Fragment,
                                    isAddToBackStack: Boolean = true) {
    beginTransaction()
            .apply {
                replace(containerId, fragment)
                if(isAddToBackStack) addToBackStack(fragment::class.java.simpleName)
                if (fragments.size>1)
                     remove(fragments[0])
            }.
            commitAllowingStateLoss()
}
fun FragmentManager.replaceFragmentWithOnResume(@IdRes containerId: Int, fragment: Fragment,
                                    isAddToBackStack: Boolean = true) {
    beginTransaction()
        .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out)
        .apply {
            replace(containerId, fragment)
            if(isAddToBackStack) addToBackStack(fragment::class.java.simpleName)
        }.
        commitAllowingStateLoss()

fun FragmentManager.replaceRightToLeft(@IdRes containerId: Int, fragment: Fragment,
                                       isAddToBackStack: Boolean = true) {
    beginTransaction()
        .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
        .apply {
            add(containerId, fragment)
            if(isAddToBackStack) addToBackStack(fragment::class.java.simpleName)
        }.
        commitAllowingStateLoss()
}

    fun FragmentManager.replaceAsBotomSheet(@IdRes containerId: Int, fragment: Fragment,
                                            isAddToBackStack: Boolean = true) {
        beginTransaction()
            .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
            .apply {
                add(containerId, fragment)
                if(isAddToBackStack) addToBackStack(fragment::class.java.simpleName)
            }.
            commitAllowingStateLoss()
    }
}
