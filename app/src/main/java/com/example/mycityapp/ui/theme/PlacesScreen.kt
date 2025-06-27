package com.example.mycityapp.ui.theme

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycityapp.R
import com.example.mycityapp.data.Place
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycityapp.data.PlacesDataSource
import com.example.mycityapp.data.RecommendationsDataSource
import com.example.mycityapp.model.MyCityUiState

@Composable
fun PlacesScreen(
    uiState: MyCityUiState,
    windowSize: WindowWidthSizeClass,
    viewModel: MyCityViewModel = viewModel(),
    onClick: (Place) -> Unit,
    modifier: Modifier =  Modifier
){
    val recommendation = uiState.currentRecommendation

    Column(
        modifier = modifier
    ){
        if(windowSize != WindowWidthSizeClass.Expanded){
            if(uiState.isShowingPlacesPage){
                PlaceList(
                    places = recommendation.places,
                    onClick = onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(R.dimen.padding_medium),
                            start = dimensionResource(R.dimen.padding_medium),
                            end = dimensionResource(R.dimen.padding_medium),
                        )
                )
            }
            else{
                PlaceDetails(
                    selectedPlace = uiState.currentPlace,
                    onBackPressed = { viewModel.navigateToListPage() }
                )
            }
        }
        else{
            PlaceListAndDetails(
                places = uiState.currentRecommendation.places,
                onClick = onClick,
                selectedPlace = uiState.currentPlace
            )
        }
    }
}

@Composable
private fun PlaceList(
    places: List<Place>,
    onClick: (Place) -> Unit,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues = PaddingValues(0.dp)
){
    LazyColumn(
        contentPadding = contentPaddingValues,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier
    ) {
        items(places) { place ->
            PlaceListItem(
                place = place,
                onItemClick = onClick
            )
        }
    }
}

@Composable
private fun PlaceListAndDetails(
    places: List<Place>,
    onClick: (Place) -> Unit,
    selectedPlace: Place,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
){

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        PlaceList(
            places = places,
            onClick = onClick,
            contentPaddingValues = PaddingValues(
                top = contentPadding.calculateTopPadding()
            ),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )

        val context = LocalContext.current
        val activity = remember(context) { context as? Activity }

        PlaceDetails(
            selectedPlace = selectedPlace,
            onBackPressed = {activity?.finish()},
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding()
            ),
            modifier = Modifier.weight(1f).padding(end = dimensionResource(R.dimen.padding_small))
        )

    }

}

@Composable
private fun PlaceListItem(
    place: Place,
    onItemClick: (Place) -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(place) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            PlaceImage(
                place = place,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_medium),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
            ) {
                Text(
                    text = stringResource(place.name),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
                )
                Text(
                    text = stringResource(place.location),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.scrim,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
                )
                Text(
                    text = stringResource(place.description),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.scrim,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
private fun PlaceImage(
    place: Place,
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(place.image),
        contentDescription = null,
        alignment = Alignment.Center,
        contentScale = ContentScale.FillWidth,
        modifier = modifier
    )
}

@Composable
private fun PlaceDetails(
    selectedPlace: Place,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
){
    BackHandler {
        onBackPressed()
    }
    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current
    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(top = contentPadding.calculateTopPadding())
    ){
        Column(
            modifier = Modifier
                .padding(
                    bottom = contentPadding.calculateBottomPadding(),
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    end = contentPadding.calculateLeftPadding(layoutDirection)
                )
        ) {
            Box{
                Image(
                    painter = painterResource(selectedPlace.image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.detail_image_height))
                )
            }
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
                        horizontal = dimensionResource((R.dimen.padding_detail_content_horizontal))
                    )
            ) {
                Text(
                    text = stringResource(selectedPlace.name),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)

                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = stringResource(selectedPlace.location),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = stringResource(selectedPlace.description),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PlacesListItemPreview(){
    MyCityAppTheme {
        PlaceListItem(
            place = PlacesDataSource.defaultPlace,
            onItemClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PlaceDetailsPreview(){
    MyCityAppTheme {
        PlaceDetails(
            selectedPlace = PlacesDataSource.defaultPlace,
            onBackPressed = {}
        )
    }
}

@Composable
@Preview(showBackground = true, widthDp = 1000)
fun PlaceListAndDetailPreview(){
    MyCityAppTheme {
        Surface {
            PlaceListAndDetails(
                places = RecommendationsDataSource.defaultList.places,
                onClick = {},
                selectedPlace = PlacesDataSource.defaultPlace
            )
        }
    }
}
