package android.example.myroomdatabase.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.example.myroomdatabase.R
import android.example.myroomdatabase.databinding.FragmentUpdateBinding
import android.example.myroomdatabase.model.User
import android.example.myroomdatabase.viewModel.UserViewModel
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    private lateinit var _binding: FragmentUpdateBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        _binding.updateFirstNameEt.setText(args.currentUser.firstName)
        _binding.updateLastNameEt.setText(args.currentUser.lastName)
        _binding.updateAgeEt.setText(args.currentUser.age.toString())

        _binding.updateBtn.setOnClickListener {
            updateItem()
        }

        ////Delete Data
        setHasOptionsMenu(true)

        return binding.root

    }


    //Update Data
    private fun updateItem() {
        val firstName = _binding.updateFirstNameEt.text.toString()
        val lastName = _binding.updateLastNameEt.text.toString()
        val age = Integer.parseInt(_binding.updateAgeEt.text.toString())

        if (inputCheck(firstName, lastName, age.toString())) {
            //Create User Object
            val updateUser = User(args.currentUser.id, firstName, lastName, age)
            //Update data to database
            mUserViewModel.upDateUser(updateUser)
            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_SHORT).show()
            //Navigation Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()

        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


    //Delete Data
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successful Removed ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete ${args.currentUser.firstName}")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}")
        builder.create().show()
    }


}