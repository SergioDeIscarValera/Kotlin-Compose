package interfaces

interface CrudRepository<T, ID> {
    fun save(t: T): T
    fun update(t: T, old: ID): T?
    fun findById(id: ID): T?
    fun findByElement(t: T): T?
    fun delete(id: ID): T?
    fun getAll(): List<T>
    fun saveAll(list: List<T>)
}