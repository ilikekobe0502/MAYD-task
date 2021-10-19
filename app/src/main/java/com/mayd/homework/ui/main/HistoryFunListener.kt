package com.mayd.homework.ui.main

import com.mayd.homework.model.api.model.response.Shorten

class HistoryFunListener(
        /**
         * Item delete click event
         */
        val onDeleteClick: (Shorten) -> Unit = { },
        /**
         * Item copy click event
         */
        val onCopyClick: (Shorten) -> Unit = { },
)