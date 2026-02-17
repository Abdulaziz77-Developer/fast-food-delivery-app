package com.example.app.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.app.R
import kotlin.math.absoluteValue

@Composable
fun BannerSection() {
    val banners = listOf(R.drawable.banner1, R.drawable.banner1, R.drawable.banner1)

    // Создаем состояние пагинатора
    val pagerState = rememberPagerState(pageCount = { banners.size })

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            // contentPadding — это секрет того самого "выглядывания" соседних фото
            contentPadding = PaddingValues(horizontal = 30.dp),
            // Расстояние между карточками
            pageSpacing = 12.dp,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            Image(
                painter = painterResource(id = banners[page]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth() // Теперь ширина адаптивная, подстраивается под экран
                    .height(160.dp)
                    .clip(RoundedCornerShape(20.dp))
                    // Эффект легкого уменьшения боковых карточек (для красоты)
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                                ).absoluteValue
                        alpha = lerp(
                            start = 0.8f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleY = lerp(
                            start = 0.9f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    },
                contentScale = ContentScale.Crop
            )
        }
    }
}