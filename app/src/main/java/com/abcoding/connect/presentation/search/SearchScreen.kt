package com.abcoding.connect.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abcoding.connect.presentation.components.StandardToolbar
import com.abcoding.connect.R
import com.abcoding.connect.domain.models.User
import com.abcoding.connect.presentation.components.StandardTextField
import com.abcoding.connect.presentation.components.StandardTextFieldSmall
import com.abcoding.connect.presentation.components.UserProfileItem
import com.abcoding.connect.presentation.ui.theme.IconSizeMedium
import com.abcoding.connect.presentation.ui.theme.SpaceLarge
import com.abcoding.connect.presentation.ui.theme.SpaceMedium
import com.abcoding.connect.presentation.ui.theme.SpaceSmall
import com.abcoding.connect.presentation.util.states.StandardTextFieldState

@ExperimentalMaterialApi
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            StandardToolbar(
                navController = navController,
                showBackArrow = true,
                title = {
                    StandardTextFieldSmall(
                        text = viewModel.searchState.value.text,
                        hint = stringResource(id = R.string.search),
                        onValueChange = {
                            viewModel.setSearchState(
                                StandardTextFieldState(text = it)
                            )
                        },

                    )
                },

                )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceSmall)
        ) {

            Spacer(modifier = Modifier.height(SpaceSmall))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(10) {
                    UserProfileItem(
                        user = User(
                            profilePictureUrl = "",
                            username = "Philipp Lackner",
                            description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed\n" +
                                    "diam nonumy eirmod tempor invidunt ut labore et dolore \n" +
                                    "magna aliquyam erat, sed diam voluptua",
                            followerCount = 234,
                            followingCount = 534,
                            postCount = 65
                        ),
                        actionIcon = {
                            Icon(
                                imageVector = Icons.Default.PersonAdd,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.size(IconSizeMedium)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(SpaceMedium))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun Preview() {
    val navController = rememberNavController()
    SearchScreen(navController = navController)
}