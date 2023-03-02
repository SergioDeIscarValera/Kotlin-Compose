package interfaces

import enums.UserRol
import models.User

interface ManagementUsers: CrudRepository<User, Int> {
    fun login(username: String, password: String): User?
    fun changePassword(id: Int, oldPassword: String, newPassword: String): User?
    fun changeRole(id: Int, newRole: UserRol): User?
}