# Relatório Individual - Miguel Pauzinho (27131)

UC: Interação Pessoa-Computador · TG1 · Equipa 14
Ano Letivo: 2025/2026 · IPBeja / ESTIG

---

## 1. Tarefas Realizadas

### Secção 2 - Análise de Sistema Semelhante
Realizei a análise da aplicação **Medisafe**, uma app Android de gestão de medicação. A análise incidiu sobre a interface e design geral, funcionalidades principais, e identificação de pontos positivos e negativos da interface, com particular atenção aos aspetos relevantes para os utilizadores-alvo da EasyMed.

### Secção 3 - Persona
Criei a **Persona 1 - António Ferreira**, um homem de 72 anos, reformado, residente em Aldeia do Bispo, com baixa literacia digital e que toma 4 medicamentos diários para doenças crónicas. A persona foi construída com base em pesquisa sobre os perfis típicos de utilizadores de apps de gestão de medicação em Portugal, nomeadamente idosos que vivem sozinhos com pouca autonomia tecnológica.

### Secção 4 - Cenário de Utilização
Desenvolvi o **Cenário 1 - Confirmar a Toma de Medicamento**, uma narrativa que descreve como o António recebe um alerta sonoro da EasyMed, vai buscar o medicamento, e confirma a toma através de um botão na notificação. O cenário foi escrito em prosa e está diretamente ligado à Persona 1.

### Secção 5 - Análise de Tarefas (HTA)
Realizei a **Análise de Tarefas Hierárquica (HTA)** do Cenário 1, decompondo a tarefa "Confirmar a toma de medicamento" em 4 tarefas principais e respetivas sub-tarefas: receber o alerta, tomar o medicamento, confirmar na app e verificar o resumo do dia.

### Secção 6 - Estilos e Dispositivos de Interação
Em conjunto com o Francisco Soudo, definimos e justificámos os estilos de interação (manipulação direta, menus e listas, notificações) e os dispositivos de interação (ecrã tátil Android, som e vibração) adequados à EasyMed, fundamentados em princípios IPC.

### Secção 7c - Princípios de Usabilidade
Identifiquei e justifiquei 4 princípios de usabilidade de Nielsen aplicados ao design da EasyMed para o meu cenário: Visibilidade do Estado do Sistema, Correspondência com o Mundo Real, Prevenção de Erros e Reconhecimento em vez de Memorização.

### Secção 7a - Esboços e Protótipo Figma
Desenvolvi os esboços iniciais dos ecrãs do Cenário 1 a lápis e papel, cobrindo os 4 momentos principais da interação: ecrã bloqueado com notificação, notificação expandida, confirmação de toma e ecrã principal. Os esboços foram depois transpostos para o **Figma**, onde foram desenhados os 4 ecrãs digitais com um guia de estilo consistente (cor primária #4A90E2, fonte Roboto, botões arredondados de 48dp).

| Ecrã | Nome | Função |
|------|------|--------|
| M1 | Ecrã Bloqueado | Notificação EasyMed no ecrã bloqueado |
| M2 | Notificação Expandida | Botões "Tomei" e "Adiar" acessíveis diretamente |
| M3 | Confirmação de Toma | Feedback imediato com visto verde e hora registada |
| M4 | Ecrã Principal | Dashboard com resumo do dia e lista de medicamentos |

### Secção 7b - Diagrama de Navegação
Criei o diagrama de navegação do Cenário 1 em formato Mermaid (stateDiagram-v2), documentado em `Docs/07_prototipo/miguel/navegacao/README.md`. O fluxo foi desenhado de forma linear e simples: a entrada é sempre pela notificação no ecrã bloqueado, a ação principal (confirmar toma) está a um único toque, e o ecrã principal funciona como hub opcional para consulta do resumo diário.

---

## 2. Organização do Trabalho em Equipa

O trabalho foi organizado segundo uma metodologia Scrum, com gestão das tarefas no Trello (board: IPC TG1 - EasyMed, colunas: Épicos, Tarefas, Sprints). A divisão de tarefas foi a seguinte:

| Tarefa | Miguel | Francisco |
|--------|--------|-----------|
| Análise de sistema semelhante | Medisafe | MyTherapy |
| Persona | António Ferreira | Carla Sousa |
| Cenário de utilização | Confirmar toma | Notificação ignorada |
| HTA | Cenário 1 | Cenário 2 |
| Estilos e dispositivos | Equipa | Equipa |
| Protótipo Figma | Cenário 1 (4 ecrãs) | Cenário 2 (4 ecrãs) |
| Relatório individual | Individual | Individual |

A comunicação entre a equipa foi feita via WhatsApp, com partilha de ficheiros e revisão mútua do trabalho.

---

## 3. Utilização de Inteligência Artificial no Projeto

| Tarefa | Ferramenta IA | Como foi utilizada |
|--------|--------------|-------------------|
| Análise Medisafe | Claude (Anthropic) | Geração de análise de interface com base no conhecimento da app; revista e aprovada por mim |
| Persona 1 | Claude (Anthropic) | Geração do perfil do utilizador com base nas características do público-alvo da EasyMed; revista e aprovada por mim |
| Cenário 1 | Claude (Anthropic) | Geração da narrativa do cenário com base na persona e tarefa definidas; revista e aprovada por mim |
| HTA 1 | Claude (Anthropic) | Geração da decomposição hierárquica de tarefas com base no cenário; revista e aprovada por mim |
| Estilos e dispositivos | Claude (Anthropic) | Geração das justificações com base em princípios IPC; revista e aprovada por mim |
| Princípios de usabilidade | Claude (Anthropic) | Seleção e justificação dos princípios de Nielsen aplicados ao design; revista e aprovada por mim |
| Protótipo Figma | Claude (Anthropic) via MCP | Geração dos 4 ecrãs do Cenário 1 diretamente no Figma via MCP; layout, cores e conteúdo revistos e aprovados por mim |
| Diagrama de navegação | Claude (Anthropic) | Geração do diagrama Mermaid com base no fluxo do Cenário 1; revista e aprovada por mim |

---

## 4. Evidências de Contribuição
> Adicionar aqui capturas de ecrã do Trello e dos commits no GitHub.

---

## 5. Reflexão

O desenvolvimento deste projeto permitiu-me compreender na prática a importância de conhecer bem os utilizadores antes de desenhar qualquer interface. A criação da persona do António tornou evidente que uma app de gestão de medicação tem de ser desenhada com acessibilidade como prioridade - botões grandes, linguagem simples e feedback imediato são decisões de design que fazem a diferença para este perfil de utilizador.

A metodologia Scrum ajudou a organizar o trabalho de forma distribuída no tempo, evitando acumulação de tarefas no final. A divisão clara de responsabilidades entre os elementos da equipa garantiu que cada um contribuiu de forma autónoma mas coerente com o trabalho do outro.

A utilização do Figma para a prototipagem revelou-se mais eficaz do que o Balsamiq originalmente previsto, permitindo um resultado visual mais próximo da aplicação real e facilitando a colaboração - ambos os protótipos ficam no mesmo ficheiro Figma com páginas separadas, mantendo consistência visual entre os dois cenários.
