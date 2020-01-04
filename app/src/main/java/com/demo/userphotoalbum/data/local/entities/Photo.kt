package com.demo.userphotoalbum.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Chandra Kant on 4/1/20.
 */
@Entity
data class Photo(
    var albumId: Int?, // 100
    @PrimaryKey
    var id: Int?, // 5000
    var thumbnailUrl: String?, // https://via.placeholder.com/150/6dd9cb
    var title: String?, // error quasi sunt cupiditate voluptate ea odit beatae
    var url: String? // https://via.placeholder.com/600/6dd9cb
)