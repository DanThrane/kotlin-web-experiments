package dk.thrane.playground.psql

import dk.thrane.playground.ObjectPool
import dk.thrane.playground.useInstance
import kotlinx.coroutines.runBlocking

typealias PostgresConnectionPool = ObjectPool<PostgresConnection>

@Suppress("FunctionName")
fun PostgresConnectionPool(parameters: PostgresConnectionParameters): PostgresConnectionPool {
    return ObjectPool(
        size = 1,
        itemGenerator = {
            PostgresConnection(parameters).apply {
                runBlocking { open() } // TODO Deal with this
            }
        },
        reset = {}
    )
}

suspend inline fun <R> PostgresConnectionPool.useTransaction(block: (PostgresConnection) -> R): R {
    return useInstance { conn ->
        conn.withTransaction {
            block(conn)
        }
    }
}

suspend inline fun <R> PostgresConnection.withTransaction(block: () -> R): R {
    var success = true
    try {
        begin()
        return block()
    } catch (ex: Throwable) {
        success = false
        throw ex
    } finally {
        if (success) commit()
        else rollback()
    }
}
