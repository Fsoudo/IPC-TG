# Relatório Individual - Francisco Soudo (14060)

UC: Interação Pessoa-Computador · TG1 · Equipa 14
Ano Letivo: 2025/2026 · IPBeja / ESTIG

---

## 1. Tarefas Realizadas

### Secção 2 - Análise de Sistema Semelhante

Realizei a análise da aplicação **MyTherapy**, uma app disponível para Android e iOS focada na gestão abrangente da medicação e manutenção da saúde do utilizador. A análise incidiu sobre a descrição geral da app, a sua interface e design, as funcionalidades principais, e a identificação de pontos positivos e negativos da interface, com particular atenção aos aspetos relevantes para os utilizadores-alvo da EasyMed.

Os principais pontos analisados foram:
- **Interface e design**: design "clean" com fundo branco e acentos em azul claro; ecrã principal funciona como *To-Do List* cronológica; navegação por barra inferior; tipografia e botões de grandes dimensões adequados ao público idoso.
- **Funcionalidades**: lembretes de medicação avançados, inventário de medicamentos, registo holístico de saúde, monitorização paramétrica, diário de progresso, exportação de relatórios PDF e modo familiar/equipa (Team).
- **Pontos positivos**: design acessível e minimizador do esforço cognitivo, confirmação de toma segura (exige ação intencional), agenda visual e cronológica, sistema de gamificação.
- **Pontos negativos**: risco de sobrecarga funcional, processo de inserção de medicamento extenso, fraca distinção nas notificações do ecrã bloqueado, navegação nas configurações pouco imediata.

### Secção 3 - Persona

Criei a **Persona 2 - Carla Sousa**, uma mulher de 46 anos, enfermeira, licenciada, residente em Beja, casada com dois filhos. A Carla é o perfil do **cuidador familiar** com formação na área da saúde — um utilizador secundário mas igualmente importante na EasyMed. O seu pai, com 74 anos, toma 5 medicamentos diários após um AVC e a Carla utiliza a app para monitorizar remotamente a adesão à medicação do pai, ser alertada quando ele ignora uma toma e ter acesso ao histórico para as consultas médicas. A persona foi construída com base no perfil real de cuidadores familiares próximos de idosos dependentes, com experiência tecnológica acima da média e valorização da eficiência e fiabilidade.

### Secção 4 - Cenário de Utilização

Desenvolvi o **Cenário 2 - Receber Notificação de Toma Ignorada**, uma narrativa que descreve como a Carla, estando no hospital a terminar o turno, recebe uma notificação da EasyMed a informar que o pai não confirmou a toma das 15h da Sinvastatina 20mg. A Carla abre a app, confirma o estado dos medicamentos do dia através do código de cores (vermelho = ignorado, verde = tomado), liga ao pai, que adormeceu e não ouviu o alarme, e acompanha a atualização em tempo real do ícone de vermelho para amarelo (toma efetuada com atraso). O cenário termina com a Carla a verificar o resumo semanal e a identificar um padrão de duas falhas na toma da tarde. O cenário está diretamente ligado à Persona 2.

### Secção 5 - Análise de Tarefas (HTA)

Realizei a **Análise de Tarefas Hierárquica (HTA)** do Cenário 2, decompondo a tarefa "Reagir a uma notificação de toma ignorada" em 5 tarefas principais e respetivas sub-tarefas:

1. Receber a notificação de alerta (sentir vibração/som; ler a notificação)
2. Consultar o detalhe na app (abrir app; aceder ao perfil do pai; identificar medicamento em falta; verificar restantes medicamentos)
3. Contactar o pai (ligar; informar da toma em falta; confirmar que vai tomar)
4. Acompanhar a confirmação na app (aguardar confirmação; verificar atualização do ícone)
5. Analisar o padrão de tomas — tarefa opcional (consultar resumo semanal; identificar padrões; registar observação para o médico)

A decomposição foi também representada num diagrama Mermaid (`graph TD`), documentado em `Docs/05_analise_tarefas/francisco_hta2/hta2_francisco.md`.

### Secção 6 - Estilos e Dispositivos de Interação

Em conjunto com o Miguel Pauzinho, definimos e justificámos os estilos de interação e os dispositivos de interação adequados à EasyMed, fundamentados em princípios IPC. Os estilos definidos foram: **manipulação direta** (toque em botões e gestos), **menus e listas** (navegação inferior e listas cronológicas) e **notificações e alertas** (push proativas). Os dispositivos principais são o **ecrã tátil de smartphone Android** (com considerações de acessibilidade: botões ≥ 48×48dp, texto ajustável, compatibilidade com TalkBack) e o **som e vibração** como mecanismos de alerta complementares.

### Secção 7c - Princípios de Usabilidade

Identifiquei e justifiquei 4 princípios de usabilidade de Nielsen aplicados ao design da EasyMed para o meu cenário (Cenário 2 / Persona Carla):

1. **Visibilidade do Estado do Sistema** — alertas em tempo real e código de cores simples (Verde = Tomado, Vermelho = Ignorado, Amarelo = Tomado com Atraso) para que o cuidador compreenda o estado das tomas de relance.
2. **Prevenção de Erros** — botões de confirmação de toma com intenção extra ou gesto de "swipe" bloqueador, para evitar confirmações acidentais (Fat-Finger error), que são um erro crítico no contexto de saúde.
3. **Correspondência entre o Sistema e o Mundo Real** — histórico de tomas listado por cronologia de horário (idêntico a um calendário diário); linguagem clínica mas acessível, sem termos "informáticos".
4. **Estética e Design Minimalista** — ecrã de crise/notificação limpo com apenas o atalho "Ligar ao Paciente", reduzindo a carga cognitiva nos momentos de preocupação do cuidador.

### Secção 7a - Esboços e Protótipo Figma

Desenvolvi os esboços iniciais dos ecrãs do Cenário 2 a lápis e papel, cobrindo os momentos principais da interação da perspetiva da cuidadora. Os esboços foram depois transpostos para o **Figma**, onde foram desenhados os 4 ecrãs digitais do Cenário 2:

| Ecrã | Nome | Função |
|------|------|--------|
| F1 | Ecrã Bloqueado | Notificação EasyMed de toma não confirmada no ecrã bloqueado |
| F2 | Dashboard Cuidador | Vista do cuidador com estado dos medicamentos do familiar |
| F3 | Detalhe das Tomas | Lista detalhada das tomas do dia com estado de cada uma (por código de cores) |
| F4 | Histórico Semanal | Vista semanal do histórico de adesão ao plano de medicação |

### Secção 7b - Diagrama de Navegação

Criei o diagrama de navegação do Cenário 2 em formato Mermaid (`stateDiagram-v2`), documentado em `Docs/07_prototipo/francisco/navegacao/README.md`. O fluxo parte sempre da notificação no ecrã bloqueado, que transporta o cuidador diretamente para o Dashboard. A partir daí, o acesso ao perfil do pai é o hub central, de onde é possível ver o detalhe de aviso, iniciar uma chamada ou consultar o histórico semanal (com possibilidade de exportação PDF para o médico). A arquitetura é concêntrica no Sumário Diário, evitando bifurcações que fazem perder o contexto da tarefa de urgência.

---

## 2. Organização do Trabalho em Equipa

O trabalho foi organizado segundo uma metodologia Scrum, com gestão das tarefas no Trello (board: IPC TG1 - EasyMed, colunas: Épicos, Tarefas, Sprints). A divisão de tarefas foi a seguinte:

| Tarefa | Miguel (27131) | Francisco (14060) |
|--------|----------------|-------------------|
| Análise de sistema semelhante | Medisafe | MyTherapy |
| Persona | António Ferreira | Carla Sousa |
| Cenário de utilização | Confirmar toma (Cenário 1) | Notificação ignorada (Cenário 2) |
| HTA | Cenário 1 | Cenário 2 |
| Estilos e dispositivos | Equipa | Equipa |
| Protótipo Figma | Cenário 1 (4 ecrãs) | Cenário 2 (4 ecrãs) |
| Diagrama de navegação | Cenário 1 | Cenário 2 |
| Princípios de usabilidade | 4 princípios (Cenário 1) | 4 princípios (Cenário 2) |
| Relatório individual | Individual | Individual |

A comunicação entre a equipa foi feita via WhatsApp, com partilha de ficheiros e revisão mútua do trabalho. As secções partilhadas (como estilos e dispositivos de interação) foram desenvolvidas em conjunto com discussão e validação mútua.

---

## 3. Utilização de Inteligência Artificial no Projeto

A Inteligência Artificial foi uma ferramenta de apoio ao longo do desenvolvimento deste projeto, tendo sido utilizada de forma criteriosa para auxiliar na estruturação de ideias, redação de conteúdos e geração de código para diagramas. A principal ferramenta utilizada foi o **Claude (Anthropic)**, integrado no meu fluxo de trabalho.

De acordo com as exigências da unidade curricular, detalha-se de forma pormenorizada a utilização da IA nas tarefas que me foram atribuídas:

- **Análise do Sistema Semelhante (MyTherapy)**: Utilizei a IA para ajudar a estruturar e organizar os pontos-chave da análise da interface. Após a minha própria experimentação da aplicação MyTherapy, forneci à IA os aspetos que pretendia destacar (positivos e negativos), e a ferramenta auxiliou na formalização do texto, que foi depois revisto e ajustado por mim.
- **Caracterização dos Utilizadores (Persona Carla Sousa)**: A criação da persona teve o apoio da IA na geração do perfil narrativo. A partir das características base que defini para o público-alvo (cuidadores familiares de idosos), o modelo gerou um perfil inicial que foi adaptado e validado para garantir que os seus objetivos e frustrações se adequavam ao contexto real e justificavam o uso da EasyMed.
- **Cenários de Utilização e Análise de Tarefas (HTA)**: A IA foi utilizada como base inspiracional para gerar a narrativa do Cenário 2 com base na persona desenhada. Para a Análise de Tarefas, pedi à IA propostas de decomposição hierárquica (HTA) da tarefa principal do cenário, o que me ajudou a identificar todos os passos lógicos. O diagrama e a lista finais foram validados criticamente.
- **Estilos, Dispositivos e Princípios de Usabilidade**: A IA foi usada para relacionar as decisões de design (ex: notificações proativas, botões grandes) com os princípios teóricos de usabilidade de Nielsen e os conceitos de Interação Pessoa-Computador lecionados. As justificações geradas foram revistas para assegurar a sua correta aplicação ao desenho da aplicação.
- **Protótipo Figma e Diagrama de Navegação**: Para a conceção do diagrama de navegação, a IA foi essencial na geração rápida da sintaxe *Mermaid* representativa do fluxo de ecrãs. Em relação ao protótipo, utilizei a IA (via Model Context Protocol) para auxiliar na disposição inicial e criação de elementos (layout) dos ecrãs do Cenário 2 no Figma. Todos os aspetos de conteúdo, cores e usabilidade final foram verificados, alterados e aprovados manualmente por mim.

Em todas as etapas, a Inteligência Artificial atuou como um co-piloto para "brainstorming" e aceleração de tarefas rotineiras. Todo o resultado final foi submetido a um processo de revisão, edição e curadoria manual, de modo a garantir a consistência do projeto e o cumprimento das metodologias exigidas.

---

## 4. Evidências de Contribuição

Como evidência do trabalho colaborativo, do versionamento de código e da contribuição ao longo do projeto, apresentam-se abaixo os gráficos de contribuição (*Commits over time* e resumo por contribuidor) retirados do repositório GitHub oficial do projeto (disponível em [https://github.com/Fsoudo/IPC-TG1-PART-1](https://github.com/Fsoudo/IPC-TG1-PART-1)). Nestes gráficos é possível verificar de forma clara a contribuição e o volume de *commits* do Francisco Soudo (Fsoudo) e do colega de equipa (miguele618):

*(Nota: Insere aqui a imagem dos gráficos do GitHub que anexaste na conversa).*

*(Nota: Insere também aqui a imagem do quadro do Trello para complementar a evidência de gestão de tarefas).*

---

## 5. Reflexão

O desenvolvimento deste projeto permitiu-me compreender na prática a importância de definir bem os utilizadores antes de desenhar qualquer interface. A criação da persona da Carla Sousa revelou uma dimensão que inicialmente não estava tão evidente: a importância do utilizador secundário (o cuidador) numa app de gestão de medicação. O António pode ser o utilizador principal, mas é a Carla quem garante que ele adere ao plano — e a app tem de servir ambos de forma igualmente eficaz.

A análise do MyTherapy foi fundamental para perceber o que funciona e o que falha numa app do género. O risco de sobrecarga funcional que identifiquei no MyTherapy orientou diretamente as decisões de design do Cenário 2: o ecrã de notificação da EasyMed tem de ser limpo, direto e focado na ação urgente — ligar ao pai — sem distrações.

A metodologia Scrum ajudou a manter o trabalho distribuído e evitar a acumulação de tarefas no final. A divisão clara de responsabilidades com o Miguel garantiu coerência entre os dois cenários, mantendo uma linguagem visual e conceptual consistente no mesmo ficheiro Figma.
