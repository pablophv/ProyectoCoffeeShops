package com.example.proyectocoffeeshops

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectocoffeeshops.ui.theme.Ex_prim100
import com.example.proyectocoffeeshops.ui.theme.Ex_prim400
import com.example.proyectocoffeeshops.ui.theme.ProyectoCoffeeShopsTheme
import com.example.proyectocoffeeshops.ui.theme.aliviaregular

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoCoffeeShopsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "MainCoffeeShops"
                    ) {
                        composable("MainCoffeeShops") {
                            MainCoffeeShops(navController = navController)
                        }
                        composable("MostrarResenasLazyGrid/{cafeName}") { backStackEntry ->
                            val cafeName = backStackEntry.arguments?.getString("cafeName")
                            MostrarResenasLazyGrid(
                                navController = navController,
                                cafeName = cafeName
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainCoffeeShops(navController: NavHostController) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Ex_prim100),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "List of CoffeeShops",
                modifier = Modifier.padding(4.dp),
                fontFamily = aliviaregular, fontSize = 24.sp
            )
        }

        ListCoffeeShopsViewLazy(navController = navController)
    }
}

@Composable
fun getCoffeeShops(): List<CoffeeShopsInfo> {

    return listOf(
        CoffeeShopsInfo(
            "Antico Caffe Greco",
            "St. Italy Rome",
            R.drawable.images,
            rating = 0.0
        ),

        CoffeeShopsInfo(
            "CoffeeRoom",
            "St. Italy Rome",
            R.drawable.images1,
            rating = 0.0
        ),
        CoffeeShopsInfo(
            "Antico Caffe Romano",
            "St. Italy Rome",
            R.drawable.images2,
            rating = 0.0
        ),
        CoffeeShopsInfo(
            "Caffee Di Roma",
            "St. Italy Rome",
            R.drawable.images3,
            rating = 0.0
        ),
        CoffeeShopsInfo(
            "Dolci al Gusto",
            "St. Italy Rome",
            R.drawable.images4,
            rating = 0.0
        ),
        CoffeeShopsInfo(
            "Expresso Di Donatello",
            "St. Italy Rome",
            R.drawable.images5,
            rating = 0.0
        ),
        CoffeeShopsInfo(
            "La Antica Italia",
            "St. Italy Rome",
            R.drawable.images5,
            rating = 0.0
        )

    )
}

@Composable
fun ListCoffeeShopsViewLazy(navController: NavHostController) {
    val coffeeShops = getCoffeeShops()
    var selectedItem: CoffeeShopsInfo? by remember { mutableStateOf(null) }

    LazyColumn {
        items(coffeeShops) { coffees ->
            ItemCoffeeShop(
                coffeeShop = coffees,
                selectedItem = selectedItem,
                onItemSelected = {
                    selectedItem = it
                },
                navController = navController
            )
        }
    }
}
//Función para mostrar las imágenes junto con la información de las mismas.

@Composable
fun ItemCoffeeShop(
    coffeeShop: CoffeeShopsInfo,
    selectedItem: CoffeeShopsInfo?,
    onItemSelected: (CoffeeShopsInfo) -> Unit,
    navController: NavHostController
) {

    var rating_1 by remember {
        mutableDoubleStateOf(1.0)
    }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp)
            .clickable {
                onItemSelected(coffeeShop)
                navController.navigate("MostrarResenasLazyGrid/${coffeeShop.coffeShopName}")
            }
    ) {
        Column {
            Image(
                painter = painterResource(id = coffeeShop.photo),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = coffeeShop.coffeShopName,
                fontFamily = aliviaregular,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )

            RatingBar(
                modifier = Modifier.size(50.dp),
                rating = rating_1,
                starsColor = Color.Yellow
            ) {
                rating_1 = it
            }

            Text(
                text = coffeeShop.adressCoffeeShop,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )

            Divider()

            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "RESERVE",
                    color = Ex_prim400,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}


@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
    onRatingChange: (Double) -> Unit
) {

    var isHalfStar = (rating % 1) != 0.0
    Row (Modifier.padding(start = 5.dp)){
        for (index in 1..stars) {
            Icon(
                modifier = Modifier
                    .clickable { onRatingChange(index.toDouble()) },
                contentDescription = null,
                tint = starsColor,
                imageVector = if (index <= rating) {
                    Icons.Rounded.Star
                } else {
                    if (isHalfStar) {
                        isHalfStar = false
                        Icons.Rounded.StarHalf
                    } else {
                        Icons.Rounded.StarOutline
                    }

                },

                )
        }
    }
}

@Composable
fun getResena(): List<String> {

    return listOf(
        "Servicio algo flojo, aún así lo recomiendo",
        "Servicio algo flojo, aún así lo recomiendo",
        "La ambientacion muy buena, pero en la planta de arriba un poco escasa.",
        "La comida estaba deliciosa y bastante bien de precio, mucha variedad de platos",
        "El personal muy amable, nos permitieron ver todo el establecimiento.",
        "Muy bueno",
        "Excelente. Destacable la extensa carta de cafés",
        "Buen ambiente y buen servicio. Lo recomiendo.",
        "En días festivos demasiado tiempo de espera. Los camareros/as no dan abasto. No lo recomiendo. No volveré",
        "Repetiremos. Gran selección de tartas y cafés.",
        "Todo lo que he probado en la cafetería está riquísimo, dulce o salado.",
        "La vajilla muy bonita todo de diseño que en el entorno del bar queda ideal.",
        "Puntos negativos: el servicio es muy lento y los precios son un poco elevados.",
        "Muy bueno",
        "Excelente. Destacable la extensa carta de cafés",
        "La ambientacion muy buena, pero en la planta de arriba un poco escasa.",
        "El personal muy amable, nos permitieron ver todo el establecimiento.",
        "Puntos negativos: el servicio es muy lento y los precios son un poco elevados.",
        "Buen ambiente y buen servicio. Lo recomiendo.",
        "En días festivos demasiado tiempo de espera. Los camareros/as no dan abasto. No lo recomiendo. No volveré",
        "Repetiremos. Gran selección de tartas y cafés.",

        )

}


@Composable
fun MostrarResenasLazyGrid(navController: NavHostController, cafeName: String?) {

    val resenas = getResena()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Ex_prim100),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "List of CoffeeShops",
                modifier = Modifier.padding(4.dp),
                fontFamily = aliviaregular, fontSize = 24.sp
            )

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (cafeName != null) {
                Text(
                    text = cafeName,
                    modifier = Modifier.padding(7.dp),
                    fontFamily = aliviaregular,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), content = {
            items(resenas) { resena ->
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Text(
                            text = resena,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        })
    }

}




