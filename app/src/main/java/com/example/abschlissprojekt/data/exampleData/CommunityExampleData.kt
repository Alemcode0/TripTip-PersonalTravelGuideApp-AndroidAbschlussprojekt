package com.example.abschlissprojekt.data.exampleData

import com.example.abschlissprojekt.R
import com.example.abschlissprojekt.data.models.Community

object CommunityExampleData {
    val Contact1 = Community(
        id = 1,
        name = "Anny",
        number = "01765893230",
        image =
            R.drawable.triptoparis,
        imageStatus = listOf(
            R.drawable.triptoparis),
        statusDescription = "It’s true, you never forget your first love, \" +\n" +
                "            \"and for me, that will always be Paris."
    )

    val Contact2 = Community(
        id = 2,
        name = "Till",
        number = "0153856043",
        image =
        R.drawable.triptoparis,
        imageStatus = listOf(
            R.drawable.traveltunisia1),
        statusDescription = "Tunisia is one of the best countries to " +
                "explore the desert. I love the camel trip."
    )
}