package exceptions

sealed class AuthExceptions(message: String): RuntimeException(message)
class AuthUserExceptionNotClient: AuthExceptions("Error -> Usuario no es cliente")
class AuthUserExceptionNotAdmin: AuthExceptions("Error -> Usuario no es administrador")
class AuthUserExceptionNotUser: AuthExceptions("Error -> Usuario no es usuario")