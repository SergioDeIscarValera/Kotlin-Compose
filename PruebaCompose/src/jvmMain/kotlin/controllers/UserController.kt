package controllers

import enums.UserRol
import exceptions.AuthUserExceptionNotAdmin
import interfaces.ManagementUsers
import interfaces.ManagementUsersAgent
import models.User

class UserController(private val repository: ManagementUsers): ManagementUsersAgent{
    override fun login(username: String, password: String): User? {
        return repository.login(username, password)
    }

    override fun changePassword(id: Int, oldPassword: String, newPassword: String, user: User): User? {
        isAdmin(user)
        return repository.changePassword(id, oldPassword, newPassword)
    }

    override fun changeRole(id: Int, newRole: UserRol, user: User): User? {
        isAdmin(user)
        return repository.changeRole(id, newRole)
    }

    override fun save(t: User, a: User): User {
        isAdmin(a)
        return repository.save(t)
    }

    override fun update(t: User, old: Int, a: User): User? {
        isAdmin(a)
        return repository.update(t, old)
    }

    override fun findById(id: Int): User? {
        return repository.findById(id)
    }

    override fun findByElement(t: User): User? {
        return repository.findByElement(t)
    }

    override fun delete(id: Int, a: User): User? {
        isAdmin(a)
        return repository.delete(id)
    }

    override fun getAll(): List<User> {
        return repository.getAll()
    }

    override fun saveAll(list: List<User>, a: User) {
        isAdmin(a)
        return repository.saveAll(list)
    }

    private fun isAdmin(user: User){
        require(user.rol == UserRol.ADMINISTRADOR){ throw AuthUserExceptionNotAdmin() }
    }
}