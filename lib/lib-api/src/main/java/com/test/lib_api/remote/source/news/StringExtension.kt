package com.test.lib_api.remote.source.news

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

internal val String.date
    get(): DateTime {
        val format = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")
        return DateTime.parse(this, format)
    }