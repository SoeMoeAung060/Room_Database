package android.example.myroomdatabase.fragments.add

import android.example.myroomdatabase.R
import android.example.myroomdatabase.model.User
import android.example.myroomdatabase.viewModel.UserViewModel
import android.example.myroomdatabase.databinding.FragmentAddBinding
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class addFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var _binding : FragmentAddBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        _binding = FragmentAddBinding.inflate(inflater, container, false)

        _binding.addBtn.setOnClickListener(View.OnClickListener {
            insertDataToDatabase()
        })
        return binding.root
    }

    private fun insertDataToDatabase() {
        val firstName = _binding.addFirstNameEt.text.toString()
        val lastName = _binding.addLastNameEt.text.toString()
        val age = _binding.addAgeEt.text.toString()

        if(inputCheck(firstName,lastName,age)){
            //Create User Object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()) )
            //Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            //Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(firstName : String, lastName : String, age : String) : Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}