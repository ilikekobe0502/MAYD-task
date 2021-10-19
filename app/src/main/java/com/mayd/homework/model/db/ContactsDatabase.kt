package com.mayd.homework.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mayd.homework.model.api.model.response.Contact
import com.mayd.homework.model.db.dao.ContactsDao

@Database(entities = [Contact::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun ContactsDao(): ContactsDao
}