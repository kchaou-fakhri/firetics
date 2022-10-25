package com.eljem.firetics.view.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eljem.firetics.databinding.FragmentAddNewProjectBinding
import com.eljem.firetics.model.entity.Project
import com.eljem.firetics.vm.ProjectVM
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.DoubleBounce


class AddNewProjectFragment : Fragment() {

    private lateinit var _binding: FragmentAddNewProjectBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewProjectBinding.inflate(inflater, container, false)




        val project = Project("test", "e-learning-cd263", "AIzaSyDD5w1xBL-DNcn_F4kEzkSaVRvFipkQRVg")


        binding.frFirestore.setOnCheckedChangeListener {compoundButton, b ->
            if (binding.frFirestore.isChecked) {
                project.firestoreDB = "YES"
            } else {
                project.firestoreDB = "NO"
            }
        }
        binding.frRealtime.setOnCheckedChangeListener { compoundButton, b ->
            if (binding.frRealtime.isChecked) {
                project.realtimeDB = "YES"
            } else {
                project.realtimeDB = "NO"
            }
        }

        binding.frAuthentication.setOnCheckedChangeListener { compoundButton, b ->
            if (binding.frAuthentication.isChecked) {
                project.authentication = "YES"

            } else {
                project.authentication = "NO"
            }
        }


        binding.frStorage.setOnCheckedChangeListener { compoundButton, b ->
            if (binding.frStorage.isChecked) {
                project.storage = "YES"
            } else {
                project.authentication = "NO"
            }
        }




        val projectVM = ProjectVM()
        binding.btnConnect.setOnClickListener {
            if (project.authentication.equals("YES")){
                binding.spinKitAuth.visibility = View.VISIBLE
                binding.frAuthentication.isEnabled = false
            }
            if (project.realtimeDB.equals("YES")){
                binding.spinKitRealtimeDb.visibility = View.VISIBLE
                binding.frRealtime.isEnabled = false
            }
            if (project.storage.equals("YES")){
                binding.spinKitStorage.visibility = View.VISIBLE
                binding.frStorage.isEnabled = false
            }
            if (project.firestoreDB.equals("YES")){
                binding.spinKitFirestore.visibility = View.VISIBLE
                binding.frFirestore.isEnabled = false
            }
            binding.btnConnect.isEnabled = false
            projectVM.connectToFireBase(project, requireContext()).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            })
        }

        return binding.root
    }



}