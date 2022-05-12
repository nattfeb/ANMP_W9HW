package com.nepnep.todo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name="title") //dipakai kalau nama di class dan database beda
    var title:String,
    @ColumnInfo(name="notes")
    var notes:String,
    @ColumnInfo(name="priority")
    var priority:Int,
    @ColumnInfo(name="isdone")
    var isdone: Int
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}
