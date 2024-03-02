package com.ved.Vedu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ved.mysafety.R

class embassy : AppCompatActivity() {
//    private val binding:ActivityEmbassyBinding by lazy {
//        ActivityEmbassyBinding.inflate(layoutInflater)
//    }
//    private lateinit var databaseReference: DatabaseReference
//    private lateinit var auth: FirebaseAuth
//    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_embassy)
//
////        recyclerView=binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        databaseReference =FirebaseDatabase.getInstance().reference
//        auth= FirebaseAuth.getInstance()
//
//        val currentUser=auth.currentUser
//        currentUser?.let { user ->
//            val noteReference=databaseReference.child("Embassy List").child(user.uid).child("note")
//            noteReference.addValueEventListener(object:ValueEventListener{
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    val noteList= mutableListOf<Nodeitems>()
//                    for (noteSnapshot:DataSnapshot in snapshot.children){
//                        val note:Any?=noteSnapshot.getValue(Nodeitems::class.java)
//                        note?.let {
//                            noteList.add(it as Nodeitems)
//                        }
//                    }
//                    val adapter = NodeAdapter(noteList)
//                    recyclerView.adapter = adapter
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//
//            })
//        }
     }
}