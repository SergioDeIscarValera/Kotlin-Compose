package interfaces

import enums.UserRol
import models.User

interface ManagementUsersAgent: CrudRepositoryAgent<User, Int, User> {
    fun login(username: String, password: String): User?
    fun changePassword(id: Int, oldPassword: String, newPassword: String, user: User): User?
    fun changeRole(id: Int, newRole: UserRol, user: User): User?
}