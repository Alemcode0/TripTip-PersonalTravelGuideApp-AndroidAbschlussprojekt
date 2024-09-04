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
            R.drawable.triptoparis
        )
    )

    val Contact2 = Community(
        id = 2,
        name = "Till",
        number = "0153856043",
        image =
        R.drawable.triptoparis,
        imageStatus = listOf(
            R.drawable.traveltunisia1
        )
    )
}