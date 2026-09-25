package com.example.vetcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.Pet
import com.example.vetcare.ui.theme.VetCareTheme
import com.example.vetcare.viewmodel.PetViewModel
import com.example.vetcare.viewmodel.PetViewModelFactory
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private lateinit var petViewModel: PetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val appContainer = AppContainer()
        val factory = PetViewModelFactory(appContainer.petRepository)
        petViewModel = ViewModelProvider(this, factory)[PetViewModel::class.java]

        setContent {
            VetCareTheme {
                val pets by petViewModel.pets.collectAsState()

                LaunchedEffect(Unit) {
                    petViewModel.loadPets()
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = { Text("VetCare - Mascotas") })
                    }
                ) { innerPadding ->
                    PetList(
                        pets = pets,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@androidx.compose.runtime.Composable
private fun PetList(
    pets: List<Pet>,
    modifier: Modifier = Modifier
) {
    if (pets.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
            Text(
                text = "Cargando mascotas...",
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(pets, key = { it.id }) { pet ->
                PetCard(pet)
            }
        }
    }
}

@androidx.compose.runtime.Composable
private fun PetCard(pet: Pet) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = pet.name, style = MaterialTheme.typography.titleLarge)
            Text(text = "Especie: ${pet.species}")
            Text(text = "Raza: ${pet.breed}")
            Text(text = "Edad: ${pet.age} años")
        }
    }
}
