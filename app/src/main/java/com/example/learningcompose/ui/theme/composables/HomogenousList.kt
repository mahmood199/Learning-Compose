package com.example.learningcompose.ui.theme.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningcompose.R

@Composable
fun HomogenousList() {
    val data = remember {
        Details.EmployDetailsList
    }

    data.addAll(Details.EmployDetailsList)

    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        items(data) {
            EmployeeCard(data = it)
        }
    }
}

@Composable
fun EmployeeCard(data: EmployDetails) {
    Card(
        modifier = Modifier.padding(all = 8.dp),
        elevation = 4.dp,
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {

        Row(
            modifier = Modifier.padding(20.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                Arrangement.Center
            ) {
                Text(
                    text = data.title,
                    fontSize = 24.sp,
                    style = TextStyle(color = Color.Blue),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Age: ${data.age}",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )
                Text(
                    text = "Description ${data.description}",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )
            }
            Image(
                painter = painterResource(id = data.ImageId),
                contentDescription = "Profile Image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp)
                    .clip(CircleShape)
            )
        }

    }
}


data class EmployDetails(
    val id: Int,
    val title: String,
    val gender: String,
    val age: Int,
    val description: String,
    val ImageId: Int = 0,
)

object Details {

    val EmployDetailsList = mutableListOf(
        EmployDetails(
            id = 1,
            title = "Rohan",
            gender = "Male",
            age = 24,
            description = " Don't judge each day by the harvest you reap but by the seeds that you plant.” - ...",
            ImageId = R.drawable.ic_launcher_foreground
        ),
        EmployDetails(
            id = 2,
            title = "Roy",
            gender = "male",
            age = 25,
            description = " Don't judge each day by the harvest you reap but by the seeds that you plant.” - ...",
            ImageId = R.drawable.ic_launcher_background
        ),
        EmployDetails(
            id = 3,
            title = "Vishal",
            gender = "Male",
            age = 29,
            description = " Don't judge each day by the harvest you reap but by the seeds that you plant.” - ...",
            ImageId = R.drawable.ic_launcher_foreground
        ),
        EmployDetails(
            id = 4,
            title = "Nikhil",
            gender = "Male",
            age = 27,
            description = " Don't judge each day by the harvest you reap but by the seeds that you plant.” - ...",
            ImageId = R.drawable.ic_launcher_background
        )
    )
}

@Preview(showSystemUi = false)
@Composable
fun HomogeneousListPreview() {
    HomogenousList()
}