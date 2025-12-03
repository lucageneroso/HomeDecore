# HomeDecore

**HomeDecore** è una piattaforma e-commerce full-stack per il settore arredamento, sviluppata su stack tecnologico **Jakarta EE**.
Il progetto non si limita all'implementazione del codice, ma è il risultato di un ciclo di ingegneria del software completo, strutturato per garantire scalabilità, sicurezza e una chiara separazione delle responsabilità.

---

## 📐 Processo di Ingegneria e Design

Il valore distintivo di questa repository risiede nell'approccio metodologico adottato prima e durante lo sviluppo. Il software è stato modellato seguendo standard industriali per garantire la tracciabilità dai requisiti al codice.

Nella cartella `Deliverables/` è disponibile la documentazione tecnica completa:

* **RAD (Requirements Analysis Document):** Analisi dei requisiti funzionali e non funzionali.
* **SDD (System Design Document):** Definizione dell'architettura di alto livello e delle interfacce.
* **ODD (Object Design Document):** Specifica di dettaglio delle classi e dei diagrammi di sequenza.
* **Test Plan:** Strategie di testing e casi di test (Unit & Integration).

---

## 💻 Architettura del Sistema

L'applicazione adotta un'architettura modulare basata sul pattern **MVC (Model-View-Controller)**, supportata da un layer di servizi e persistenza gestita via ORM.

### Highlights Tecnici
* **Backend Core:** Sviluppato in Java puro con Servlets per la gestione delle richieste HTTP. La logica di business è disaccoppiata dai controller tramite un Service Layer dedicato.
* **Dependency Injection:** Utilizzo di **CDI** per la gestione delle dipendenze, migliorando la testabilità e riducendo l'accoppiamento tra i componenti.
* **Data Persistence:** Gestione dei dati affidata a **JPA/Hibernate**, garantendo transazioni atomiche e astrazione dal database fisico.
* **Design Patterns:** Applicazione pragmatica di pattern quali *DAO, DTO, Front Controller* e *Composite* per risolvere specifiche sfide architetturali senza sovra-ingegnerizzare.

---

## 🌟 Funzionalità della WebApp

HomeDecore gestisce flussi di lavoro complessi che coinvolgono diversi attori, ciascuno con dashboard dedicate:

### 🛒 Lato Cliente
* Navigazione catalogo con filtri avanzati.
* Gestione carrello e checkout multi-step (simulazione pagamenti carte/PayPal).
* Area personale per storico ordini e tracking spedizioni.
* Sistema di recensioni prodotti e chat di supporto.

### 🏭 Lato Gestione (Back-office)
* **Gestore Ordini:** Supervisione del ciclo di vita dell'ordine (approvazione, gestione anomalie).
* **Magazziniere:** Gestione dello stock, evasione fisica degli ordini e aggiornamento giacenze.
* **Fornitore:** Portale dedicato per l'inserimento di nuovi prodotti a catalogo e gestione delle forniture.

---

## 🛠️ Tech Stack

* **Language:** Java 17+
* **Web Framework:** Jakarta EE (Servlets, JSP, JSTL)
* **Persistence:** Hibernate ORM / JPA
* **Database:** MySQL
* **Build & Dependency Management:** Maven
* **Testing:** JUnit

---

## 🚀 Quick Start

Per analizzare il progetto o eseguirlo localmente:

1.  **Clone:**
    ```bash
    git clone [https://github.com/lucageneroso/homedecore.git](https://github.com/lucageneroso/homedecore.git)
    ```
2.  **Database Setup:**
    Importare lo schema MySQL e configurare le credenziali in `src/main/resources/META-INF/persistence.xml`.
3.  **Build:**
    ```bash
    mvn clean install
    ```
4.  **Deploy:**
    Il pacchetto `.war` generato in `target/` è pronto per il deploy su qualsiasi container compatibile (es. Apache Tomcat 10+).

---
