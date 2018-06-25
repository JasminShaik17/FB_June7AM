package cubex.mahesh.fb_june7am

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("/users/"+
                FirebaseAuth.getInstance().uid)
        myRef!!.addValueEventListener(
                object : ValueEventListener {

                    override fun onDataChange(p0: DataSnapshot?) {
                    dash_name.text = p0!!.child("name").getValue().toString()
                    dash_mno.text = p0!!.child("mno").getValue().toString()
                    dash_gender.text = p0!!.child("gender").getValue().toString()
                     dash_dob.text = p0!!.child("dob").getValue().toString()
                    var url = p0!!.child("profile_pic").getValue().toString()

                    Glide.with(this@DashboardActivity).
                            load(url).into(dashboard_pic_iview)

                    }

                    override fun onCancelled(p0: DatabaseError?) {
                        Toast.makeText(this@DashboardActivity,
                                "Failed to Load the data...",
                                Toast.LENGTH_LONG).show()
                    }

                }
        )
    }
}
