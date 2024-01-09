package com.abcoding.connect.presentation.post_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abcoding.connect.R
import com.abcoding.connect.domain.models.Comment
import com.abcoding.connect.domain.models.Post
import com.abcoding.connect.presentation.components.ActionButtons
import com.abcoding.connect.presentation.components.StandardToolbar
import com.abcoding.connect.presentation.ui.theme.ConnectTheme
import com.abcoding.connect.presentation.ui.theme.HintGray
import com.abcoding.connect.presentation.ui.theme.ProfilePictureSizeExtraSmall
import com.abcoding.connect.presentation.ui.theme.ProfilePictureSizeExtraSmallplus
import com.abcoding.connect.presentation.ui.theme.ProfilePictureSizeMedium
import com.abcoding.connect.presentation.ui.theme.SpaceMedium
import com.abcoding.connect.presentation.ui.theme.SpaceSmall
import com.abcoding.connect.presentation.util.Constants

@Composable
fun PostDetailScreen(
    navController: NavController,
    post: Post
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StandardToolbar(
            navController = navController,
            title = {
                Text(
                    text = stringResource(id = R.string.your_feed),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)

        ) {

            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(8.dp)

                    ) {
                        // User Info Section (Profile Image and Name)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Profile Image

                            Image(
                                painterResource(id = R.drawable.basid),
                                contentDescription = "Profile picture",
                                modifier = Modifier
                                    .size(ProfilePictureSizeExtraSmallplus)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )


                            // User Name
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "abdul_bazzi",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.clickable(onClick = { })
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

                        Text(
                            text = stringResource(
                                id = R.string.liked_by_x_people,
                                post.likeCount
                            ),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.headlineSmall
                        )

                        // Post Content
                        Spacer(modifier = Modifier.height(SpaceSmall))

                        Text(
                            text = post.description,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Spacer(modifier = Modifier.height(SpaceSmall))


                    }
                }
            }
            items(20) {
                Comment(
                    modifier = Modifier,
                    comment = Comment(
                        username = "Abdul Basid$it",
                        comment = "Lorem ipsum dolor sit amet, consectetur adipiscing " +
                                "elit, sed do eiusmod tempor incididunt ut labore et " +
                                "dolore magna aliqua. Ut enim ad minim veniam, quis "
                    )
                )
                Spacer(modifier = Modifier.height(SpaceSmall))
            }
        }

    }

}

@Composable
fun Comment(
    modifier: Modifier = Modifier,
    comment: Comment = Comment(),
    onLikeClick: (Boolean) -> Unit = {}
) {
    Card(
        modifier = modifier,
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceMedium)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.basid),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(ProfilePictureSizeExtraSmallplus),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(SpaceSmall))
                    Text(
                        text = comment.username,
                        fontWeight = FontWeight.Bold,
                        style = androidx.compose.material.MaterialTheme.typography.body2,
                        color = androidx.compose.material.MaterialTheme.colors.onBackground
                    )
                }
                Text(
                    text = "2 days ago",
                    style = androidx.compose.material.MaterialTheme.typography.body2
                )
            }
            Spacer(modifier = Modifier.height(SpaceMedium))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = comment.comment,
                    style = androidx.compose.material.MaterialTheme.typography.body2,
                    color = androidx.compose.material.MaterialTheme.colors.onBackground,
                    modifier = Modifier.weight(9f)
                )
                Spacer(modifier = Modifier.width(SpaceMedium))
                IconButton(
                    onClick = {
                        onLikeClick(comment.isLiked)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        tint = if (comment.isLiked) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.outline
                        },
                        contentDescription = if (comment.isLiked) {
                            stringResource(id = R.string.unlike)
                        } else stringResource(id = R.string.like),
                        modifier = modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(SpaceMedium))
            Text(

                text = stringResource(id = R.string.liked_by_x_people, comment.likeCount),
                fontWeight = FontWeight.Bold,
                style = androidx.compose.material.MaterialTheme.typography.subtitle2,
                color = androidx.compose.material.MaterialTheme.colors.onBackground
            )
        }
    }
}

@Preview
@Composable
fun PostDetailScreenPreview() {
    val fakePost = Post(
        username = "Abdul Basid",
        imageUrl = "",
        profilePictureUrl = "",
        description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed" +
                " diam nonumy eirmod tempor invidunt ut labore et dolore \n  " +
                "agna aliquyam erat, sed diam voluptuaLorem ipsum dolor sit amet, consetetur sadipscing elitr, sed \n " +
                "diam nonumy eirmod tempor invidunt ut labore et dolore" +
                "magna aliquyam erat, sed diam voluptua",
        likeCount = 70,
        commentCount = 17
    )
    val fakeNavController = rememberNavController()

    ConnectTheme {
        PostDetailScreen(
            navController = fakeNavController,
            post = fakePost
        )
    }
}


