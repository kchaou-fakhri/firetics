package com.eljem.firetics.view.project

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import com.eljem.firetics.R
import com.eljem.firetics.databinding.FragmentAddNewProjectBinding
import com.eljem.firetics.model.entity.Project
import com.eljem.firetics.utils.FalureAlert
import com.eljem.firetics.utils.MessageResult
import com.eljem.firetics.utils.SuccessAlert
import com.eljem.firetics.vm.ProjectVM
import java.io.File


class AddNewProjectFragment : Fragment() {

    private lateinit var _binding: FragmentAddNewProjectBinding
    private val binding get() = _binding
    lateinit var  projectVM : ProjectVM
    lateinit var project :Project
    lateinit var listProjects : ArrayList<Project>
    var nbFeatureUsed = 1 // to check number of feature used
    var nbOfFeatureSuccess  =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewProjectBinding.inflate(inflater, container, false)


         project = Project()
       projectVM = ViewModelProvider(this).get(ProjectVM::class.java)

        projectVM.getAllProjects().observe(viewLifecycleOwner, Observer {
            listProjects = it as ArrayList<Project>
        })

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
                nbFeatureUsed++
            } else {
                project.realtimeDB = "NO"
                nbFeatureUsed--
            }
        }

        binding.frAuthentication.setOnCheckedChangeListener { compoundButton, b ->
            if (binding.frAuthentication.isChecked) {
                project.authentication = "YES"
                nbFeatureUsed++
            } else {
                project.authentication = "NO"
                nbFeatureUsed--
            }
        }


        binding.frStorage.setOnCheckedChangeListener { compoundButton, b ->
            if (binding.frStorage.isChecked) {
                project.storage = "YES"
                nbFeatureUsed++
            } else {
                project.authentication = "NO"
                nbFeatureUsed--
            }
        }





        binding.btnConnect.setOnClickListener {





                var tempNbOfResult = 0



                project.name = binding.projectName.text.toString()
                project.projectID = binding.projectId.text.toString()
                project.apiKey = binding.apiKey.text.toString()

            if (verifProjectIfexist(project)) {
                binding.btnConnect.isEnabled = false

                if (project.authentication.equals("YES")) {
                    binding.spinKitAuth.visibility = View.VISIBLE
                    binding.frAuthentication.isEnabled = false

                    projectVM.validateConnectionWithFirebaseAuthentication(
                        project,
                        requireContext()
                    ).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                        binding.spinKitAuth.visibility = View.GONE
                        if (it.message == "YES") {
                            Glide.with(this)
                                .load(R.drawable.ic_connection_ok)
                                .into(binding.imgAuth)
                            nbOfFeatureSuccess++

                        }
                        if (it.message == "NO") {
                            Glide.with(this)
                                .load(R.drawable.ic_close)
                                .into(binding.imgAuth)
                            nbOfFeatureSuccess++

                        }

                        tempNbOfResult++
                        if (tempNbOfResult.equals(nbFeatureUsed)) {
                            checkConnectionStatus()
                        }
                        binding.imgAuth.visibility = View.VISIBLE
                    })

                }


                if (project.realtimeDB.equals("YES")) {
                    binding.spinKitRealtimeDb.visibility = View.VISIBLE
                    binding.frRealtime.isEnabled = false


                    projectVM.validateConnectionWithRealtimeDatabase(project, requireContext())
                        .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                            binding.spinKitRealtimeDb.visibility = View.GONE
                            if (it.message == "YES") {
                                Glide.with(this)
                                    .load(R.drawable.ic_connection_ok)
                                    .into(binding.imgRealtimeDb)
                                nbOfFeatureSuccess++


                            }
                            if (it.message == "NO") {
                                Glide.with(this)
                                    .load(R.drawable.ic_close)
                                    .into(binding.imgRealtimeDb)
                                nbOfFeatureSuccess--

                            }
                            tempNbOfResult++
                            if (tempNbOfResult.equals(nbFeatureUsed)) {
                                checkConnectionStatus()
                            }
                            binding.imgRealtimeDb.visibility = View.VISIBLE

                        })

                }

                if (project.storage.equals("YES")) {
                    binding.spinKitStorage.visibility = View.VISIBLE
                    binding.frStorage.isEnabled = false

                    projectVM.validateConnectionWithFirebaseStorage(project, requireContext())
                        .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                            binding.spinKitStorage.visibility = View.GONE
                            if (it.message == "YES") {
                                Glide.with(this)
                                    .load(R.drawable.ic_connection_ok)
                                    .into(binding.imgStorage)
                                nbOfFeatureSuccess++

                            }
                            if (it.message == "NO") {
                                Glide.with(this)
                                    .load(R.drawable.ic_close)
                                    .into(binding.imgStorage)
                                nbOfFeatureSuccess--

                            }

                            tempNbOfResult++
                            if (tempNbOfResult.equals(nbFeatureUsed)) {
                                checkConnectionStatus()
                            }
                            binding.imgStorage.visibility = View.VISIBLE
                        })

                }

                if (project.firestoreDB.equals("YES")) {
                    binding.spinKitFirestore.visibility = View.VISIBLE
                    binding.frFirestore.isEnabled = false


                    projectVM.validateConnectionWithFireStore(project, requireContext())
                        .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                            binding.spinKitFirestore.visibility = View.GONE
                            if (it.message == "YES") {
                                Glide.with(this)
                                    .load(R.drawable.ic_connection_ok)
                                    .into(binding.imgFirestore)
                                nbOfFeatureSuccess++
                            }
                            if (it.message == "NO") {
                                Glide.with(this)
                                    .load(R.drawable.ic_close)
                                    .into(binding.imgFirestore)
                                nbOfFeatureSuccess--
                            }

                            binding.imgFirestore.visibility = View.VISIBLE

                            tempNbOfResult++
                            if (tempNbOfResult.equals(nbFeatureUsed)) {
                                checkConnectionStatus()
                            }
                        })


                }

            }
            else{
                ifProjectExist()
            }
        }

        return binding.root
    }



    private fun checkConnectionStatus(){
        Thread(Runnable {

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                if (nbOfFeatureSuccess== nbFeatureUsed){
                    val successAlert = SuccessAlert(requireActivity())
                    successAlert.startLoadingAlert()
                    projectVM.insertProjectToDatabase(project)
                }
                else{
                    val failureAlert = FalureAlert(requireActivity(),"Please check your internet connection or try again later, your operation is aborted","Connection failed")
                    failureAlert.startLoadingAlert()
                }

            }, 1000) // It will wait 10 sec before updating UI
        }).start()

    }



    private fun verifProjectIfexist(project: Project) : Boolean{

        var verif = true
        for (item : Project in listProjects){
            if (item.name == project.name || item.projectID == project.projectID || item.apiKey == project.apiKey){
                verif = false
            }
        }
        return verif
    }

    private fun ifProjectExist(){

                    val failureAlert = FalureAlert(requireActivity(),"Project already exist, please insert a new project", "Sorry")
                    failureAlert.startLoadingAlert()

    }

}