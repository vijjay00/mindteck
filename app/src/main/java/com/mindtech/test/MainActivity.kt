package com.mindtech.test

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    val viewPagerImages = ArrayList<String>()
    val recyclerViewImages = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         addViewPagerList()
         addRecyclerViewList()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL))
        recyclerView.hasFixedSize()
        val adapter = RecyclerAdapter(applicationContext,recyclerViewImages)
        recyclerView.adapter = adapter

        viewPager.adapter = MainPagerAdapter(this,viewPagerImages)
        viewPager.offscreenPageLimit = 4

        TabLayoutMediator(dots, viewPager) { _, _ ->

        }.attach()

        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.getFilter().filter(newText)
                return false
            }

        })

        viewPager?.registerOnPageChangeCallback(object : OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0, 2 -> {
                        recyclerViewImages.sort()
                        adapter.updateList(recyclerViewImages)
                    }
                    1, 3 -> {
                        recyclerViewImages.reverse()
                        adapter.updateList(recyclerViewImages)
                    }
                    else -> {
                        Toast.makeText(this@MainActivity,"No data",Toast.LENGTH_SHORT).show()
                    }
                }

            }
        })

        viewPagerTransformer()
    }

    private fun viewPagerTransformer() {
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(70))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r + 0.15f
        }
        viewPager.setPageTransformer(compositePageTransformer)
    }

    private fun addRecyclerViewList() {
        recyclerViewImages.addAll(viewPagerImages)
        recyclerViewImages.add("pinkflower")
        recyclerViewImages.add("purple")
        recyclerViewImages.add("rose")
        recyclerViewImages.add("sunflower")
        recyclerViewImages.add("tulips")
        recyclerViewImages.add("twinkle")
        recyclerViewImages.add("wings")
    }

    private fun addViewPagerList() {
        viewPagerImages.add("greenflower")
        viewPagerImages.add("highflower")
        viewPagerImages.add("blue")
        viewPagerImages.add("lilly")
    }
}