package com.eljem.firetics.view.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eljem.firetics.databinding.FragmentListProjectBinding
import com.eljem.firetics.model.entity.Project
import com.eljem.firetics.vm.ProjectVM


class ListProjectFragment : Fragment() {

    lateinit var _binding: FragmentListProjectBinding
    private val binding get() = _binding
    lateinit var projectVM: ProjectVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      _binding = FragmentListProjectBinding.inflate(inflater, container, false)

        projectVM = ViewModelProvider(this).get(ProjectVM::class.java)

        projectVM.getAllProjects().observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){


            val projectAdapter = ProjectAdapter(requireContext() , it as ArrayList<Project>, projectVM)
            binding.rcvProjects.layoutManager = LinearLayoutManager(requireContext())
            binding.rcvProjects.adapter = projectAdapter
            }
        })



        return binding.root
    }


}