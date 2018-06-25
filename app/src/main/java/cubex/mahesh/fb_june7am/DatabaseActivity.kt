package cubex.mahesh.fb_june7am

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_database.*


class DatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

       db_register.setOnClickListener({
           val database = FirebaseDatabase.getInstance()
           val myRef = database.getReference("/users/"+
                   FirebaseAuth.getInstance().uid)
           myRef.child("name").setValue(db_et1.text.toString())
           myRef.child("mno").setValue(db_et2.text.toString())
           myRef.child("gender").setValue(db_et3.text.toString())
           myRef.child("dob").setValue(db_et4.text.toString())

           startActivity(Intent(this@DatabaseActivity,
                   ProfilePicActivity::class.java))

       })


    }
}
