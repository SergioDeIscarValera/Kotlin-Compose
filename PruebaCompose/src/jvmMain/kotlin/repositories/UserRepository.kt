package repositories

import enums.UserRol
import interfaces.ManagementUsers
import models.User

class UserRepository: ManagementUsers {
    private val users = mutableMapOf<Int, User>()

    init {
        save(User("root", "SOR_2023", UserRol.ADMINISTRADOR))
    }

    override fun login(username: String, password: String): User? {
        return users.values.find { it.userName == username && it.password == password }
    }

    override fun changePassword(id: Int, oldPassword: String, newPassword: String): User? {
        return users[id]?.let {
            if(it.password == oldPassword) {
                it.password = newPassword
                it
            } else null
        }
    }

    override fun changeRole(id: Int, newRole: UserRol): User? {
        return users[id]?.let {
            it.rol = newRole
            it
        }
    }

    override fun save(t: User): User{
        users[t.id] = t
        return t
    }

    override fun update(t: User, id: Int): User? {
        if (users[id] == null) return null
        users[id]?.let {
            it.userName = t.userName
            it.password = t.password
            it.rol = t.rol
            return it
        }
        return null
    }

    override fun findById(id: Int): User? {
        return users[id]
    }

    override fun findByElement(t: User): User? {
        return users.values.find { it == t }
    }

    override fun delete(id: Int): User? {
        return users.remove(id)
    }

    override fun getAll(): List<User> {
        return users.values.toList()
    }

    override fun saveAll(list: List<User>) {
        list.forEach { save(it) }
    }
}