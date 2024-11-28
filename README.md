#Responsive Ribbon Bar

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

Spodaj so podane slike kako zgleda moja aplikacija:

Na začetek imamo na voljo in pregled na vse komponente 
![Screenshot 2024-11-28 111605](https://github.com/user-attachments/assets/4702910f-7137-43de-9655-2125e132d0e1)

Potem v sekcijo z naslov Clipboard je kliknjen gumb za storitve in se prikaze na levi strani 
![Screenshot 2024-11-28 111630](https://github.com/user-attachments/assets/1c926149-2c7b-44e0-8bff-16a560890540)

Ko kliknemo v sekcijo z naslov Font na gumb za storitve, se prikaže pojavno okno z svojimi funkcionalnostimi
![Screenshot 2024-11-28 111647](https://github.com/user-attachments/assets/c36d535e-5bb2-48d9-b863-6baa9c2f7150)

Isto je narejeno in v sekcijo z naslov Paragraph
![Screenshot 2024-11-28 111712](https://github.com/user-attachments/assets/bf996f87-4deb-4bb8-8c0e-1db45c65af4e)
