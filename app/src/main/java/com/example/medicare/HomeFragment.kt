package com.example.medicare

import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var adapter : MenuAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var menuArrayList : ArrayList<Menu>

    lateinit var icon : TypedArray
    lateinit var title : Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitiaize()
        val layoutManager = GridLayoutManager(context, 3)
        recyclerView = view.findViewById(R.id.rv_home)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MenuAdapter(menuArrayList)
        recyclerView.adapter = adapter

        btn_check_out.setOnClickListener {
            cv_check_in_info.visibility = View.GONE
        }
    }

    private fun dataInitiaize(){
        menuArrayList = arrayListOf()

        icon = resources.obtainTypedArray(R.array.menu_home_icon)
        title = resources.getStringArray(R.array.menu_home_title)


//        icon = arrayOf(
//            R.drawable.ic_sertifikat,
//            R.drawable.ic_hasil_tes_covid,
//            R.drawable.ic_ehac,
//            R.drawable.ic_history_checkin,
//            R.drawable.ic_aturan_perjalanan,
//            R.drawable.ic_teledokter,
//            R.drawable.ic_layanan_kesehatan,
//            R.drawable.ic_statistic,
//            R.drawable.ic_vaksin
//        )

//        title = arrayOf(
//            "Sertifikat Vaksin",
//            "Hasil Tes COVID-19",
//            "EHAC",
//            "Riwayat Check-In",
//            "Aturan Perjalanan",
//            "Teledokter",
//            "Pelayanan Kesehatan",
//            "Statistik COVID-19",
//            "Daftar Vaksin"
//        )

        for (i in title.indices){
            val menu = Menu(icon.getResourceId(i,0), title[i])
            menuArrayList.add(menu)
        }
        icon.recycle()
    }
}