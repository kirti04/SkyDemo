package com.test.skydemo.ui.extension

import org.joda.time.DateTime

val DateTime.formattedString: String
    get() = toString("dd/MM/yyyy")
