package com.example.ktorkoin.ui.screen.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ktorkoin.ui.screen.Loading
import com.google.accompanist.coil.rememberCoilPainter
import org.koin.androidx.compose.getViewModel

@Composable
fun ProductScreen(
    navHostController: NavHostController,
    id: String,
) {
    val viewModel: ProductViewModel = getViewModel()
    val state = viewModel.state.collectAsState().value

    if (state.isLoading) {
        Loading()
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.getProductById(id.toInt())
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        IconButton(onClick = { navHostController.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")

        }
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            state.product?.let { product ->
                val painter = rememberCoilPainter(request = product.image)
                Image(
                    painter = painter,
                    contentDescription = "painter",
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = product.description)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = product.price.toString(),
                    fontSize = 20.sp,
                    color = Color.Green)
            }
        }
    }
}