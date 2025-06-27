package com.example.mycityapp.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycityapp.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycityapp.data.Place
import com.example.mycityapp.data.Recommendation
import com.example.mycityapp.data.RecommendationsDataSource
import com.example.mycityapp.data.RecommendationsDataSource.recommendationsList
import com.example.mycityapp.model.MyCityUiState
import com.example.mycityapp.utils.MyCityContentType




enum class MyCityScreen(@StringRes val title: Int){
    RecommendationScreen(title = R.string.recommendation_screen),
    PlaceListScreen(title = R.string.place_list_screen)
}

@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    navController: NavHostController = rememberNavController()
){

    val viewModel: MyCityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val contentType: MyCityContentType

    when(windowSize){
        WindowWidthSizeClass.Expanded -> {
            contentType = MyCityContentType.ListAndDetail
        }
        else -> {
            contentType = MyCityContentType.ListOnly
        }
    }

    Scaffold(
        topBar = {
            MyCityAppBar(
                uiState = uiState,
                windowSize = windowSize,
                onBackButtonClicked = {
                    if(uiState.isShowingPlacesPage){
                        viewModel.navigateToRecommendationsPage()
                        navController.navigateUp()
                    }
                    else{
                        viewModel.navigateToListPage()
                    }
                }
            )
        }
    ) { innerPadding ->
        if(contentType == MyCityContentType.ListAndDetail){
            RecommendationsListAndPlaces(
                uiState = uiState,
                viewModel = viewModel,
                windowSize = windowSize,
                recommendations = recommendationsList,
                onRecommendationClick = { recommendation->
                    viewModel.updateCurrentRecommendation(recommendation)
                },
                onPlaceClick = { viewModel.updateCurrentPlace(it)},
                modifier = Modifier.padding(innerPadding)
            )
        }
        else {
            NavHost(
                navController = navController,
                startDestination = MyCityScreen.RecommendationScreen.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = MyCityScreen.RecommendationScreen.name) {
                    RecommendationsList(
                        recommendations = RecommendationsDataSource.recommendationsList,
                        onClick = { recommendation ->
                            viewModel.updateCurrentRecommendation(recommendation)
                            viewModel.navigateToPlacesPage()
                            navController.navigate(MyCityScreen.PlaceListScreen.name)
                        }
                    )
                }
                composable(route = MyCityScreen.PlaceListScreen.name) {
                    PlacesScreen(
                        uiState = uiState,
                        windowSize = windowSize,
                        viewModel = viewModel,
                        onClick = {
                            viewModel.updateCurrentPlace(it)
                            viewModel.navigateToDetailPage()
                        }
                    )
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    uiState: MyCityUiState,
    windowSize: WindowWidthSizeClass,
    onBackButtonClicked:() -> Unit,
    modifier: Modifier = Modifier
){
    val isShowingRecommendationsPage = uiState.isShowingRecommendationsPage
    val isShowingPlacesPage = uiState.isShowingPlacesPage

    TopAppBar(
        title = {
            Text(
                text = if(isShowingRecommendationsPage){
                    stringResource(R.string.app_name)
                }
                else if(isShowingPlacesPage){
                    stringResource(uiState.currentRecommendation.name)
                }
                else{
                    stringResource(uiState.currentPlace.name)
                }
            )
        },
        navigationIcon = {
            if(!isShowingRecommendationsPage && windowSize != WindowWidthSizeClass.Expanded){
                IconButton(onClick = onBackButtonClicked) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.Back_Button)
                    )
                }
            }else{
                Box{}
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary
        ),
        modifier = modifier
    )
}

@Composable
private fun RecommendationsList(
    recommendations: List<Recommendation>,
    onClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues = PaddingValues(0.dp)
){
    LazyColumn(
        contentPadding = contentPaddingValues,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(dimensionResource(R.dimen.padding_small))
    ) {
        items(recommendations) { recommendation ->
            RecommendationsListItem(
                recommendation = recommendation,
                onItemClick = onClick
            )
        }
    }
}

@Composable
private fun RecommendationsListAndPlaces(
    uiState: MyCityUiState,
    viewModel: MyCityViewModel,
    windowSize: WindowWidthSizeClass,
    recommendations: List<Recommendation>,
    onRecommendationClick: (Recommendation) -> Unit,
    onPlaceClick: (Place) -> Unit,
    modifier: Modifier = Modifier
){
  Row(
      modifier = modifier
          .padding(
              top = dimensionResource(R.dimen.padding_small),
              start = dimensionResource(R.dimen.padding_small)
          ),
      horizontalArrangement = Arrangement.SpaceEvenly
  ) {
      RecommendationsList(
          recommendations = recommendations,
          onClick = onRecommendationClick,
          modifier = Modifier.weight(1f)
      )
      PlacesScreen(
          uiState = uiState,
          windowSize = windowSize,
          viewModel = viewModel,
          onClick = onPlaceClick,
          modifier = Modifier.weight(2f)
      )
  }
}


@Composable
private fun RecommendationsListItem(
    recommendation: Recommendation,
    onItemClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            RecommendationImage(
                recommendation = recommendation,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_small)
                    )
                    .width(200.dp)
            ) {
                Text(
                    text = stringResource(recommendation.name),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(bottom = dimensionResource(R.dimen.padding_small))
                        .align(Alignment.Start)
                )
                Text(
                    text = stringResource(recommendation.desc),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.scrim,
                    overflow = TextOverflow.Ellipsis
                )
            }
            IconButton(
                onClick = {onItemClick(recommendation)},
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun RecommendationImage(
    recommendation: Recommendation,
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(recommendation.image),
        contentDescription = null,
        alignment = Alignment.Center,
        contentScale = ContentScale.FillWidth,
        modifier = modifier
    )
}

@Composable
@Preview
fun RecommendationListItemPreview(){
    MyCityAppTheme {
        RecommendationsListItem(
            recommendation = RecommendationsDataSource.defaultList,
            onItemClick = {}
        )
    }
}

