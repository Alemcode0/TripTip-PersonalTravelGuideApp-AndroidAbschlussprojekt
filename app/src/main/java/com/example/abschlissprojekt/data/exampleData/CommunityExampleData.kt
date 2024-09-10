package com.example.abschlissprojekt.data.exampleData

import com.example.abschlissprojekt.R
import com.example.abschlissprojekt.data.models.Community

object CommunityExampleData {
    val Contact1 = Community(
        id = 1,
        name = "Anny",
        number = "01765893230",
        image =
            R.drawable.portrait2,
        imageStatus = listOf(
            R.drawable.triptoparis),
        statusDescription = "It’s true, you never forget your first love, " +
                " and for me, that will always be Paris.",
        cityName = "Paris, France",
        latitude = (48.870502),
        longitude = (2.304897)
    )

    val Contact2 = Community(
        id = 2,
        name = "Till",
        number = "0153856043",
        image =
        R.drawable.portrait3a,
        imageStatus = listOf(
            R.drawable.traveltunisia1),
        statusDescription = "Tunisia is one of the best countries to " +
                "explore the desert. I love the camel trip.",
        cityName = "Tunisia, Africa",
        latitude =(33.8869),
        longitude = (9.5375)
    )

    val Contact3 = Community(
        id = 3,
        name = "Juli",
        number = "016853298",
        image =
        R.drawable.portrait8a,
        imageStatus = listOf(
            R.drawable.travelcanada),
        statusDescription = "Simply Colorful.",
        cityName = "North America",
        latitude =(53.135509),
        longitude = (-57.660435)
    )

    val Contact4 = Community(
        id = 4,
        name = "Dani",
        number = "0174520175",
        image =
        R.drawable.portrait5,
        imageStatus = listOf(
            R.drawable.travelengland),
        statusDescription = "A short trip but i enjoy it alot.",
        cityName = "Hampshire, England",
        latitude =(51.063202),
        longitude = (-1.308000)
    )

    val Contact5 = Community(
        id = 5,
        name = "Sonia",
        number = "01609845632",
        image =
        R.drawable.portrait6,
        imageStatus = listOf(
            R.drawable.travelspain5),
        statusDescription = "A once-in-a-lifetime experience, I love spain.",
        cityName = "Barcelona, Spain",
        latitude =(41.414494),
        longitude = (2.152695)
    )

    val Contact6 = Community(
        id = 6,
        name = "John",
        number = "0176320418",
        image =
        R.drawable.portrait7,
        imageStatus = listOf(
            R.drawable.travelcroatia4),
        statusDescription = "Vacation vibes @ Hvar.",
        cityName = "Hvar, Croatia",
        latitude =(43.172989),
        longitude = (16.440144)
    )

    val Contact7 = Community(
        id = 7,
        name = "Queen",
        number = "0159856432",
        image =
        R.drawable.portrait4,
        imageStatus = listOf(
            R.drawable.travelborabora2),
        statusDescription = "Time to relax in my private bungalow.",
        cityName = "Bora Bora, French Polynesia",
        latitude =(-16.499701),
        longitude = (-151.770538)
    )
}