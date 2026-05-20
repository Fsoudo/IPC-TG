# Secção 6 - Estilos e Dispositivos de Interação
**Responsável:** Equipa (Miguel Pauzinho 27131 · Francisco Soudo 14060)

## Estilos de Interação

### 1. Manipulação Direta
O estilo de interação principal da EasyMed é a **manipulação direta** - o utilizador interage com os elementos visuais no ecrã através de toque, pressão e gesto.

**Exemplos na app:**
- Carregar no botão "Tomei" para confirmar uma toma
- Deslizar um cartão de medicamento para ver detalhes
- Carregar numa notificação para abrir a app diretamente

**Justificação:**
A manipulação direta é o estilo mais adequado para uma app Android destinada a utilizadores como o António (idoso com pouca experiência digital). Segundo o princípio de **correspondência entre o sistema e o mundo real** (Nielsen), a interação deve ser semelhante ao que o utilizador já conhece - e o toque em botões grandes é uma metáfora simples e familiar. Além disso, o **feedback imediato** (botão que muda de cor após o toque) confirma ao utilizador que a ação foi registada, reduzindo a ansiedade de não saber se "funcionou".

---

### 2. Menus e Listas
A navegação entre as diferentes secções da app (medicamentos, histórico, perfil) é feita através de **menus e listas**.

**Exemplos na app:**
- Menu de navegação inferior com ícones (Hoje, Medicamentos, Histórico, Perfil)
- Lista cronológica de medicamentos no ecrã principal
- Lista de medicamentos registados nas definições

**Justificação:**
Os menus de navegação inferior são o padrão nas apps Android modernas, o que garante **consistência com o sistema** (heurística de Nielsen) - o utilizador não precisa de aprender uma nova forma de navegar. Para o perfil do António, a lista cronológica de medicamentos é especialmente adequada pois apresenta a informação de forma estruturada e fácil de seguir, reduzindo a **carga cognitiva**.

---

### 3. Notificações e Alertas
As **notificações push** são o mecanismo principal de comunicação proativa da app com o utilizador.

**Exemplos na app:**
- Alerta sonoro e visual de toma de medicamento
- Notificação ao cuidador quando uma toma é ignorada
- Confirmação visual após registar uma toma

**Justificação:**
Para utilizadores como o António, que podem esquecer-se de abrir a app por iniciativa própria, as notificações são essenciais para garantir a **visibilidade do estado do sistema** - o utilizador é informado proativamente do que precisa de fazer, sem ter de procurar essa informação. Para a Carla (cuidadora), as notificações de toma ignorada permitem **controlo e monitorização remota**, dando-lhe a sensação de presença mesmo à distância.

---

## Dispositivos de Interação

### 1. Ecrã Tátil (Smartphone Android)
O dispositivo principal de interação é o **ecrã tátil de um smartphone Android**.

**Justificação:**
O smartphone é o dispositivo mais acessível e portátil para os dois perfis de utilizadores da EasyMed. O António já possui um smartphone e está familiarizado com o toque básico. A Carla utiliza o smartphone diariamente em contexto profissional e pessoal. A escolha de Android garante compatibilidade com uma vasta gama de dispositivos a preços acessíveis, importante para utilizadores com recursos limitados.

**Considerações de acessibilidade:**
- Botões de grandes dimensões (mínimo 48x48dp) para facilitar o toque por utilizadores com menor destreza manual
- Texto de tamanho ajustável para utilizadores com dificuldades de visão
- Compatibilidade com o leitor de ecrã TalkBack do Android

---

### 2. Som e Vibração
O **som e a vibração** são dispositivos de saída complementares ao ecrã, essenciais para o funcionamento dos alertas.

**Justificação:**
Para o António, que pode não ter o telemóvel à vista em todos os momentos, o som é o principal mecanismo que chama a sua atenção para a notificação de toma. A vibração serve de complemento em ambientes ruidosos ou quando o telemóvel está em silêncio. Estes dispositivos garantem que o sistema consegue **interromper o utilizador de forma não intrusiva** no momento certo, sem exigir que este esteja a olhar para o ecrã.
