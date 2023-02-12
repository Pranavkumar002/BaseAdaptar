package com.pranavkumar.BaseAdaptar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.coroutines.NonDisposableHandle.parent

class ListAdapter(var array: ArrayList<MyModel>,var clickInterface: ClickInterface):BaseAdapter() {
    override fun getCount(): Int {
        return array.size
    }

    override fun getItem(position: Int): MyModel {
        return array[position]
    }

    override fun getItemId(position: Int): Long {
        return 123
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{
        var initview = LayoutInflater.from(parent?.context).inflate(R.layout.layout_recycler, parent, false)

        var etname = initview.findViewById<TextView>(R.id.tvName)
        var etrollno= initview.findViewById<TextView>(R.id.tvRollno)
        var etPhoneNo= initview.findViewById<TextView>(R.id.tvPhoneno)
        var ivDelete : ImageButton =initview.findViewById(R.id.ivDelete)
        var ivEdit : ImageButton = initview.findViewById(R.id.ivEdit)
        etname.setText(array[position].name)
        etrollno.setText(array[position].rollno.toString())
        etPhoneNo.setText(array[position].phoneno.toString())

        ivDelete.setOnClickListener{
            clickInterface.deleteClick(position)
        }
        ivEdit.setOnClickListener{
            clickInterface.editClick(position)
        }

        return initview
    }
}