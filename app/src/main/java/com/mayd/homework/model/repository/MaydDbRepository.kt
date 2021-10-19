package com.mayd.homework.model.repository

import com.mayd.homework.model.api.model.response.Contact
import com.mayd.homework.model.db.dao.ContactsDao
import javax.inject.Inject

class MaydDbRepository @Inject constructor(private val contactsDao: ContactsDao) {
    fun fetchAllContacts() = contactsDao.getAll()
    fun fetchStarredContacts() = contactsDao.getStarredContacts()
    fun insertContactsData(contacts: ArrayList<Contact>) = contactsDao.insertAll(contacts)
    fun updateContactsData(contact: Contact) = contactsDao.updateFavorite(contact)
}
