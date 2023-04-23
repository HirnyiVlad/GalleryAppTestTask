package org.hirnyivlad.galaryapp.model

class User(
    private var username: String
) {
    fun getUserName(): String {
        return username
    }
}