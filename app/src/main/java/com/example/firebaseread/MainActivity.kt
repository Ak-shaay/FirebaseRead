package com.example.firebaseread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseread.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRead.setOnClickListener {
            val userName=binding.etusername.text.toString()
            if (userName.isNotEmpty()){
                readData(userName)
            }
            else{
                Toast.makeText(this,"Please Enter Username",Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun readData(userName:String) {
        database=FirebaseDatabase.getInstance().getReference("Users")
        database.child(userName).get().addOnSuccessListener {
           if (it.exists()){
               val firstName=it.child("firstName").value
               val lastName=it.child("lastName").value
               val age=it.child("age").value

               Toast.makeText(this,"Successful !!!",Toast.LENGTH_LONG).show()

               binding.etusername.text.clear()

               binding.tvFirstName.text=firstName.toString()
               binding.tvLastName.text=lastName.toString()
               binding.tvAge.text=age.toString()


           }
            else{
               Toast.makeText(this,"User Does not exist !!!",Toast.LENGTH_LONG).show()

           }
        }.addOnFailureListener {
            Toast.makeText(this,"Failed!!!",Toast.LENGTH_LONG).show()
        }
    }
}