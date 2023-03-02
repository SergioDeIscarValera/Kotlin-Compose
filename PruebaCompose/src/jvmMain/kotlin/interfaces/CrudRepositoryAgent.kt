package interfaces

/**
 * @param T: Tipo de dato que se va a gestionar
 * @param ID: Tipo de dato de la primary key
 * @param AGENT: Tipo de dato del agente que realiza la acción
 */
interface CrudRepositoryAgent<T, ID, AGENT> {
    fun save(t: T, a: AGENT): T
    fun update(t: T, old: ID, a: AGENT): T?
    fun findById(id: ID): T?
    fun findByElement(t: T): T?
    fun delete(id: ID, a: AGENT): T?
    fun getAll(): List<T>
    fun saveAll(list: List<T>, a: AGENT)
}