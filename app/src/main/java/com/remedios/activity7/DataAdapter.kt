package com.remedios.activity7

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import java.net.FileNameMap

class DataAdapter(private val students: List<Student>, private val context: Context):
    RecyclerView.Adapter<DataAdapter.StudentViewHolder>() {

    companion object {
        private const val REQUEST_CODE_UPLOAD = 1
        private const val REQUEST_CODE_CAMERA = 2
    }

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.student_name)
        val courseTextView: TextView = itemView.findViewById(R.id.student_course)
        val taglineTextView: TextView = itemView.findViewById(R.id.student_college)
        val btnUpload: Button = itemView.findViewById(R.id.button_Upload)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_layout, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataAdapter.StudentViewHolder, position: Int) {
        val student = students[position]
        holder.nameTextView.text = student.name
        holder.courseTextView.text = student.course
        holder.taglineTextView.text = student.college
        holder.btnUpload.setOnClickListener{
            val popupMenu = PopupMenu(holder.itemView.context, holder.btnUpload)
            popupMenu.menuInflater.inflate(R.menu.upload_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId){
                    R.id.upload -> {
                        val intent = Intent(Intent.ACTION_PICK)
                        intent.type = "image/*"
                        if(context is ComponentActivity){
                            context.startActivityForResult(intent, REQUEST_CODE_UPLOAD)
                        }else{
                            Toast.makeText(context, "startActivityForResult not supported", Toast.LENGTH_SHORT).show()
                        }
                        true
                    }
                    R.id.open_camera -> {
                        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        if(intent.resolveActivity(context.packageManager) != null){
                            if(context is ComponentActivity){
                                context.startActivityForResult(intent, REQUEST_CODE_CAMERA)
                            }else{
                                Toast.makeText(context, "startActivityforResult not supported", Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Toast.makeText(holder.itemView.context, "Unfound Camera", Toast.LENGTH_SHORT).show()
                        }
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }


}