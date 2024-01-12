package com.abcoding.connect.presentation.activitysceen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.sql.Timestamp
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
) : ViewModel() {
    fun timestampToFormattedString(timestamp: Long, pattern: String) {

    }
}