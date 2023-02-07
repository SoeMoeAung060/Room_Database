package android.example.myroomdatabase.fragments.list

import android.app.AlertDialog
import android.example.myroomdatabase.R
import android.example.myroomdatabase.viewModel.UserViewModel
import android.example.myroomdatabase.databinding.FragmentListBinding
import android.example.myroomdatabase.fragments.update.UpdateFragmentArgs
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.zip.Inflater

class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!


    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListBinding.inflate(inflater, container, false)

        //Recycleview
        val adapter = ListAdapter()
        val recyclerView = _binding!!.recycleView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        _binding!!.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        //Update Data
        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



    //Delete Data
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mUserViewModel.deleteAllUser()
            Toast.makeText(requireContext(), "Successful removed everything", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_ ->
        }
        builder.setTitle("Delete Everything")
        builder.setMessage("Are you sure, you want to delete Everything ")
        builder.create().show()
    }
}




