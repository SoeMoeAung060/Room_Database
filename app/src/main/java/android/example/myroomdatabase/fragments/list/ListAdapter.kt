package android.example.myroomdatabase.fragments.list

import android.example.myroomdatabase.model.User
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.example.myroomdatabase.R
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.navigation.findNavController


class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val id : TextView = itemView.findViewById(R.id.id_txt)
        val firstName : TextView = itemView.findViewById(R.id.firstName_txt)
        val lastName : TextView = itemView.findViewById(R.id.lastName_txt)
        val age : TextView = itemView.findViewById(R.id.age_txt)
        val rowLayout : View = itemView.findViewById(R.id.rowLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.id.text = currentItem.id.toString()
        holder.firstName.text = currentItem.firstName.toString()
        holder.lastName.text = currentItem.lastName.toString()
        holder.age.text = currentItem.age.toString()
        holder.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

        }


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }



}