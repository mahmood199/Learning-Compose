package com.example.learningcompose.canvas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.learningcompose.BottomNavScreen
import com.example.learningcompose.ui.theme.SilverChalice

@Composable
fun BottomBarItem(
    screen: BottomNavScreen,
    isSelected: Boolean,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .clickable(onClick = {
                onClick(screen.position)
            })
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(screen.drawable),
            contentDescription = null,
            tint = if (isSelected) Color.Black else SilverChalice,
            modifier = Modifier.size(if (isSelected) 40.dp else 32.dp)
        )

        Text(
            text = screen.text,
            style = MaterialTheme.typography.bodySmall,
            color = if (isSelected) Color.Black else SilverChalice,
        )
    }
}
