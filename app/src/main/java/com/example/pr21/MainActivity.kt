package com.example.pr21

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.composesqliteapp.DatabaseHelper
import com.example.pr21.ui.theme.NikolaenkoPR22101Theme
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        dbHelper = DatabaseHelper(this)
        setContent {
            NikolaenkoPR22101Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    UserInputScreen(dbHelper)
                }
            }
        }
    }
}

@Composable
fun UserInputScreen(dbHelper: DatabaseHelper) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var year by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Введите имя") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Введите год") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current
        Button(
            onClick = {
                val nameText = name.text
                val yearText = year.text.toIntOrNull()
                if (nameText.isNotEmpty() && yearText != null) {
                    dbHelper.insertUser(nameText, yearText)
                    Toast.makeText(
                        context,
                        "Пользователь сохранен",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            content = {
                Text("Сохранить пользователя")
            }
        )
    }
}
