package android.example.myroomdatabase

import android.example.myroomdatabase.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ActionBar Fragment Name
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNatHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

    }

    //Back Button for Action Bar
    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.myNatHostFragment).navigateUp() || super.onSupportNavigateUp()
    }
}