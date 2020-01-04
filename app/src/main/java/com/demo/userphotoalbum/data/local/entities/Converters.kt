package com.demo.userphotoalbum.data.local.entities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Chandra Kant on 4/1/20.
 */

object AddressConverter {
    @TypeConverter
    @JvmStatic
    fun fromString(value: String): User.Address {
        val type = object : TypeToken<User.Address>() {
        }.type
        return Gson().fromJson(value, type) as User.Address
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(address: User.Address?): String {
        return Gson().toJson(address)
    }
}


object CompanyConverter {
    @TypeConverter
    @JvmStatic
    fun fromString(value: String): User.Company {
        val type = object : TypeToken<User.Company>() {
        }.type
        return Gson().fromJson(value, type) as User.Company
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(company: User.Company?): String {
        return Gson().toJson(company)
    }
}


object GeoConverter {
    @TypeConverter
    @JvmStatic
    fun fromString(value: String): User.Address.Geo {
        val type = object : TypeToken<User.Address.Geo>() {
        }.type
        return Gson().fromJson(value, type) as User.Address.Geo
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(geo: User.Address.Geo?): String {
        return Gson().toJson(geo)
    }
}