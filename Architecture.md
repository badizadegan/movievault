## Architektur

MovieVault ist eine Android-Anwendung, die mit **Jetpack Compose** entwickelt wird.
Das Projekt folgt einer schichtbasierten Architektur, die an der **Clean Architecture**
angelehnt ist, und verwendet das **MVVM-Pattern** für das UI-State-Management.

Der Fokus liegt auf einer sauberen Struktur, klaren Verantwortlichkeiten
und einem vorhersehbaren, unidirektionalen Datenfluss.
Einige Funktionen befinden sich noch in Entwicklung und werden schrittweise ergänzt.

---

## Architekturüberblick

Die Anwendung ist in drei Hauptschichten unterteilt:

- **UI-Schicht (Presentation Layer)**
- **Domain-Schicht**
- **Data-Schicht**

Jede Schicht hat eine klar definierte Aufgabe und kommuniziert
ausschließlich mit den jeweils vorgesehenen Nachbarschichten.

---

## Schichten und Verantwortlichkeiten

### UI-Schicht (Presentation Layer)

Die UI-Schicht ist für die Darstellung der Benutzeroberfläche
und die Verarbeitung von Benutzerinteraktionen verantwortlich.

- Umsetzung mit **Jetpack Compose** und **Material 3**
- Screens beobachten den vom ViewModel bereitgestellten **UI-State** (`StateFlow`)
- Darstellung der Zustände *Loading*, *Error*, *Empty* und *Success*
- Navigationslogik ist von den UI-Komponenten getrennt
- Keine direkte Abhängigkeit von Netzwerk- oder Datenquellen

---

### Domain-Schicht

Die Domain-Schicht enthält die zentrale Logik der Anwendung
und definiert die Verträge zwischen UI- und Data-Schicht.

- Definition von **Domain-Modellen**
- Definition von **Repository-Interfaces**
- Unabhängig vom Android-Framework und externen Bibliotheken

---

### Data-Schicht

Die Data-Schicht ist für das Laden, Verarbeiten und Bereitstellen von Daten zuständig.

- Kommunikation mit der **TMDB API** über Retrofit
- Abbildung von API-Antworten (DTOs) auf Domain-Modelle (Mapping)
- Implementierung der in der Domain-Schicht definierten Repository-Interfaces
- Vorbereitung für lokale Datenhaltung (z. B. Favoriten mit Room)

---

## Datenfluss

MovieVault folgt einem **unidirektionalen Datenfluss**:

1. Die UI löst eine Aktion aus (z. B. Laden eines Screens)
2. Das ViewModel fordert Daten über ein Repository an
3. Das Repository lädt die Daten aus der Data-Schicht
4. API-Antworten werden in Domain-Modelle gemappt
5. Das ViewModel aktualisiert den UI-State
6. Die UI reagiert auf State-Änderungen und recomposed sich automatisch

Dieser Ansatz sorgt für eine klare Trennung der Verantwortlichkeiten
und eine gut testbare Struktur.

---

## Projektstatus

MovieVault befindet sich aktuell in aktiver Entwicklung.

- **Home** und **Detail** sind funktional umgesetzt
- **Search** und **Favorites** sind derzeit Platzhalter
- Die Architektur ist so ausgelegt, dass neue Features
  (z. B. Pagination, Favoriten mit Room, Offline-Caching)
  ohne größere Refactorings integriert werden können.