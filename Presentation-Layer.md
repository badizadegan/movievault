# Presentation Layer (UI)

Die Presentation-Schicht ist für die Darstellung der Benutzeroberfläche
und die Verarbeitung von Nutzerinteraktionen zuständig.

In MovieVault wird die UI mit Jetpack Compose umgesetzt.
Die Screens beobachten den UI-State aus dem ViewModel und reagieren
automatisch auf Änderungen (Recomposition).

## Verantwortlichkeiten

- UI-Aufbau mit Jetpack Compose (Material 3)
- Darstellung von Listen und Details (z. B. Trending/Popular, Movie Details)
- Verarbeitung von Nutzeraktionen (z. B. Klick auf einen Film)
- Beobachten von UI-State und Reaktion auf Zustandsänderungen
- Navigation zwischen Screens

## Struktur (wie im Projekt)

ui/
├── components/
├── design/
├── navigation/
├── screen/
│   ├── banner/
│   ├── home/
│   ├── details/
│   ├── search/
│   └── favorites/
├── theme/
└── MainActivity.kt

## MainActivity

`MainActivity` ist der Einstiegspunkt der App.
Sie setzt das Compose-UI-Root und startet die Navigation bzw. den Start-Screen.

## Screens und Components

- Screens stellen vollständige Seiten dar (z. B. Home, Details, Search, Favorites).
- Components sind wiederverwendbare UI-Bausteine (z. B. Cards, Listen-Items).

Ein wichtiges Prinzip: **State gehört in den Screen/ViewModel**, nicht in einzelne Components.
Components sollten möglichst „dumb“ sein und nur Daten anzeigen sowie Events nach oben weiterreichen.

## ViewModel und UI-State

ViewModels kapseln die Logik zur Datenbeschaffung und stellen den UI-State bereit.

- ViewModels rufen Repository/Domain-Logik auf
- Ergebnisse werden in einen UI-State (z. B. Loading/Success/Error/Empty) überführt
- Die UI sammelt den State und rendert entsprechend

Dadurch bleibt die UI klar, testbarer und leichter erweiterbar.

## Status

Search und Favorites sind aktuell noch nicht vollständig umgesetzt
und dienen derzeit als Platzhalter-Screens.
Die Architektur der UI ist jedoch so aufgebaut, dass diese Features
ohne große Umstrukturierung ergänzt werden können.