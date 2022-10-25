package com.eljem.firetics.view.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eljem.firetics.databinding.FragmentAddNewProjectBinding
import com.eljem.firetics.model.entity.Project
import com.eljem.firetics.vm.ProjectVM
import java.util.Observer


class AddNewProjectFragment : Fragment() {

    private lateinit var _binding:  FragmentAddNewProjectBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewProjectBinding.inflate(inflater,container, false)

        val projectVM = ProjectVM()
        binding.btnConnect.setOnClickListener {
            val project = Project("test","e-learning-cd263","AIzaSyDD5w1xBL-DNcn_F4kEzkSaVRvFipkQRVg")
            projectVM.connectToFireBase(project, requireContext()).observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            })
        }

        return binding.root
    }


}