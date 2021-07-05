package com.task.nycnewsarticles.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Result(
    val abstract: String,
    val adx_keywords: String,
    val asset_id: Long,
    val byline: String,
    val column: String,
    /*val des_facet: List<String>,
    val geo_facet: String,*/
    val id: Long,
    val media: List<Media>,
    /*val org_facet: List<String>,
    val per_facet: String,*/
    val published_date: String,
    val section: String,
    val source: String,
    val title: String,
    val type: String,
    val uri: String,
    val url: String,
    val views: Int
)
