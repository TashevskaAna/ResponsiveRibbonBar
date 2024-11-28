**Opis projekta**
Ta projekt implementira odzivno postavitev Ribbon, ki posnema Ribbon-Bar iz Microsoft Worda. Namenjena je ustvarjanju prilagodljivih uporabniških vmesnikov v JavaFX. Ključne značilnosti vključujejo:

_Odzivno obnašanje:_

Ob prvem zmanjšanju se vsebine v Ribbon skrijejo in se prikaže samo ikona z gumbom za razširitev (arrow button), ki odpre pojavna okna s skritimi vsebinami.
Pri nadaljnjem zmanjševanju Ribbon prilagodi vrstice s komponentami.
Končna stopnja zmanjšanja vse Ribbon komponente spremeni v ikoniziran način.

_Struktura Ribbon vsebnika:_

Naslov na spodnjem delu.
Komponente v vrsticah (gumbi, preklopni gumbi, potrditvena polja, radijski gumbi, kombinirana polja).
Gumb za storitve, ki odpre pojavna okna.

_Večji prilagodljiv sistem:_

Razvit kot generična komponenta, ki se lahko uporablja kot uporabniški nadzor v SceneBuilderju.
Vse tekstovne vire (npr. besedila in opisi orodij) so naloženi iz resource.properties datoteke za lažje vzdrževanje in lokalizacijo.
Vse vizualne vire (npr. ikone in slogi gumbov) so opredeljeni v style.css datoteki.

**Lastnosti projekta**

_Prilagodljivost:_

Ribbon komponente se prilagodijo glede na velikost zaslona ali okna.
Pri večjem pomanjšanju se komponente samodejno skrijejo in prikažejo le osnovne ikone.

_Funkcionalnost:_

Vsaka Ribbon komponenta vsebuje osnovne JavaFX kontrole (gumbi, preklopni gumbi, potrditvena polja itd.).
Omogočeno prilagajanje referenc na vsebovane komponente prek String-Property lastnosti (npr. prek fx:id).

_Generična komponenta:_

Komponento lahko ponovno se uporabi v različnih projektih kot prilagodljiv uporabniški nadzor (Custom Control).

**Navodila za uporabo**

_Prenos in nastavitev:_

Kloniraj ali prenesi ta repozitorij.
Prepričaj se, da uporabljaš ustrezno različico JavaFX (npr. JavaFX 17 ali novejša).

_Dodajanje v projekt:_

Ribbon komponento lahko uporabiš neposredno z uvozom v svoj SceneBuilder.
Dodaj resource.properties in style.css v svoj projekt za pravilno delovanje.

_Primer strukture:_

Projekt vsebuje tri Ribbon komponente, definirane v FXML datoteki.
Vsaka komponenta uporablja prednastavljene ikone, besedila in pojavna okna.

**Datoteke in struktura**

hello-view.fxml:

Glavna FXML datoteka z definicijami Ribbon vsebnikov.

HelloController.java:

Logika za odzivno obnašanje Ribbon komponente.

resource.properties:

Vsebuje vsa besedila in opise orodij za kontrole.

style.css:

Vsebuje ikone, sloge in oblikovanje za komponente.

HelloApplication.java:

Glavna aplikacija za prikaz delovanja Ribbon postavitve.
