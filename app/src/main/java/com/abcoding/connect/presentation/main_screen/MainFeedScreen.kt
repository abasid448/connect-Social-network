package com.abcoding.connect.presentation.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material.icons.automirrored.outlined.Comment
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abcoding.connect.R
import com.abcoding.connect.domain.models.Post
import com.abcoding.connect.presentation.util.Screen

private val AccentColor = Color(0xFFFF5E62)

private data class FeedCard(
    val post: Post,
    val imageRes: Int,
    val profileRes: Int,
    val timeAgo: String
)

private data class StoryData(
    val name: String,
    val imageRes: Int,
    val hasUnseen: Boolean
)

private val storyList = listOf(
    StoryData("Your Story", com.abcoding.connect.R.drawable.basid, false),
    StoryData("abdul_baz", com.abcoding.connect.R.drawable.copy3, true),
    StoryData("ui_design", com.abcoding.connect.R.drawable.crop2, true),
    StoryData("dev.life", com.abcoding.connect.R.drawable.copy4, false),
    StoryData("kotlin", com.abcoding.connect.R.drawable.basid, true),


)

private val cardList = listOf(
    FeedCard(Post("abdul_baz", "", "", "Just shipped something massive 🚀 The grind never stops. Building in public, learning every single day — drop a ❤️ if you vibe!", 142, 38), com.abcoding.connect.R.drawable.basid, com.abcoding.connect.R.drawable.basid, "2h ago"),
    FeedCard(Post("connect.dev", "", "", "Design is how it works, not just how it looks. Minimal. Clean. Editorial. ✦", 287, 61), com.abcoding.connect.R.drawable.copy3, com.abcoding.connect.R.drawable.basid, "4h ago"),
    FeedCard(Post("ux.vibes", "", "", "Dark mode supremacy. Keeping it edge-to-edge. 🌑", 95, 44), com.abcoding.connect.R.drawable.crop2, com.abcoding.connect.R.drawable.basid, "6h ago")
)

@Composable
fun MainFeedScreen(navController: NavController) {
    val liked = remember { mutableStateListOf<Int>() }
    val saved = remember { mutableStateListOf<Int>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Sticky Header (Includes status bar space and top bar)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Connect",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-1).sp
                )
                Icon(Icons.Outlined.FavoriteBorder, null, tint = MaterialTheme.colorScheme.onBackground, modifier = Modifier.size(26.dp))
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 104.dp)
        ) {
            // Circular Story Rings
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(storyList) { story ->
                    StoryCircle(story)
                }
            }
            Divider(color = MaterialTheme.colorScheme.surfaceVariant, thickness = 1.dp)
        }

        // Standard Edge-to-Edge Feed
        items(cardList.size) { index ->
            val card = cardList[index]
            val isLiked = index in liked
            val isSaved = index in saved

            EditorialPostCard(
                card = card,
                isLiked = isLiked,
                isSaved = isSaved,
                onLike = { if (isLiked) liked.remove(index) else liked.add(index) },
                onSave = { if (isSaved) saved.remove(index) else saved.add(index) },
                onClick = { navController.navigate(Screen.PostDetailScreen.route) }
            )
        }
        }
    }
}

@Composable
private fun StoryCircle(story: StoryData) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val ringBrush = if (story.hasUnseen) {
            Brush.linearGradient(listOf(Color(0xFFFEDA75), Color(0xFFFA7E1E), Color(0xFFD62976), Color(0xFF962FBF), Color(0xFF4F5BD5)))
        } else {
            Brush.linearGradient(listOf(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.surfaceVariant))
        }

        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .border(2.5.dp, ringBrush, CircleShape)
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(story.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = story.name,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 11.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(74.dp)
        )
    }
}

@Composable
private fun EditorialPostCard(
    card: FeedCard,
    isLiked: Boolean,
    isSaved: Boolean,
    onLike: () -> Unit,
    onSave: () -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Post Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(card.profileRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text(card.post.username, color = MaterialTheme.colorScheme.onBackground, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
            Icon(Icons.Default.MoreHoriz, null, tint = MaterialTheme.colorScheme.onBackground)
        }

        // Edge-to-Edge Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clickable { onClick() }
        ) {
            Image(
                painter = painterResource(card.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Action Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onLike, modifier = Modifier.size(36.dp)) {
                Icon(
                    imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isLiked) AccentColor else MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(Modifier.width(8.dp))
            IconButton(onClick = onClick, modifier = Modifier.size(36.dp)) {
                Icon(Icons.AutoMirrored.Outlined.Comment, null, tint = MaterialTheme.colorScheme.onBackground, modifier = Modifier.size(26.dp))
            }
            Spacer(Modifier.width(8.dp))
            IconButton(onClick = {}, modifier = Modifier.size(36.dp)) {
                Icon(Icons.AutoMirrored.Outlined.Send, null, tint = MaterialTheme.colorScheme.onBackground, modifier = Modifier.size(26.dp))
            }
            Spacer(Modifier.weight(1f))
            IconButton(onClick = onSave, modifier = Modifier.size(36.dp)) {
                Icon(
                    imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        // Likes & Caption
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp)) {
            Text(
                text = "${if (isLiked) card.post.likeCount + 1 else card.post.likeCount} likes",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)) {
                        append(card.post.username)
                    }
                    append("  ")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                        append(card.post.description)
                    }
                },
                fontSize = 14.sp,
                lineHeight = 20.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "View all ${card.post.commentCount} comments",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = card.timeAgo,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )
        }
        
        Spacer(Modifier.height(12.dp))
    }
}