package com.abcoding.connect.presentation.components

import androidx.compose.animation.core.EaseInCirc
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.abcoding.connect.R
import com.abcoding.connect.domain.models.Post
import com.abcoding.connect.presentation.ui.theme.HintGray
import com.abcoding.connect.presentation.ui.theme.ProfilePictureSizeExtraSmall
import com.abcoding.connect.presentation.ui.theme.ProfilePictureSizeExtraSmallplus
import com.abcoding.connect.presentation.ui.theme.ProfilePictureSizeMedium
import com.abcoding.connect.presentation.ui.theme.SpaceMedium
import com.abcoding.connect.presentation.ui.theme.SpaceSmall
import com.abcoding.connect.presentation.ui.theme.md_theme_light_inversePrimary
import com.abcoding.connect.presentation.ui.theme.md_theme_light_onSecondary
import com.abcoding.connect.presentation.ui.theme.md_theme_light_scrim
import com.abcoding.connect.presentation.ui.theme.md_theme_light_secondary
import com.abcoding.connect.presentation.ui.theme.md_theme_light_shadow
import com.abcoding.connect.presentation.util.Constants

@Composable
fun Post(
    post: Post,
    modifier: Modifier = Modifier,
    showProfileImage: Boolean = true,
    onPostClick: () -> Unit = {},
    isLiked: Boolean = false,

) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
            .clickable {
                onPostClick()
            }
    ) {
        // User Info Section (Profile Image and Name)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            if (showProfileImage) {
                Image(
                    painterResource(id = R.drawable.basid),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(ProfilePictureSizeExtraSmallplus)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            // User Name
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "abdul_bazzi",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.clickable(onClick = {  })
            )
        }

        // Post Image
        Image(
            painterResource(id = R.drawable.basid),
            contentDescription = "Post image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(MaterialTheme.shapes.extraSmall)
        )

        // Like, Comment, Share Icons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            ActionButtons(
                isLiked = isLiked,

                onLikeClick = { isLiked ->

                },
                onCommentClick = {

                },
                onShareClick = {

                }

                )
        }
        //post and comment count

        Spacer(modifier = Modifier.height(SpaceSmall))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(
                    id = R.string.liked_by_x_people,
                    post.likeCount
                ),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = stringResource(
                    id = R.string.x_comments,
                    post.commentCount
                ),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = MaterialTheme.typography.headlineSmall
            )
        }

        // Post Content
        Spacer(modifier = Modifier.height(SpaceSmall))
                Text(
                    text = buildAnnotatedString {
                        append(post.description)
                        withStyle(
                            SpanStyle(
                                color = HintGray,
                            )
                        ) {
                            append(
                                LocalContext.current.getString(
                                    R.string.read_more
                                )
                            )
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = Constants.MAX_POST_DESCRIPTION_LINES
                )
    }
}

@Composable
fun ActionButtons(
    modifier: Modifier = Modifier,
    isLiked: Boolean = false,
    onLikeClick: (Boolean) -> Unit = {},
    onCommentClick: () -> Unit = {},
    onShareClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,

        ) {
        IconButton(
            onClick = {
                onLikeClick(!isLiked)
            },
            modifier = modifier.size(Constants.iconSize)
        ) {
            Icon(
                imageVector = if (isLiked) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Outlined.FavoriteBorder
                },
                tint = if (isLiked) {
                    Color.Red
                } else {
                    MaterialTheme.colorScheme.tertiaryContainer
                },
                contentDescription = if (isLiked) {
                    stringResource(id = R.string.unlike)
                } else {
                    stringResource(id = R.string.like)
                }
            )
        }
        Spacer(modifier = Modifier.width(SpaceMedium))
        IconButton(
            onClick = {
                onCommentClick()
            },
            modifier = modifier.size(Constants.iconSize)
        ) {
            Icon(
                imageVector = Icons.Outlined.Comment,
                contentDescription = stringResource(id = R.string.comment),
                tint = MaterialTheme.colorScheme.tertiaryContainer

            )
        }
        Spacer(modifier = Modifier.width(SpaceMedium))

        IconButton(
            onClick = {
                onShareClick()
            },
            modifier = modifier.size(Constants.iconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = stringResource(id = R.string.share),
                tint = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }

}
@Preview
@Composable
fun Preview(){
    Post(post = Post(
        username = "Alakanandah a",
        "",
        "",
        "ahbcuacah cuhvjabchab a ckjnjnain ababa0",
        24,
        12
    ))
}


