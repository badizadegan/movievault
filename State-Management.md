# State Management

MovieVault verwendet einen einfachen, expliziten und gut nachvollziehbaren
State-Management-Ansatz auf Basis von **StateFlow**.

Ziel ist ein vorhersehbares UI-Verhalten sowie eine klare Trennung
zwischen UI, Business-Logik und Zustandsverwaltung.

---

## UI-State-Konzept

Der Zustand eines Screens wird nicht implizit verwaltet,
sondern explizit über ein zentrales UI-State-Modell abgebildet.

Typische UI-Zustände sind:

- **Loading**
- **Success**
- **Error**
- **Empty**

Diese Zustände werden im gesamten Projekt
über ein gemeinsames Modell (z. B. `UiState`) repräsentiert.

Dadurch wird ein konsistentes und transparentes UI-Verhalten
über alle Screens hinweg sichergestellt.

---

## Rolle der ViewModels

ViewModels sind für das Verwalten und Aktualisieren des UI-States verantwortlich.

Zu ihren Aufgaben gehören:

- Laden von Daten über Repository- oder Domain-Logik
- Übersetzen der Ergebnisse in UI-Zustände
- Bereitstellen des aktuellen Zustands als `StateFlow`

Die UI-Schicht greift niemals direkt auf Datenquellen zu,
sondern beobachtet ausschließlich den vom ViewModel gelieferten State.

---

## StateFlow

Der UI-State wird vom ViewModel zur UI über **StateFlow** exponiert.

Vorteile von StateFlow:

- Lifecycle-aware
- Hält stets den aktuellsten Zustand
- Sehr gut geeignet für Jetpack Compose

Jetpack Compose sammelt den State
und recomposed sich automatisch bei Zustandsänderungen.

---

## UI-Komponenten für Zustände

Für wiederkehrende UI-Zustände werden dedizierte, wiederverwendbare Komponenten eingesetzt:

- `LoadingView` für Ladezustände
- `ErrorView` für Fehlerfälle
- `EmptyView` für leere Ergebnisse

Diese Komponenten sind bewusst generisch gehalten
und können in mehreren Screens wiederverwendet werden.

---

## Vorteile dieses Ansatzes

- Klare und vorhersehbare Zustandslogik
- Weniger UI-Fehler und Seiteneffekte
- Verbesserte Testbarkeit der ViewModels
- Einheitliches Verhalten über alle Screens hinweg