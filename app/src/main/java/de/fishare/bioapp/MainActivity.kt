package de.fishare.bioapp

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MainActivity : Activity() {
    private lateinit var adapter: ListAdapter
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListView()
    }

    private fun initListView(){
        adapter = ListAdapter()
        adapter.dataSource = object : ListAdapter.AdapterDataSource {
            override fun numberOfRowIn(section: Int): Int {
                return 10
            }

            override fun numberOfSection(): Int {
                return 1
            }

            override fun onBindOfRow(vh: RecyclerView.ViewHolder, indexPath: ListAdapter.IndexPath) {
                setItemViewContent(vh as TableCellItem, indexPath)
            }

            override fun cellForRow(parent: ViewGroup, section: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(applicationContext).inflate(R.layout.cell_device, parent, false)
                return TableCellItem(view)
            }
        }

        adapter.listener = object : ListAdapter.ItemEvent{
            override fun onItemClick(view: View, indexPath: ListAdapter.IndexPath) {
                when(view.id){
                    R.id.btnConnect -> {
                        val vh = getRenderItem(indexPath)
                    }
                }
            }
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setItemViewContent(v:TableCellItem, indexPath: ListAdapter.IndexPath){
            v.lblName.text = indexPath.row.toString()
    }

    private fun getRenderItem(indexPath: ListAdapter.IndexPath):TableCellItem?{
        val position = adapter.getPositionOf(indexPath)
        val view = recyclerView.findViewHolderForLayoutPosition(position)?.itemView
        return if(view != null){ TableCellItem(view) } else null
    }
}
