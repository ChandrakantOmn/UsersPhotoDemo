package com.demo.userphotoalbum.data.local.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

/**
 * Created by Chandra Kant on 4/1/20.
 */
@Entity
class User(
    @TypeConverters(AddressConverter::class)
    var address: Address?,
    @TypeConverters(CompanyConverter::class)
    var company: Company?,
    var email: String?, // Rey.Padberg@karina.biz
    @PrimaryKey
    var id: Int?, // 10
    var name: String?, // Clementina DuBuque
    var phone: String?, // 024-648-3804
    var username: String?, // Moriah.Stanton
    var website: String? // ambrose.net
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Address::class.java.classLoader),
        parcel.readParcelable(Company::class.java.classLoader),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    data class Address(
        var city: String?, // Lebsackbury
        @TypeConverters(GeoConverter::class)
        var geo: Geo?,
        var street: String?, // Kattie Turnpike
        var suite: String?, // Suite 198
        var zipcode: String? // 31428-2261
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readParcelable(Geo::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        ) {
        }

        data class Geo(
            var lat: String?, // -38.2386
            var lng: String? // 57.2232
        ) : Parcelable {
            constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString()
            ) {
            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(lat)
                parcel.writeString(lng)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<Geo> {
                override fun createFromParcel(parcel: Parcel): Geo {
                    return Geo(parcel)
                }

                override fun newArray(size: Int): Array<Geo?> {
                    return arrayOfNulls(size)
                }
            }
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(city)
            parcel.writeParcelable(geo, flags)
            parcel.writeString(street)
            parcel.writeString(suite)
            parcel.writeString(zipcode)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Address> {
            override fun createFromParcel(parcel: Parcel): Address {
                return Address(parcel)
            }

            override fun newArray(size: Int): Array<Address?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Company(
        var bs: String?, // target end-to-end models
        var catchPhrase: String?, // Centralized empowering task-force
        var name: String? // Hoeger LLC
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(bs)
            parcel.writeString(catchPhrase)
            parcel.writeString(name)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Company> {
            override fun createFromParcel(parcel: Parcel): Company {
                return Company(parcel)
            }

            override fun newArray(size: Int): Array<Company?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(address, flags)
        parcel.writeParcelable(company, flags)
        parcel.writeString(email)
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(username)
        parcel.writeString(website)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
