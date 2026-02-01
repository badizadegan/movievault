# Architektur

MovieVault ist eine Android-Anwendung, die mit Jetpack Compose entwickelt wird.
Das Projekt folgt einer schichtbasierten Architektur, die an Clean Architecture
angelehnt ist, und nutzt das MVVM-Pattern für das UI-State-Management.

Der aktuelle Fokus liegt auf einer sauberen Struktur,
klaren Verantwortlichkeiten und einem vorhersehbaren Datenfluss.
Einige Funktionen befinden sich noch in Entwicklung.

## Architekturüberblick

Das Projekt ist in drei Hauptschichten unterteilt:

- UI-Schicht (Presentation Layer)
- Domain-Schicht
- Data-Schicht

Jede Schicht hat eine klar definierte Aufgabe und kommuniziert
nur mit den jeweils vorgesehenen Nachbarschichten.

## Schichten und Verantwortlichkeiten

### UI-Schicht
Die UI-Schicht ist für die Darstellung der Benutzeroberfläche
und die Verarbeitung von Benutzerinteraktionen zuständig.

- Umsetzung mit Jetpack Compose
- Screens beobachten den von den ViewModels bereitgestellten UI-State
- Navigationslogik ist von UI-Komponenten getrennt

### Domain-Schicht
Die Domain-Schicht enthält die zentrale Logik der Anwendung.

- Definition von Domain-Modellen
- Definition von Repository-Interfaces
- Dient als Vertrag zwischen UI- und Data-Schicht
- Unabhängig vom Android-Framework und von Netzwerkbibliotheken

### Data-Schicht
Die Data-Schicht ist für das Laden und Aufbereiten von Daten zuständig.

- Kommunikation mit der TMDB API
- Abbildung von API-Antworten (DTOs) auf Domain-Modelle
- Implementierung der in der Domain-Schicht definierten Repository-Interfaces

## Datenfluss

Die Anwendung folgt einem unidirektionalen Datenfluss:

1. Die UI löst eine Aktion aus (z. B. Laden eines Screens)
2. Das ViewModel fordert Daten aus der Domain-Schicht an
3. Die Domain-Schicht delegiert die Anfrage an ein Repository
4. Das Repository lädt und mappt die Daten aus der Remote-API
5. Das ViewModel aktualisiert den UI-State
6. Die UI reagiert auf State-Änderungen und recomposed sich

## Projektstatus

MovieVault befindet sich aktuell in aktiver Entwicklung.
Einige Screens (z. B. Suche und Favoriten) sind derzeit Platzhalter
und werden in zukünftigen Iterationen umgesetzt.

Die Architektur ist so ausgelegt, dass diese Erweiterungen
ohne größere Refactorings integriert werden können.