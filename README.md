# Introductie

Deze applicatie is ontwikkeld als eindopdracht voor de hbo opleiding Full-Stack Developer leerlijn backend van Novi Hogeschool en onderdeel van de bachelor opleiding Software Development.

Deze backend applicatie is geschreven in Java met Spring Boot framework, voor de geschereven test cases is gebruik gemaakt van Junit en Mockito.
Installatie handleidingen en andere documentatie zijn niet meegeleverd op github maar kunnen desgevraagd worden aangeleverd.

# Beschrijving van de casus
**De eindopdracht is ontwikkeld op basis van onderstaande casus:**

In de garage komen klanten hun auto afleveren voor een reparatie. Een administratief medewerker voegt de klant en de auto toe aan het systeem, wanneer de klant en of de auto voor het eerst bij de garage komen. De medewerker plant vervolgens een moment in om de auto te keuren. Tijdens deze registratie kunnen de autopapieren in pdf-formaat toegevoegd worden. 

Een monteur keurt vervolgens de auto en voegt de gevonden tekortkomingen toe aan de auto in het systeem. Nadat de auto gekeurd is, neemt de monteur contact op met de klant. Gaat de klant akkoord met de reparatie, dan maakt de monteur een afspraak om de auto te repareren. 

Gaat de klant niet akkoord met de reparatie dan zet de monteur dat in het systeem, maakt hij de bon op voor de keuring, à 45 euro, en kan de klant de auto komen ophalen en wordt de reparatie op 'niet uitvoeren' gezet.

Wanneer de klant akkoord gaat, voegt de monteur toe wat afgesproken is en gaat hij de auto repareren. Elk onderdeel dat gebruikt wordt, wordt toegevoegd aan de reparatie. Ook wordt elke handeling gedocumenteerd. Vervangt de monteur bijvoorbeeld de remschijf dan wordt het onderdeel remschijf aan de reparatie toegevoegd en wordt de handeling remschijf vervangen aan de reparatie toegevoegd.

Al deze onderdelen en handelingen staan al, inclusief prijs, in het systeem. De monteur kan deze opgeslagen handelingen en onderdelen selecteren. Omdat een monteur soms iets specifieks moet doen, kan de monteur ook een 'overige' handeling en prijs toevoegen.

Wanneer de reparatie voltooid is, wordt de reparatie op voltooid gezet en kan de klant opgebeld worden. De klant wordt opgebeld door een administratief medewerker. Deze medewerker kan een lijst opvragen met te bellen klanten waarvan de reparatie voltooid is of de status 'niet uitvoeren' is.

Wanneer de klant de auto komt ophalen zal een kassamedewerker de bon laten genereren door het systeem. De bon bevat de keuring + bedrag, de handelingen + bedrag en de onderdelen + bedrag. Bij alle bedragen moet het BTW tarief nog berekend worden voordat de bedragen op de bon getoond worden. Wanneer de klant betaald heeft, wordt de status op betaald gezet.

Daarnaast is er een backoffice medewerker die onderdelen (naam, prijs, voorraad) kan toevoegen aan het systeem, voorraden kan aanpassen en handelingen (naam, prijs) kan toevoegen aan het systeem. Alle prijzen in het systeem zijn exclusief BTW. 

# Opgeleverde documentatie eindopdracht:

**Voor deze eindopdracht is de volgende documentatie opgeleverd:**

*	**Functioneel Ontwerp:**
    * requirements;
    * UML use case diagrammen; 
    * UML use case scenarios.

*	**Technisch Ontwerp:**
    *	UML klassendiagram;
	  * UML sequence diagrammen;
	  * database model (Entity Relation Diagram en Relationeel Model);
	  * beveiliging.

* **Verantwoordingsdocument broncode:**
    *	uitleg toegepaste technieken op basis van SOLID;
    *	uitleg toegepaste technieken in de code (met focus op Java en Hibernate).

* **Installatiehandleiding:**
    *	handleiding om benodigde applicaties te installeren: 
        * IDE IntelliJ IDEA
        * JDK versie 15
        * PostgreSQL
        * Postman
    *	handleiding om de backend applicatie op de database aan te sluiten;
    *   instructies om de applicatie te gebruiken met rest endpoints en uitgeschreven JSON body's.
