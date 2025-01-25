package com.example.testwithpoetry.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testwithpoetry.domain.models.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email: String,
    val birthday: Long
) {
    fun toDomain(): User {
        return User(
            name = name,
            email = email,
            birthday = birthday
        )
    }
}
