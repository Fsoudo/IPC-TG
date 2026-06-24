package com.example.easymed

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

// ============================================================
// NOTIFICAÇÕES REAIS DO ANDROID (TG2)
// Autores: Miguel Pauzinho (27131) e Francisco Soudo (14060)
//
//
//
// Este objeto trata do envio de notificações reais para a barra
// de notificações do Android (NotificationCompat + NotificationChannel).
//
// No Android 8.0+ (API 26) todas as notificações precisam de ser
// associadas a um "canal" (categoria). Criamos dois canais:
//   CANAL_TOMA    = lembrete de medicação (Cenário 1 - paciente)
//   CANAL_ALERTA  = alerta crítico de toma ignorada (Cenário 2 - cuidador)
//
// "object" = singleton: existe uma só instância, partilhada por todos
// os ecrãs que chamem enviarNotificacaoToma() / enviarNotificacaoAlerta().
//
//
// ============================================================
object NotificacoesHelper {

    // identificadores dos dois canais de notificação
    private const val CANAL_TOMA   = "canal_toma_medicacao"
    private const val CANAL_ALERTA = "canal_alerta_critico"

    // ============================================================
    // CENÁRIO 1 (Miguel) - LEMBRETE DE TOMA
    //
    // Chamada pelo EcraHoje quando o utilizador prime "Simular Alarme".
    // Envia uma notificação do tipo "É hora de tomar o medicamento".
    //
    // Parâmetros:
    //   contexto        - acesso ao Android (necessário para o NotificationManager)
    //   nomeMedicamento - ex: "Metformina"
    //   dosagem         - ex: "500mg"
    //   hora            - ex: "12:00"
    // ============================================================
    fun enviarNotificacaoToma(
        contexto: Context,
        nomeMedicamento: String,
        dosagem: String,
        hora: String
    ) {
        // vai buscar o gestor de notificações do Android
        val gestor = contexto.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // cria o canal (só tem efeito na primeira chamada; depois é ignorado)
        val canal = NotificationChannel(
            CANAL_TOMA,
            "Lembretes de medicação",
            NotificationManager.IMPORTANCE_HIGH
        )
        gestor.createNotificationChannel(canal)

        // constrói a notificação
        val notificacao = NotificationCompat.Builder(contexto, CANAL_TOMA)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("EasyMed - Hora da medicação")
            .setContentText("Está na hora de tomar $nomeMedicamento $dosagem ($hora)")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        // envia (id 1 = lembrete de toma)
        gestor.notify(1, notificacao)
    }

    // ============================================================
    // CENÁRIO 2 (Francisco) - ALERTA CRÍTICO
    //
    // Chamada pelo EcraDashboard quando a Carla prime "Simular toma ignorada".
    // Envia uma notificação de alerta vermelho para o telemóvel da Carla.
    //
    // Parâmetros:
    //   contexto        - acesso ao Android
    //   nomeMedicamento - ex: "Sinvastatina"
    //   dosagem         - ex: "20mg"
    //   horaPrevista    - ex: "15:00"
    // ============================================================
    fun enviarNotificacaoAlerta(
        contexto: Context,
        nomeMedicamento: String,
        dosagem: String,
        horaPrevista: String
    ) {
        val gestor = contexto.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // canal de alerta critico (prioridade máxima)
        val canal = NotificationChannel(
            CANAL_ALERTA,
            "Alertas críticos de medicação",
            NotificationManager.IMPORTANCE_HIGH
        )
        gestor.createNotificationChannel(canal)

        val notificacao = NotificationCompat.Builder(contexto, CANAL_ALERTA)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("EasyMed - Alerta")
            .setContentText("António não confirmou $nomeMedicamento $dosagem das $horaPrevista")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        // envia (id 2 = alerta critico)
        gestor.notify(2, notificacao)
    }
}
