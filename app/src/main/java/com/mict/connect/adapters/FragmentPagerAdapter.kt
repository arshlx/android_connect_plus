package com.mict.connect.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mict.connect.interfaces.PositionInterface
import com.mict.connect.global_objects.Constants.NAV_ASSIGNMENTS
import com.mict.connect.global_objects.Constants.NAV_DISCUSSION
import com.mict.connect.global_objects.Constants.NAV_NUM_TOTAL
import com.mict.connect.global_objects.Constants.NAV_OVERVIEW
import com.mict.connect.global_objects.Constants.NAV_PREFERENCES
import com.mict.connect.main.AssignmentsFragment
import com.mict.connect.main.DiscussionFragment
import com.mict.connect.main.OverviewFragment
import com.mict.connect.main.PreferencesFragment

class FragmentPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val listener: PositionInterface
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = NAV_NUM_TOTAL

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            NAV_OVERVIEW -> {
                listener.onPositionChanged(NAV_OVERVIEW)
                OverviewFragment.newInstance()
            }
            NAV_ASSIGNMENTS -> {
                listener.onPositionChanged(NAV_ASSIGNMENTS)
                AssignmentsFragment.newInstance()
            }
            NAV_DISCUSSION -> {
                listener.onPositionChanged(NAV_DISCUSSION)
                DiscussionFragment.newInstance()
            }
            else -> {
                listener.onPositionChanged(NAV_PREFERENCES)
                PreferencesFragment.newInstance()
            }
        }
    }

}