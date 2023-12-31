package com.selincengiz.ghibli.presentation.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.common.Extensions.loadUrl
import com.selincengiz.ghibli.common.PermissionUtils
import com.selincengiz.ghibli.common.PermissionUtils.checkPermissionn
import com.selincengiz.ghibli.common.PermissionUtils.shouldShowRationale
import com.selincengiz.ghibli.databinding.FragmentProfileBinding
import com.selincengiz.ghibli.presentation.profile.ProfileFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment  : Fragment() {

    private lateinit var binding:FragmentProfileBinding
    @Inject
    lateinit var auth: FirebaseAuth
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                openGalleryIntent()

            }
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false)
        binding.profileFunctions = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvEmail.text=auth.currentUser!!.email
        binding.tvNamee.text="Hello "+auth.currentUser!!.displayName

        binding.buttonLogout.setOnClickListener {
            auth.signOut()
            findNavController().navigate(ProfileFragmentDirections.profileToSplash())
        }

       auth.currentUser!!.photoUrl?.let {
           binding.ivPic.loadUrl(it)
        }




    }

    fun imageViewClicked(){

        openGalleryIntent()
        requireContext().checkPermissionn( PermissionUtils.galleryPermission,
            onGranted = {
                openGalleryIntent()
            },
            onDenied = {
                requestPermissionLauncher.launch(PermissionUtils.galleryPermission)
            })

        requireActivity().shouldShowRationale(
            PermissionUtils.galleryPermission,
            onGranted = {
                Toast.makeText(
                    requireContext(),
                    R.string.permission_required,
                    Toast.LENGTH_SHORT
                ).show()

                requestPermissionLauncher.launch(
                    PermissionUtils.galleryPermission
                )
            },
        )
    }

    private val GALLERY_REQUEST_CODE = 100
    private fun openGalleryIntent() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            selectedImageUri?.let {
                binding.ivPic.loadUrl(it)
                val profileUpdates = userProfileChangeRequest {

                    photoUri = it
                }
                auth.currentUser?.updateProfile(profileUpdates)
                    ?.addOnCompleteListener { task ->

                        task.addOnSuccessListener {
                            Toast.makeText(requireContext(), "Successfully updated profile!", Toast.LENGTH_SHORT).show()

                        }
                        task.addOnFailureListener {
                            Toast.makeText(requireContext(), "Profile update is failed!", Toast.LENGTH_SHORT).show()
                        }

                    }

            }


        }
    }


}