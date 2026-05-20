# Apresentação do Projeto: EasyMed

---

## Slide 1 - Título e Identificação
- **Projeto:** Interação Pessoa-Computador · TG1 · App EasyMed
- **Equipa 14:** Francisco Soudo (14060) e Miguel Pauzinho (27131)
- **Ano Letivo:** 2025/2026 · IPBeja / ESTIG

*(Imagem de fundo/capa sugerida: Logotipo do IPBeja/ESTIG e eventual logotipo da EasyMed)*

---

## Slide 2 - Análise de Sistemas Semelhantes
- **MyTherapy (Análise por Francisco):** Design limpo e foco em lembretes, mas com risco de sobrecarga funcional devido a configurações complexas.
- **Medisafe (Análise por Miguel):** Foco na gestão de medicação, avaliada do ponto de vista de acessibilidade e usabilidade para o público-alvo principal.
- **Conclusão de Equipa:** A EasyMed deve focar-se na simplicidade e na eliminação de funcionalidades excessivas, priorizando a acessibilidade.

*(Imagens sugeridas: Screenshots divididos - MyTherapy de um lado e Medisafe do outro)*

---

## Slide 3 - Personas
- **Persona 1: António Ferreira (por Miguel)**
  - 72 anos, baixa literacia digital (Utilizador Principal).
  - Precisa de alertas simples e botões grandes para confirmar as tomas.
- **Persona 2: Carla Sousa (por Francisco)**
  - 46 anos, enfermeira, cuidadora familiar (Utilizador Secundário).
  - Precisa de monitorizar o pai à distância e receber alertas rápidos em caso de esquecimento.

*(Imagens sugeridas: Duas fotografias representativas dividindo o slide - António e Carla)*

---

## Slide 4 - Cenários de Utilização
- **Cenário 1: Confirmar Toma de Medicamento (António)**
  - Recebe alerta sonoro, vai buscar o medicamento e confirma com um botão na notificação expandida.
- **Cenário 2: Receber Notificação de Toma Ignorada (Carla)**
  - Recebe alerta crítico, verifica que o pai não tomou (código de cor vermelho) e liga-lhe imediatamente pelo atalho na app.

*(Imagens sugeridas: Ilustrações ou ícones de "Confirmação/Check" e "Alerta/Notificação Urgente")*

---

## Slide 5 - Análise de Tarefas (HTA)
- **HTA Cenário 1:** Decomposição em 4 passos simples focados na ação imediata de tomar e confirmar.
- **HTA Cenário 2:** Decomposição focada na reação da cuidadora (receber alerta, consultar detalhe, contactar pai e verificar atualização).
- **Abordagem da Equipa:** Foco em reduzir ao máximo o número de toques (cliques) para atingir o objetivo em ambos os cenários.

*(Imagens sugeridas: Dois esquemas HTA simplificados gerados via Mermaid)*

---

## Slide 6 - Estilos e Dispositivos de Interação
- **Decisões de Equipa:**
  - **Estilos:** Manipulação direta (toques largos e gestos swipe), Menus/Listas simples cronológicas e Notificações proativas (push).
  - **Dispositivos:** Ecrã tátil de smartphone Android.
  - **Acessibilidade:** Botões de grandes dimensões (≥ 48x48dp), uso complementar de som e vibração para alertas de toma.

*(Imagens sugeridas: Ícones de Smartphone Android, Toque/Swipe e Ícones de Som/Vibração)*

---

## Slide 7 - Princípios de Usabilidade (Nielsen)
- **Visibilidade do Estado do Sistema:** Códigos de cores claros (Verde = Tomado, Vermelho = Ignorado) e feedback imediato.
- **Prevenção de Erros:** Exigência de swipe ou botões de confirmação claros para evitar toques acidentais (*fat-finger errors*).
- **Correspondência com o Mundo Real:** Termos não técnicos e listas cronológicas semelhantes a uma agenda diária.
- **Estética e Design Minimalista:** Ecrãs limpos sem distrações, essenciais tanto para o idoso como para a urgência da cuidadora.

*(Imagens sugeridas: Ícones representativos de prevenção de erros e usabilidade visual)*

---

## Slide 8 - Protótipos Figma e Navegação
- **Cenário 1 (António):** Notificação (M1) -> Notificação Expandida (M2) -> Confirmação (M3) -> Ecrã Principal (M4).
- **Cenário 2 (Carla):** Notificação (F1) -> Dashboard do Cuidador (F2) -> Detalhe das Tomas (F3) -> Histórico Semanal (F4).
- **Guia de Estilo Unificado:** Cor primária #4A90E2, tipografia Roboto e coerência visual entre ambas as perspetivas (paciente e cuidador).

*(Imagens sugeridas: Layout com ecrãs representativos do Paciente à esquerda e do Cuidador à direita)*

---

## Slide 9 - Organização do Trabalho e Scrum
- **Metodologia:** Divisão de tarefas estruturada em Trello com base no framework Scrum.
- **Trabalho Colaborativo:** Definição conjunta de interações e estilos, com cenários distintos (Paciente vs Cuidador) que se complementam.
- **Ferramentas:** Comunicação via WhatsApp, versionamento de relatórios em GitHub e design colaborativo em Figma.

*(Imagens sugeridas: Print do Quadro Trello e gráficos combinados de Contribuição do GitHub)*

---

## Slide 10 - Utilização de IA no Projeto
- **Aplicação Ética e Produtiva:** O Claude (Anthropic) foi usado como co-piloto por ambos os elementos da equipa.
- **Casos de Uso:** Estruturação de textos, "brainstorming" para personas, geração de código Mermaid para diagramas HTA/Navegação e suporte ao layout inicial no Figma via MCP.
- Todo o conteúdo gerado foi validado, revisto e aprovado manualmente, garantindo alinhamento com a teoria IPC.

*(Imagens sugeridas: Ícone de IA / Claude aliado a símbolos de revisão ou check mark)*

---

## Slide 11 - Reflexão Final da Equipa
- **Acessibilidade como Prioridade (Miguel):** O design tem de se adaptar ao utilizador com literacia reduzida (António) através de feedback imediato e botões grandes.
- **O Papel do Utilizador Secundário (Francisco):** A app só funciona se a cuidadora (Carla) tiver uma interface rápida e sem sobrecarga para monitorizar e agir em crise.
- **Conclusão:** O Figma permitiu materializar estes conceitos num protótipo visualmente coerente e muito próximo de uma aplicação real.

*(Imagens sugeridas: Fotografia/Ilustração que represente o sucesso do trabalho em equipa no contexto de saúde digital)*
