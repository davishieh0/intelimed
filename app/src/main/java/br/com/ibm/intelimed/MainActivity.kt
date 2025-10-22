package br.com.ibm.intelimed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.ibm.intelimed.ui.theme.IntelimedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Toast de boas-vindas usando context correto
        Toast.makeText(
            this, // Aqui é correto usar 'this' pois estamos dentro da Activity
            "Bem-vindo ao InteliMed!",
            Toast.LENGTH_SHORT
        ).show()

        setContent {
            IntelimedTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    // Usando LocalContext do Compose - forma correta de obter context em Composables
    val context = LocalContext.current

    var counter by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        // Título
        Text(
            text = "InteliMed - Sistema de Saúde",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Contador de exemplo
        Text(
            text = "Contador: $counter",
            style = MaterialTheme.typography.bodyLarge
        )

        // Botão: Incrementar com Toast
        Button(
            onClick = {
                counter++
                // Usando context (não 'this') para Toast em Composable
                showToast(context, "Contador incrementado: $counter")
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Incrementar Contador")
        }

        // Botão: Resetar com Toast de confirmação
        OutlinedButton(
            onClick = {
                if (counter > 0) {
                    counter = 0
                    showToast(context, "Contador resetado!", Toast.LENGTH_SHORT)
                } else {
                    showToast(context, "Contador já está em zero", Toast.LENGTH_SHORT)
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Resetar Contador")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botões de navegação (preparados para futuras Activities)
        NavigationButtons(context)

        Spacer(modifier = Modifier.height(24.dp))

        // Botão de ajuda com Toast informativo
        TextButton(
            onClick = {
                showToast(
                    context,
                    "Sistema InteliMed - Aguardando conexão com MongoDB",
                    Toast.LENGTH_LONG
                )
            }
        ) {
            Text("Ajuda")
        }
    }
}

@Composable
fun NavigationButtons(context: Context) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Navegação (Em desenvolvimento)",
            style = MaterialTheme.typography.titleMedium
        )

        // Botão: Orientação Médica
        ElevatedButton(
            onClick = {
                // Exemplo de como será a navegação correta usando context
                // val intent = Intent(context, MedicalGuidanceActivity::class.java)
                // context.startActivity(intent)
                showToast(context, "Orientação Médica - Em breve", Toast.LENGTH_SHORT)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Orientação Médica")
        }

        // Botão: Registro de Sintomas
        ElevatedButton(
            onClick = {
                // Preparado para: Intent(context, SymptomLogActivity::class.java)
                showToast(context, "Registro de Sintomas - Em breve", Toast.LENGTH_SHORT)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Edit,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Registrar Sintomas")
        }

        // Botão: Relatórios do Paciente
        ElevatedButton(
            onClick = {
                // Preparado para: Intent(context, PatientReportsActivity::class.java)
                showToast(context, "Relatórios do Paciente - Em breve", Toast.LENGTH_SHORT)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Relatórios do Paciente")
        }

        // Botão: Alertas
        ElevatedButton(
            onClick = {
                // Preparado para: Intent(context, AlertManagement::class.java)
                showToast(context, "Gerenciamento de Alertas - Em breve", Toast.LENGTH_LONG)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Notifications,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Alertas")
        }
    }
}

/**
 * Função auxiliar para exibir Toast usando context
 * Demonstra o padrão correto: recebe context como parâmetro ao invés de usar 'this'
 */
fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    IntelimedTheme {
        MainScreen()
    }
}