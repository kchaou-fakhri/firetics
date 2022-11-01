package com.eljem.firetics.view.project

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eljem.firetics.R
import com.eljem.firetics.model.entity.Project
import com.eljem.firetics.vm.ProjectVM
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlin.random.Random

class ProjectAdapter(val context: Context, val projects: ArrayList<Project>,val projectVM: ProjectVM) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        /************ Color of project icon *****************/
        val colors = arrayListOf<Int>(Color.parseColor("#277BC0"),
            Color.parseColor("#FFB200"),
            Color.parseColor("#6C4AB6"),
            Color.parseColor("#4FA095"),
            Color.parseColor("#E26868"),
        )

        fun bind(project: Project, projectVM: ProjectVM){
            itemView.findViewById<TextView>(R.id.project_icon).text = project.name.substring(0,1).uppercase()
            if (position<5){
                itemView.findViewById<TextView>(R.id.project_icon).setBackgroundColor(colors[position])

            }
            else{
                itemView.findViewById<TextView>(R.id.project_icon).setBackgroundColor(colors[Random.nextInt(0,5)])

            }
            itemView.findViewById<TextView>(R.id.project_name).text = project.name
            itemView.findViewById<TextView>(R.id.created_at).text   = project.created_at.toString()

            var switch = itemView.findViewById<SwitchMaterial>(R.id.btn_state)
            switch.isChecked = !project.status.equals("NO")

            itemView.findViewById<SwitchMaterial>(R.id.btn_state).setOnCheckedChangeListener { buttonView, isChecked->
                if (isChecked){

                    project.status = "YES"
                    projectVM.updateProject(project)
                }
                else
                {
                    project.status = "NO"
                    projectVM.updateProject(project)
                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.project_item, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(projects.get(position), projectVM)
    }

    override fun getItemCount(): Int {
        return projects.size
    }


}