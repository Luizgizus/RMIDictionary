# RMIDictionary

O c�digo tem o intuito de construir uma aplica��o b�sica de comunica��o RMI para aprendizado dessa parte da mat�ria com tema de sistemas distribu�dos.

O c�digo tem tr�s m�dulos principais:
	- O m�dulo cliente.
		- Este apresenta uma camada de intera��o por meio de um interface gr�fica constru�da com o window builder, 
		um plugin do eclipse que auxilia a constru��o de interfaces gr�ficas por meio de drag and drop de componentes e etc..
		
		- Simplesmente rodando ele ser� disponibilizado uma interface de inser��o do servidor que se deseja conectar e ap�s escrever o mesmo
		ele abrir� a tela de intera��o caso tudo ocorra corretamente.
		
	- O m�dulo de manipula��o dos dados armazenados.
		- Todo o armazenamento dos dados foi feito dentro de dois arquivos, um arquivo de indexes (baseIdxWords.txt), e um arquivo da base de 
		significados (baseMeaningWords.txt).
		
		- O arquivo de �ndices de indexes tem a seguinte estrutura:
			- palavra;posi��o do significado;deletado ou n�o deletado.
			- A palavra � tratada como string.
			- A posi��o do significado � tratada como um long e indica qual a posi��o do significado no outro arquivo.
			- A flag 'deletado ou n�o deletado' � interpretada como um booleano que indica se a palavra foi deletada ou n�o.
			
		- O arquivo de significados � bastante simples, tem somente os significados separados por quebra de linha
		
	- O m�dulo servidor.
		- No m�dulo do servidor foi usado RMI para a constru��o dos m�todos remotos.
		
		- Nos m�todos que talvez poderiam causar inconsist�ncia dos dados foi adicionado o modificador 'synchronized' de modo que
		o acesso ao m�todo � feito um por vez, n�o deixando sobrescrita de dados acontecer em casos de acesso simult�neo.