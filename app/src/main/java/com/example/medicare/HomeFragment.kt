package com.example.medicare

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter : AdapterRVHome
    private lateinit var recyclerView : RecyclerView
    private lateinit var menuArrayList : ArrayList<DataRVHome>

    lateinit var icon : Array<Int>
    lateinit var title : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitiaize()
        val layoutManager = GridLayoutManager(context, 3)
        recyclerView = view.findViewById(R.id.rvHome)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = AdapterRVHome(menuArrayList)
        recyclerView.adapter = adapter

        btn_check_out.setOnClickListener {
            check_in_info.visibility = View.GONE
        }
    }

    private fun dataInitiaize(){
        menuArrayList = arrayListOf<DataRVHome>()

        icon = arrayOf(
            R.drawable.ic_sertifikat,
            R.drawable.ic_hasil_tes_covid,
            R.drawable.ic_ehac,
            R.drawable.ic_history_checkin,
            R.drawable.ic_aturan_perjalanan,
            R.drawable.ic_teledokter,
            R.drawable.ic_layanan_kesehatan,
            R.drawable.ic_statistic,
            R.drawable.ic_vaksin
        )

        title = arrayOf(
            "Sertifikat Vaksin",
            "Hasil Tes COVID-19",
            "EHAC",
            "Riwayat Check-In",
            "Aturan Perjalanan",
            "Teledokter",
            "Pelayanan Kesehatan",
            "Statistik COVID-19",
            "Daftar Vaksin"
        )

        for (i in icon.indices){
            val menu = DataRVHome(icon[i], title[i])
            menuArrayList.add(menu)
        }
    }
}