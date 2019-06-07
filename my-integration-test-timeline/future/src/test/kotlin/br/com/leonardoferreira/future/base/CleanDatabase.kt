package br.com.leonardoferreira.future.base

import org.springframework.stereotype.Component
import java.sql.Connection
import java.sql.Statement
import javax.sql.DataSource

@Component
class CleanDatabase(val dataSource: DataSource) {

    fun clean() {
        dataSource.connection.use { connection ->
            connection.createStatement().use { stmt ->
                stmt.executeUpdate("SET REFERENTIAL_INTEGRITY = 0")
                truncateTables(connection, stmt)
                restartSequences(connection, stmt)
                stmt.executeUpdate("SET REFERENTIAL_INTEGRITY = 1")
            }
        }
    }

    private fun truncateTables(connection: Connection, stmt: Statement) {
        connection.createStatement().executeQuery("SHOW TABLES").use { rs ->
            while (rs.next()) {
                stmt.executeUpdate("TRUNCATE TABLE " + rs.getString("TABLE_NAME"))
            }
        }
    }

    private fun restartSequences(connection: Connection, stmt: Statement) {
        connection.createStatement().executeQuery("SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SEQUENCES").use { rs ->
            while (rs.next()) {
                stmt.executeUpdate("ALTER SEQUENCE " + rs.getString("SEQUENCE_NAME") + " RESTART WITH 1")
            }
        }
    }

}
