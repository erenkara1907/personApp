package com.erenkara.personsapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "person")
data class PersonDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") @NotNull var id: Int,
    @ColumnInfo(name = "name") @NotNull var name: String,
    @ColumnInfo(name = "phone") @NotNull var phone: String
) {
}