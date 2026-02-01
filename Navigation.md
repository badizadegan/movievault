## Navigation

MovieVault verwendet **Navigation Compose** für die Navigation zwischen Screens.
Die Navigationslogik ist in einem eigenen Package gekapselt, sodass
Screens und UI-Komponenten möglichst unabhängig bleiben.

---

## Struktur (wie im Projekt)

ui/navigation/
├── Route // Zentrale Definition aller Routen
├── BottomNavBar // Bottom Navigation UI
├── AppNavigation // Navigation Graph (NavHost)
└── AnimatedAppRoot // Einstiegspunkt inkl. Scaffold & Transitions

---

## Routen (Route)

Alle Navigationsziele werden zentral in der `Route`-Klasse definiert.
Dadurch werden String-Literale nicht im gesamten Projekt verteilt
und Änderungen an Routen sind einfacher und sicherer.

Typische Routen im Projekt:

- **Banner** – Start- bzw. Einstiegsscreen
- **Home** – Hauptübersicht (Trending / Popular)
- **Search** – Suche (derzeit Platzhalter)
- **Favorites** – Favoriten (derzeit Platzhalter)
- **Details/{movieId}** – Detailansicht mit Argument (Movie ID)

---

## Bottom Navigation

Die Bottom Navigation wird in `BottomNavBar` umgesetzt.

- Jede Tab-Option entspricht einer Route
- Der aktuell aktive Tab wird anhand der aktuellen Route hervorgehoben
- Klick auf einen Tab navigiert zum entsprechenden Screen
- Die Bottom Navigation ist nur auf ausgewählten Screens sichtbar
  (z. B. Home, Search, Favorites – nicht auf Banner oder Details)

---

## Navigation Graph (AppNavigation)

`AppNavigation` enthält den zentralen Navigation Graph der Anwendung.

- Definition des `NavHost`
- Zuordnung von Route → Composable Screen
- Übergabe und Auslesen von Argumenten
  (z. B. `movieId` für die Detailansicht)

---

## Animierte Transitions (AnimatedAppRoot)

Der Einstiegspunkt der App ist `AnimatedAppRoot`.
Hier wird ein `Scaffold` mit optionaler Bottom Navigation verwendet
und Screen-Wechsel werden mit animierten Transitions umgesetzt
(z. B. über `AnimatedContent`).

Vorteile:
- Flüssige und moderne Screen-Wechsel
- Verbesserte User Experience
- Klare Trennung von Navigation und UI-Logik

---

## Vorteile dieser Navigationsstruktur

- Navigation ist klar von der UI-Logik getrennt
- Routen sind zentral definiert und leicht wartbar
- Screens bleiben schlank und fokussiert
- Neue Screens oder Tabs können ohne größere Refactorings ergänzt werden