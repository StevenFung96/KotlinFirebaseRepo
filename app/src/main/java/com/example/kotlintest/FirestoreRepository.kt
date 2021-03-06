package com.example.kotlintest

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreRepository {

    val firestoreDB = FirebaseFirestore.getInstance()


    fun saveUserItem(user: User): Task<Void> {

        var documentRef = firestoreDB.collection("users").document(user.name!!)
        return documentRef.set(user)
    }

    fun getUserItem(): CollectionReference {

        return firestoreDB.collection("users")
    }

    fun deleteUserItem(user: User): Task<Void> {

        var documentRef = firestoreDB.collection("users").document(user.name!!)
        return documentRef.delete()
    }

    //testing11
}