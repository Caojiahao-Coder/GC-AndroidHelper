package com.example.gc_androidhelper

data class UserInfo(
    var Id: Int,
    var Gender: String,
    var UserName: String
) {
    override fun toString(): String {
        return "$Id-$Gender-$UserName"
    }
}