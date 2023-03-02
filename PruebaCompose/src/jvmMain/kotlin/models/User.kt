package models

import enums.UserRol

class User(var userName: String, var password: String, var rol: UserRol) {
    val id = count++
    companion object {
        private var count = 0
    }
    override fun toString(): String {
        return "User(id=$id, userName='$userName', password='$password', rol=$rol)"
    }

    override fun hashCode(): Int {
        return id.hashCode() + userName.hashCode() + password.hashCode() + rol.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if(other !is User) return false
        return userName == other.userName && password == other.password
    }
}