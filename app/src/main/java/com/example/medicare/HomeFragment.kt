package com.example.medicare

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

//    private var layoutManager: RecyclerView.LayoutManager? = null
//    private var adapter: RecyclerView.Adapter<AdapterRVHome.ViewHolder>? = null
    private val items: MutableList<DataRVHome> = mutableListOf()
    private val menuAdapter : AdapterRVHome by lazy {
        AdapterRVHome(
            items
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvHome.apply {
            val title = resources.getStringArray(R.array.menu_home_title)
            val icon = resources.obtainTypedArray(R.array.menu_home_icon)

            items.clear()
            for (i in title.indices) {
                items.add(DataRVHome(icon.getResourceId(i, 0), title[i]))
            }
            icon.recycle()
            val glm = GridLayoutManager(context, 3)
            rvHome?.setHasFixedSize(true)
            rvHome?.layoutManager = glm
            rvHome?.adapter = menuAdapter
        }
    }
}