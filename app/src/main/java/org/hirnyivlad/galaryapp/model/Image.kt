package org.hirnyivlad.galaryapp.model

class Image(
    private var urls: Url,
    private var user: User
) {
    fun getUrls(): Url {
        return urls
    }

    fun getUser(): User {
        return user
    }
}