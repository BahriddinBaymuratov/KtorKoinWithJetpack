package com.example.ktorkoin.ui.screen.product_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ktorkoin.model.StoreDTO
import com.example.ktorkoin.ui.screen.Loading
import com.example.ktorkoin.ui.screen.Screen
import com.google.accompanist.coil.rememberCoilPainter
import org.koin.androidx.compose.getViewModel

@Composable
fun ProductListScreen(navHostController: NavHostController) {
    val viewModel = getViewModel<ProductLIstViewModel>()
    val state = viewModel.state.collectAsState().value

    if (state.isLoading) {
        Loading()
    }
    if (state.error.isNotEmpty()) {
        println("@@@${state.error}")
    }
    LazyColumn {
        items(state.postList, key = { it.id }) {
            StoreItem(store = it) { id ->
                navHostController.navigate("${Screen.PostScreen.route}/$id")
            }
        }
    }


}

@Composable
fun StoreItem(
    store: StoreDTO,
    onClick: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onClick(store.id)
            },
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = rememberCoilPainter(request = store.image)
            Image(
                painter = painter,
                contentDescription = "image",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = store.title,
                    fontSize = 17.sp,
                    maxLines = 1)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = store.price.toString())

            }

        }

    }

}