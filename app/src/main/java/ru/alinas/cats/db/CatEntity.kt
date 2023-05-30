package ru.alinas.cats.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "cats", indices = [Index(value = ["img"], unique = true)])
data class CatEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val img: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CatEntity

        if (id != other.id) return false
        if (!img.contentEquals(other.img)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + img.contentHashCode()
        return result
    }
}