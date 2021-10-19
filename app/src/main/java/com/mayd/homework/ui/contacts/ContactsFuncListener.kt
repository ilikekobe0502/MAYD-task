package com.mayd.homework.ui.contacts

import com.mayd.homework.model.api.model.response.Contact

class ContactsFuncListener(
        /**
         * Favorite button click event
         */
        val onFavoriteClick: (Contact) -> Unit = { },
        /**
         * Item click event
         */
        val onItemClick: (Contact) -> Unit = { },
)