# EasyMed - Protótipo Funcional (TG2 de IPC)

App Android de gestão da medicação semanal para pacientes idosos e os seus cuidadores.
Trabalho de Grupo 2 de Interação Pessoa-Computador (IPBeja/ESTIG, 2025/2026) - continuação do TG1.

**Equipa 14**
- Miguel Pauzinho (27131) - Cenário 1: o paciente confirma a toma (ecrãs M1-M4)
- Francisco Soudo (14060) - Cenário 2: a cuidadora reage a uma toma ignorada (ecrãs F1-F4)

## Como executar

1. Abrir a pasta `EasyMed` no Android Studio
2. Esperar pela sincronização do Gradle
3. Correr a app num emulador ou telemóvel (API 24+)

## Como fazer a demo dos dois cenários

**Cenário 1 (paciente):** no ecrã *Hoje*, tocar em **Simular Alarme** -> aparece o ecrã
bloqueado com a notificação -> tocar na notificação -> **Tomei** -> ecrã de confirmação
com a hora real -> *Ver resumo do dia* (a toma fica verde).

**Cenário 2 (cuidadora):** em *Perfil*, **Mudar para o modo Cuidador** -> no dashboard,
tocar em **Simular toma ignorada** -> alerta no ecrã bloqueado da Carla -> tocar no alerta
-> detalhe das tomas do António -> **Ligar ao António** (abre o marcador) -> **Forçar
Registo** -> confirmar no diálogo (a toma fica amarela e o alerta passa a "resolvido").

## Estrutura do código

```
app/src/main/java/com/example/easymed/
├── MainActivity.kt        <- ponto de entrada + sistema de notificações + navegação ("when")
├── DadosApp.kt            <- data classes (Medicamento, Toma, EstadoToma) + estado
│                             partilhado da app (singleton) + funções de ação
├── Componentes.kt         <- peças de UI reutilizadas (cabeçalho, cartão de toma, semana)
├── ui/theme/              <- cores do protótipo do TG1, tema claro fixo, texto 18sp
└── ecras/
    ├── miguel/            <- M1 bloqueado, M2 notificação, M3 confirmação, M4 "Hoje"
    ├── francisco/         <- F1 alerta crítico, F2 dashboard, F3 detalhe das tomas
    │                         (com "Forçar registo"), F4 histórico semanal
    └── EcraPerfil.kt      <- troca de modo paciente <-> cuidador (partilhado)
```

## Decisões principais (justificadas no relatório)

- **Jetpack Compose** - a tecnologia usada nas aulas de IPC
- **Ligação paciente-cuidador simulada localmente** - os dois perfis partilham os dados
  na mesma app (o enunciado permite ilustrar o funcionamento geral)
- **Alarmes/alertas simulados por botão** - demo controlável e fiável nos testes com
  utilizadores (plano B definido no planeamento)
- **Acessibilidade** - texto base 18sp, botões >= 48dp, contentDescription nos ícones
  (TalkBack), código de cores com texto associado (não depende só da cor)
