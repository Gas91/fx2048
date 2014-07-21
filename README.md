##Progetto di Programmazione 2 (Gruppo 1):
####Fork del clone di 2048 in JavaFX modificato dagli studenti del Secondo Anno di UNICA
- Luigi Fiorelli - Chiamata del giocatore automatico, eventi, thread, javadoc
- Marco Loriga - Grafica, Algoritmo di Gioco, Layout
- Dario Puligheddu -

##Introduzione a 2048Holo:
Progetto finale per il corso di Programmazione 2, prevedeva la possibilità di implementare un giocatore automatico interfacciabile ad una versione modificata del clone del famoso gioco "2048" realizzato da B. Borges in JavaFX.
La grafica è stata modificata seguendo uno stile più moderno ed elegante.
Nonostante le svariate versioni di algoritmi per la soluzione del puzzle game, ne abbiamo applicato uno semplice ed efficace, il quale sarà spiegato nelle prossime righe.


##Il rework grafico:
Il progetto originale era un fedele clone di 2048, ricalcando sia le meccaniche di gioco, sia l'aspetto della UI. 
Per dargli un tocco di originalità e distaccamento dell'originale, abbiamo apportato delle modifiche grafiche sufficientemente importanti per regalare al gioco un nuovo aspetto, ma per niente invadenti, permettendo al progetto originale di essere sempre compatibile con altri giocatori automatici utilizzanti le stesse interfacce.

Da qui il nome "2048 Holo".

Innanzitutto, è stata realizzata un icona per il programma, poichè ne era sprovvisto; dopodichè si è deciso in comune accordo di modificare il foglio di stile in modo da ricalcare in modo più fedele possibile lo stile "Holo", diffuso sulla piattaforma Android dalla versione 4.0 Ice Cream Sandwitch ed evoluto fin'oggi.
Lo stile prevede una tonalità di sfondo grigio scuro, con elementi grigio chiaro; si può notare la modifica del subtitle da "FX" a "Holo", del famoso colore azzurro. 
I tile sono stati colorati secondo la guida colori ufficiale Android, reperibile qui: http://developer.android.com/design/style/color.html 
Abbiamo dato un aspetto più minimalistico eliminando (ricolorando, per la precisione) lo sfondo della griglia di gioco, ed utilizzando il font "Roboto Light", font utilizzato nelle interfacce Android (pre Android L) estremamente sottile e semplicistico, ma elegante.
Ogni tile utilizza il suddetto font, di colore bianco. L'utilizzo di colori uguali per tile uguali permette di giocare anche "a colpo d'occhio", senza effettivamente controllare il valore dei tile.
Il bottone del giocatore automatico, "Avvia Bot", è stato inserito nello stesso Box del subtitle, per permettere il loro incolonnamento, disposizione molto più pulita della precedente (bottone tra subtitle e score).

##Chiamata del giocatore automatico

Per poter richiamare il giocatore automatico sono state aggiunte una serie di classi che ne permettono il funzionamento:
- AutoGame Il thread che ogni tot ms richiama l'evento per la gestione della mossa automatica successiva.
- BotEvent
- BotEventListener
- SimpleBotEventlistener Genera la mossa successiva e simula la pressione del pulsante.

All'interno della classe GameManager è stata aggiunta la creazione del SimpleBotEventListener (al quale viene passato il GameManager) e del thread AutoGame, al quale viene passato il SimpleBotEventListener; una volta che il thread è avviato continua a girare in beckground controllando il proprio stato di attivazione sino alla pressione dell'apposito pulsante del bot. 
Quando il pulsante viene premuto il thread viene attivato e inizia a richiamare l'eventListener ogni TIME_DELAY_BOT millisecondi; l'eventListener ogni volta che viene richiamato richiede al giocatore automatico la mossa successiva passandogli una Griglia appena recuperata dal GameManager che la genera automaticamente ad ogni richiamo del metodo Move.
Per rendere effettiva la mossa risultante viene simulata la pressione da tastiera della freccia corrispondente (soltanto per la Scene del gioco stesso, in modo che la pressione automatica non interferisca con gli altri programmi); in questo modo il gioco esegue la mossa voluta come se fosse stato premuto fisicamente il pulsante dal giocatore.

Per permettere tutto questo sono state effettuate numerose modifiche alla classe GameManager, in particolare sono stati aggiunti i seguenti metodi:
- createGriglia Crea la griglia di classe MyGriglia necessaria per il funzionamento del giocatore automatico. Viene richiamata ad ogni ciclo di Move.
- getGriglia Restituisce la Griglia di classe MyGriglia precedentemente istanziata.

Inoltre nel metodo createScore di GameManager è stata aggiunta la creazione del bottone del bot e del thread.

La classe Attendi è stata creata per far attendere il thread che la richiama di tot millisecondi o tot secondi in base a quanto necessatio.

##L'intelligenza Artificiale
Inizialmente il giocatore automatico utilizzava un algoritmo estremamente semplice: mosse a caso.
Si è deciso, poi, di studiare più approfonditamente l'algoritmo, implementandone diverse versioni: alcune troppo complesse per il punteggio effettivo finale, alcune troppo semplici e troppo poco fruttuose.
In seguito a diverse ricerche, abbiamo scelto un algoritmo semplice ma funzionale: facendo un breve sondaggio, abbiamo notato che uno dei metodi più semplici per avvicinarsi alla vittoria in 2048 consiste nel tenere in un angolo a scelta (in alto a destra, nel nostro caso) la tile col valore maggiore, e accatastare diagonalmente ad essa i tile di valori via via inferiori, fino a formare una sorta di scacchiera sbilanciata.

L'IA del giocatore automatico scopre, interrogando "isPossibile", quali mosse è possibile fare, e le effettua, ciclando il controllo, seguendo questa priorità:

	-se possibile, muovo le tile verso l'alto
	-se possibile e non ho potuto effettuare la mossa precedente, muovo le tile verso destra
	
Queste sono denominate "mosse di gioco", poichè sono le mosse principali da fare. 
Capita, però, che non sempre queste mosse si possano eseguire, di conseguenza "bloccando" la partita; si utilizzano, quindi, le "mosse di sblocco":

	-se possibile e non ho potuto effettuare la mossa precedente, muovo le tile verso sinistra e subito dopo verso destra (sblocco a sinistra)
	-se possibile e non ho potuto effettuare la mossa precedente, muovo le tile verso il basso

	
<pre>
######################### 
#     #  8  #  16 #  32 # 
#########################
#     #     #  8  #  16 #
#########################
#     #     #     #  8  #
#########################
#     #     #     #     #
#########################

Esempio di situazione bloccata, necessaria una mossa di sblocco:
</pre>


Come per la maggior parte degli algoritmi di 2048, l'ostacolo più importante è dato dallo spawn dei tile 2 e 4 in posizioni "scomode".
Nonostante la sua semplicità, l'algoritmo arriva in media al tile 256 e al 512, con picchi positivi di tile 1024(partita ottima) e picchi negativi di tile 128 o 64(partita pessima).

##Utilizzo:
Il gioco non nasce esclusivamente per il giocatore automatico; al suo avvio, infatti, è possibile giocare normalmente con i tasti freccia della tastiera, sia avviare e fermare a piacimento il Bot per il giocatore automatico tramite l'apposito tasto; è inoltre possibile eseguire delle mosse da tastiera mentre il bot è in azione, in modo da aiutarlo o dargli fastidio.
