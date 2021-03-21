package com.vikas.fitbit.ui.login.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.vikas.fitbit.R
import com.vikas.fitbit.databinding.SingleSliderViewBinding
import java.util.*


/**
 *<h1>SliderAdapter</h1>
 * <p>this is the adapter class for slider in login</p>
 * @author : Vikas Suthar
 * @since : 13 March 2021
 * @version : 1.0
 */

class SliderAdapter(
    private var sliderTextList: ArrayList<String>,
    private var imageList: ArrayList<Int>
): PagerAdapter() {


    override fun getCount(): Int = sliderTextList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }




    @SuppressLint("UseCompatLoadingForDrawables")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val binding =  SingleSliderViewBinding.inflate(
            LayoutInflater.from(container.context),
            container,
            false
        )
            //SingleSliderViewBinding.inflate(LayoutInflater.from(context),  false)



        if(position == 0) {
            binding.imageCardView.visibility = View.GONE
        } else {
            binding.imageCardView.visibility = View.VISIBLE
            binding.ivImage.setImageResource(imageList[position])
        }

        when(position) {
            0 -> {
                binding.view.background = container.context.getDrawable(R.drawable.slider_one_bg)
            }
            1 -> {
                binding.root.background = container.context.getDrawable(R.drawable.slider_two_bg)
            }
            2 -> {
                binding.root.background = container.context.getDrawable(R.drawable.slider_three_bg)
            }
            3 -> {
                binding.root.background = container.context.getDrawable(R.drawable.slider_four_bg)
            }
            4 -> {
                binding.root.background = container.context.getDrawable(R.drawable.slider_five_bg)
            }

        }


        binding.tvSliderText.text = sliderTextList[position]
        val viewPager = container as ViewPager
        viewPager.addView(binding.root, 0)
        return binding.root
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }


}