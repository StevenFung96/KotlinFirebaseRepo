package com.example.kotlintest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class FirestoreViewModel : ViewModel() {

    var firebaseRepo = FirestoreRepository()
    var savedUsers: MutableLiveData<List<User>> = MutableLiveData()


    fun saveUserToFirebase(user: User) {
        firebaseRepo.saveUserItem(user).addOnFailureListener {
            Log.e("Test", "Failed to save user")
        }
    }

    fun getSavedUser(): LiveData<List<User>> {
        firebaseRepo.getUserItem().addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                Log.w("Test", "Listen failed.", e)
                savedUsers.value = null
                return@EventListener
            }

            var savedUserList: MutableList<User> = mutableListOf()
            for (doc in value!!) {
                var user = doc.toObject(User::class.java)
                savedUserList.add(user)
            }
            savedUsers.value = savedUserList
        })
        return savedUsers
    }

    fun deleteUser(user: User) {
        firebaseRepo.deleteUserItem(user).addOnFailureListener {
            Log.e("Test", "Failed to delete User")
        }
    }
}