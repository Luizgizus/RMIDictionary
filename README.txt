# RMIDictionary

O código tem o intuito de construir uma aplicação básica de comunicação RMI para aprendizado dessa parte da matéria com tema de sistemas distribuídos.

O código tem três módulos principais:
	- O módulo cliente.
		- Este apresenta uma camada de interação por meio de um interface gráfica construída com o window builder, 
		um plugin do eclipse que auxilia a construção de interfaces gráficas por meio de drag and drop de componentes e etc..
		
		- Simplesmente rodando ele será disponibilizado uma interface de inserção do servidor que se deseja conectar e após escrever o mesmo
		ele abrirá a tela de interação caso tudo ocorra corretamente.
		
	- O módulo de manipulação dos dados armazenados.
		- Todo o armazenamento dos dados foi feito dentro de dois arquivos, um arquivo de indexes (baseIdxWords.txt), e um arquivo da base de 
		significados (baseMeaningWords.txt).
		
		- O arquivo de índices de indexes tem a seguinte estrutura:
			- palavra;posição do significado;deletado ou não deletado.
			- A palavra é tratada como string.
			- A posição do significado é tratada como um long e indica qual a posição do significado no outro arquivo.
			- A flag 'deletado ou não deletado' é interpretada como um booleano que indica se a palavra foi deletada ou não.
			
		- O arquivo de significados é bastante simples, tem somente os significados separados por quebra de linha
		
	- O módulo servidor.
		- No módulo do servidor foi usado RMI para a construção dos métodos remotos.
		
		- Nos métodos que talvez poderiam causar inconsistência dos dados foi adicionado o modificador 'synchronized' de modo que
		o acesso ao método é feito um por vez, não deixando sobrescrita de dados acontecer em casos de acesso simultâneo.