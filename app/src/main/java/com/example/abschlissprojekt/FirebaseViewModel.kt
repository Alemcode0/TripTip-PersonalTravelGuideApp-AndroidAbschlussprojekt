package com.example.abschlissprojekt

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.abschlissprojekt.data.models.Profile
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class FirebaseViewModel: ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseStrorage = FirebaseStorage.getInstance()
    private val firebaseFireStore = FirebaseFirestore.getInstance()
    private lateinit var firebaseAnalytics : FirebaseAnalytics

    lateinit var profileRef: DocumentReference

    private var _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser


    init {
        if(firebaseAuth.currentUser != null){
            setUpUserEnv()
        }
        firebaseAnalytics = Firebase.analytics
    }

    //Diese Funktion nutzt Firebase Auth um einen Nutzer ein zuloggen und logged ein Analytics Event (Login)
    fun login(email : String, pass: String){
        firebaseAuth.signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    Log.d("MainViewModel","Login done")
                    setUpUserEnv()
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN) {
                        param(FirebaseAnalytics.Param.ITEM_ID, _currentUser.value?.email!!)
                        param(FirebaseAnalytics.Param.ITEM_NAME, "Email")
                    }
                }else{
                    Log.d("MainViewModel","Login failed")
                }
            }
    }

    fun register(email : String, pass: String){
        firebaseAuth.createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Log.d("MainViewModel","Register done")
                    setUpUserEnv()
                    setupNewProfile()
                }else{
                    Log.d("MainViewModel","Register failed")
                }
            }
    }

    private fun setUpUserEnv(){
        _currentUser.value = firebaseAuth.currentUser
        profileRef = firebaseFireStore.collection("profile").document(firebaseAuth.currentUser?.uid!!)
    }

    //Initialisiert ein neues leeres Profile Doc im Firestore
    private fun setupNewProfile(){
        profileRef.set(Profile())
    }

    fun logout(){
        firebaseAuth.signOut()
        _currentUser.value = null

    }
}