package com.pranavkumar.BaseAdaptar

import android.app.AlertDialog
import android.app.Dialog
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.pranavkumar.BaseAdaptar.databinding.ActivityMainBinding
import com.pranavkumar.BaseAdaptar.databinding.LayoutAddBinding
import com.pranavkumar.BaseAdaptar.databinding.LayoutEditBinding

class MainActivity : AppCompatActivity(), ClickInterface {
    lateinit var binding: ActivityMainBinding
    lateinit var listAdapter: ListAdapter
    private var array = ArrayList<MyModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listAdapter = ListAdapter(array, this)
        binding.listView.adapter = listAdapter
        binding?.fabAdd?.setOnClickListener{
            var dialogBinding = LayoutAddBinding.inflate(layoutInflater)
            var dialog = Dialog(this)
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT)
            dialogBinding.btnAdd.setOnClickListener{
                if(dialogBinding.etName.text.toString().isNullOrEmpty()){
                    dialogBinding.etName.setError("Enter Name")
                }else if(dialogBinding.etRollno.text.toString().isNullOrEmpty()){
                    dialogBinding.etRollno.setError("Enter Roll No")
                }else if(dialogBinding.etPhoneNo.text.toString().isNullOrEmpty()){
                    dialogBinding.etPhoneNo.setError("Enter Phone No")
                }else{
                    array.add(MyModel(dialogBinding.etName.text.toString(),
                        dialogBinding.etRollno.text.toString(),
                        dialogBinding.etPhoneNo.text.toString()))
                    dialog.dismiss()
                    listAdapter.notifyDataSetChanged()
                }

            }
            dialog.show()
        }



    }
    override fun deleteClick(position: Int){
        var alertDialog =AlertDialog.Builder(this)
        alertDialog.setTitle("Delete Name")
        alertDialog.setMessage("Do you want to delete name")
        alertDialog.setCancelable(false)
        alertDialog.setNegativeButton("No"){_,_->
            alertDialog.setCancelable(true)
        }
        alertDialog.setPositiveButton("Yes"){_,_->
            Toast.makeText(this,"This Name is Deleted ", Toast.LENGTH_SHORT).show()
            array.removeAt(position)
            listAdapter.notifyDataSetChanged()
        }
        alertDialog.show()

    }
    override fun editClick(position: Int){
        var dialogBinding = LayoutEditBinding.inflate(layoutInflater)
        var dialog = Dialog(this)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialogBinding.etName.setText(array[position].name)
        dialogBinding.etRollno.setText(array[position].rollno)
        dialogBinding.etPhoneNo.setText(array[position].phoneno)
        dialogBinding.btnUpdate.setOnClickListener{
            if(dialogBinding.etName.text.toString().isNullOrEmpty()){
                dialogBinding.etName.setError("Enter Name")
            }else if(dialogBinding.etRollno.text.toString().isNullOrEmpty()){
                dialogBinding.etRollno.setError("Enter Roll No")
            }else if(dialogBinding.etPhoneNo.text.toString().isNullOrEmpty()) {
                dialogBinding.etPhoneNo.setError("Enter Phone No")
            }else{
                array[position] = (MyModel(dialogBinding.etName.text.toString(),
                    dialogBinding.etRollno.text.toString(),
                    dialogBinding.etPhoneNo.text.toString()))
                dialog.dismiss()
                listAdapter.notifyDataSetChanged()
            }
        }
        dialog.show()
    }
}