# EasyMed — IPC Trabalho de Grupo

**Interação Pessoa-Computador · IPBeja / ESTIG · Engenharia Informática · 2025/2026**

> App móvel Android de apoio à gestão de medicação de pacientes por cuidadores informais.

---

## Equipa

| Aluno | Nº | GitHub | Cenário |
|---|---|---|---|
| Francisco Manuel Baião Soudo | 14060 | [@Fsoudo](https://github.com/Fsoudo) | Cenário 2 — Notificação de Toma Ignorada (Cuidador) |
| Miguel Pauzinho | 27131 | [@miguele618](https://github.com/miguele618) | Cenário 1 — Confirmação de Toma (Paciente) |

**Docente:** Luís Garcia

---

## Estado do Projeto

| Componente | Estado |
|---|---|
| Parte 1 — Análise e Desenho | ✅ Concluída |
| Parte 2 — Implementação e Avaliação | ✅ Concluída  |

---

## Parte 1 — Análise e Desenho

### Sobre a EasyMed

A **EasyMed** é uma aplicação móvel Android desenhada para dois tipos de utilizador:

- **Paciente** (Cenário 1 — Miguel): idoso com múltiplos medicamentos diários que precisa de confirmação de toma e lembretes visuais/sonoros.
- **Cuidador informal** (Cenário 2 — Francisco): familiar com formação em saúde que monitoriza remotamente a adesão à medicação do paciente.

### Persona — Francisco (Cenário 2)

**Carla Sousa** · 46 anos · Enfermeira · Beja

- Monitoriza o pai (António, 74 anos, pós-AVC, 5 medicamentos/dia) à distância
- Precisa de ser notificada quando ele ignora uma toma
- Valoriza interfaces rápidas, limpas e sem sobrecarga de informação

### Cenário 2 — Receber Notificação de Toma Ignorada

A Carla, durante o turno no hospital, recebe uma notificação push da EasyMed: o pai não confirmou a toma da **Sinvastatina 20mg** às 15h (30 min de atraso). Abre a app, vê o dashboard com código de cores (🔴 ignorado), acede ao detalhe, liga ao pai através do botão de chamada direta, e acompanha a atualização do estado em tempo real (🔴 → 🟡 tomado com atraso). Consulta o histórico semanal e identifica um padrão recorrente de falhas à tarde.

### Análise de Tarefas — HTA Cenário 2

```
0. Reagir a uma notificação de toma ignorada
   ├── 1. Receber e reconhecer o alerta (ecrã bloqueado)
   ├── 2. Diagnóstico de contexto (app → dashboard → detalhe)
   ├── 3. Intervenção direta (ligar ao paciente)
   ├── 4. Verificação de ciclo (confirmar atualização de estado)
   └── 5. Análise de tendências [opcional] (histórico semanal)
```

Diagrama completo: [`Docs/05_analise_tarefas/francisco_hta2/hta2_francisco.md`](Docs/05_analise_tarefas/francisco_hta2/hta2_francisco.md)

### Ecrãs do Protótipo (Figma) — Cenário 2

| ID | Ecrã | Descrição |
|----|------|-----------|
| F1 | Ecrã Bloqueado | Notificação crítica com paciente e medicamento em falta |
| F2 | Dashboard Cuidador | Cartões com estado visual por paciente (código de cores) |
| F3 | Detalhe das Tomas | Lista cronológica + semântica de cores + botão "Ligar ao Paciente" |
| F4 | Histórico Semanal | Vista de calendário + exportação de relatório PDF |

Protótipos: [`Docs/07_prototipo/francisco/`](Docs/07_prototipo/francisco/)

### Decisões de Design

**Semântica de cores:**
- 🟢 Verde — Tomado
- 🔴 Vermelho — Ignorado
- 🟡 Amarelo — Tomado com Atraso

**Heurísticas Nielsen aplicadas:**
1. Visibilidade do estado do sistema
2. Prevenção de erros (botões ≥ 48×48dp, swipe intencional para confirmar)
3. Correspondência entre o sistema e o mundo real
4. Estética e design minimalista

**Navegação:** Arquitetura concêntrica centrada no "Sumário Diário" — Bottom Navigation Bar (Hoje / Medicamentos / Histórico / Perfil)

**Acessibilidade:** TalkBack, texto ajustável, botões com área de toque mínima de 48×48dp

### Sistema Semelhante Analisado

**MyTherapy** (Android/iOS) — gestão de medicação e saúde holística.

| Pontos Positivos | Pontos Negativos |
|---|---|
| Design limpo e acessível (ecrã branco, acentos azuis) | Risco de sobrecarga funcional |
| Confirmação de toma com ação intencional | Processo de inserção de medicamento extenso |
| Agenda visual e cronológica | Fraca distinção entre tipos de notificação |
| Sistema de gamificação | Navegação nas configurações pouco intuitiva |

Análise completa: [`Docs/02_sistemas_similares/francisco_mytherapy/analise_mytherapy.md`](Docs/02_sistemas_similares/francisco_mytherapy/analise_mytherapy.md)

### Ferramentas

| Ferramenta | Uso |
|---|---|
| Figma | Protótipos de baixa e média/alta fidelidade |
| Trello | Gestão de projeto (Épicos / Tarefas / Sprints) |
| GitHub | Controlo de versões |
| WhatsApp | Comunicação diária da equipa |

### Melhorias

|Apresentação| Separar as partes nos slides, não ter tudo junto (Parte do Miguel/Parte do Francisco)|
|Ser mais explicito na distinção dos cenarios, nao resumir nada, indicar tudo|


---

## Parte 2 — Implementação e Avaliação *(em desenvolvimento)*

### Objetivo

Implementar um protótipo funcional da EasyMed em **Android** e realizar uma primeira avaliação com utilizadores reais.

### Componentes

| Componente | Cotação | Responsável (Francisco) |
|---|---|---|
| Protótipo de alta fidelidade (app Android) | 10 val. | Ecrãs F1–F4 do Cenário 2 |
| Funcionalidade implementada | 5 val. | Dashboard + Detalhe das Tomas com código de cores e chamada direta |
| Avaliação com utilizadores | 3 val. | Sessões de teste + questionário pós-teste |
| Apresentação final | 2 val. | Demo + metodologia + resultados (Parte 1 + Parte 2) |

### Funcionalidade Prioritária a Implementar

**Dashboard do Cuidador com atualização de estado em tempo real** (F2 + F3):
- Lista de pacientes com indicador de estado por cor
- Navegação para detalhe das tomas ao tocar no paciente
- Botão "Ligar ao Paciente" com `Intent ACTION_DIAL`
- Simulação de transição de estados para demo

### Plano de Avaliação com Utilizadores

**Metodologia:** Teste de usabilidade com observação + questionário pós-teste (SUS)

**Tarefas propostas:**

| # | Tarefa | Métrica |
|---|--------|---------|
| T1 | Identificar o medicamento em falta do António | ≤ 3 cliques, tempo |
| T2 | Ligar ao paciente através da app | Sucesso S/N, nº cliques |
| T3 | Consultar o histórico semanal | Tempo, erros |

**Métricas recolhidas:** nº de cliques, tempo de conclusão, nº de erros, taxa de sucesso

---

## Estrutura do Repositório

```
/
├── README.md                          # Este ficheiro
├── Docs/
│   ├── 00_eventos/
│   ├── 01_organizacao/
│   ├── 02_sistemas_similares/
│   │   ├── francisco_mytherapy/       # Análise MyTherapy
│   │   └── miguel_medisafe/           # Análise MediSafe
│   ├── 03_utilizadores/
│   │   ├── francisco_persona2/        # Persona Carla Sousa
│   │   └── miguel_persona1/
│   ├── 04_cenarios/
│   │   ├── francisco_cenario2/        # Cenário 2 — Toma Ignorada
│   │   └── miguel_cenario1/
│   ├── 05_analise_tarefas/
│   │   ├── francisco_hta2/            # HTA Cenário 2
│   │   └── miguel_hta1/
│   ├── 06_estilos_interacao/
│   └── 07_prototipo/
│       ├── francisco/                 # Ecrãs F1–F4 (Figma, esboços, navegação)
│       └── miguel/
├── Relatorios/
│   ├── relatorio_francisco_14060.md
│   └── relatorio_miguel_27131.md
└── [app Android — a adicionar na Parte 2]
```

---

## Entrega

- **Parte 1:** Concluída — Abril 2026
- **Parte 2:** Em desenvolvimento — Maio/Junho 2026
- **Formato:** Relatório PDF único (grupo) + Apresentação + Repositório GitHub app Android
- **Plataforma:** Moodle IPC — IPBeja
