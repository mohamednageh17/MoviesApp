package com.example.moviesapp.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// TODO: use database converter to save custom objects in room
class MovieResponse():Parcelable {
     @SerializedName("page")
     var page: Long? = null
     @SerializedName("results")
     var results: List<Movie>? = null
     @SerializedName("total_pages")
     var totalPages: Long? = null
     @SerializedName("total_results")
     var totalResults: Long? = null

     constructor(parcel: Parcel) : this() {
         page = parcel.readValue(Long::class.java.classLoader) as? Long
         results = parcel.createTypedArrayList(Movie)
         totalPages = parcel.readValue(Long::class.java.classLoader) as? Long
         totalResults = parcel.readValue(Long::class.java.classLoader) as? Long
     }

     override fun writeToParcel(parcel: Parcel, flags: Int) {
         parcel.writeValue(page)
         parcel.writeTypedList(results)
         parcel.writeValue(totalPages)
         parcel.writeValue(totalResults)
     }

     override fun describeContents(): Int {
         return 0
     }

     companion object CREATOR : Parcelable.Creator<MovieResponse> {
         override fun createFromParcel(parcel: Parcel): MovieResponse {
             return MovieResponse(parcel)
         }

         override fun newArray(size: Int): Array<MovieResponse?> {
             return arrayOfNulls(size)
         }
     }
 }

@Entity(tableName = "fav_movie")
class Movie() : Parcelable {
    @PrimaryKey(autoGenerate = false) @SerializedName("id")
    var id: Long? = null
    @ColumnInfo(name = "name") @SerializedName("name")
    var name: String? = null
    @ColumnInfo(name = "backdrop_path") @SerializedName("backdrop_path")
    var backdropPath: String? = null
    @ColumnInfo(name = "first_air_date") @SerializedName("first_air_date")
    var firstAirDate: String? = null
    @ColumnInfo(name = "original_language") @SerializedName("original_language")
    var originalLanguage: String? = null
    @ColumnInfo(name = "original_name")  @SerializedName("original_name")
    var originalName: String? = null
    @ColumnInfo(name = "overview")   @SerializedName("overview")
    var overview: String? = null
    @ColumnInfo(name = "popularity")  @SerializedName("popularity")
    var popularity: Double? = null
    @ColumnInfo(name = "poster_path")  @SerializedName("poster_path")
    var posterPath: String? = null
    @ColumnInfo(name = "vote_average")  @SerializedName("vote_average")
    var voteAverage: Double? = null
    @ColumnInfo(name = "vote_count")  @SerializedName("vote_count")
    var voteCount: Long? = null
    @Ignore
    @SerializedName("origin_country")
    var originCountry: List<String>? = null
    @Ignore
    @SerializedName("genre_ids")
    var genreIDS: List<Long>? = null
    @Ignore
    var trailer: Trailer? = null
    @Ignore
    var isFavourite:Boolean =false

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Long::class.java.classLoader) as? Long
        name = parcel.readString()
        backdropPath = parcel.readString()
        firstAirDate = parcel.readString()
        originalLanguage = parcel.readString()
        originalName = parcel.readString()
        overview = parcel.readString()
        popularity = parcel.readValue(Double::class.java.classLoader) as? Double
        posterPath = parcel.readString()
        voteAverage = parcel.readValue(Double::class.java.classLoader) as? Double
        voteCount = parcel.readValue(Long::class.java.classLoader) as? Long
        originCountry = parcel.createStringArrayList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(backdropPath)
        parcel.writeString(firstAirDate)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalName)
        parcel.writeString(overview)
        parcel.writeValue(popularity)
        parcel.writeString(posterPath)
        parcel.writeValue(voteAverage)
        parcel.writeValue(voteCount)
        parcel.writeStringList(originCountry)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}

class TrailerResponse():Parcelable {
    @SerializedName("id")
    val id: Long? = null
    @SerializedName("results")
    val results: List<Trailer>? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TrailerResponse> {
        override fun createFromParcel(parcel: Parcel): TrailerResponse {
            return TrailerResponse(parcel)
        }

        override fun newArray(size: Int): Array<TrailerResponse?> {
            return arrayOfNulls(size)
        }
    }
}
class Trailer():Parcelable {
    @SerializedName("iso639_1")
    val iso639_1: String? = null
    @SerializedName("iso3166_1")
    val iso3166_1: String? = null
    @SerializedName("name")
    val name: String? = null
    @SerializedName("key")
    val key: String? = null
    @SerializedName("site")
    val site: String? = null
    @SerializedName("size")
    val size: Long? = null
    @SerializedName("type")
    val type: String? = null
    @SerializedName("official")
    val official: Boolean? = null
    @SerializedName("publishedAt")
    val publishedAt: String? = null
    @SerializedName("id")
    val id: String? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trailer> {
        override fun createFromParcel(parcel: Parcel): Trailer {
            return Trailer(parcel)
        }

        override fun newArray(size: Int): Array<Trailer?> {
            return arrayOfNulls(size)
        }
    }

}

