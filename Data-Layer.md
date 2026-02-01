# Data Layer

Die Data-Schicht ist für das Laden von Filmdaten aus externen Quellen
und deren Aufbereitung für die Domain-Schicht verantwortlich.

In MovieVault werden die Daten aktuell ausschließlich aus einer
Remote-API (TMDB) geladen.

## Verantwortlichkeiten

- Kommunikation mit der externen API
- Abbildung von API-Antworten in DTOs
- Mapping von DTOs zu Domain-Modellen
- Implementierung der Repository-Interfaces aus der Domain-Schicht
- Abschirmung der Domain-Schicht von Netzwerk- und Framework-Details

## Struktur

data/
├── remote/
│   ├── TmdbApi.kt
│   ├── RetrofitClient.kt
│   ├── PopularMoviesResponse.kt
│   ├── MovieDetailsDto.kt
│   ├── CreditsDto.kt
│   ├── MovieMappers.kt
│   └── DetailsMappers.kt
└── repository/

## Remote API

Die Kommunikation mit der TMDB API erfolgt über Retrofit.
Die API-Interfaces definieren die verfügbaren Endpunkte
(z. B. Trending Movies, Popular Movies, Movie Details).

Netzwerk-Logging wird über einen OkHttp Logging Interceptor unterstützt,
um Requests und Responses während der Entwicklung nachvollziehen zu können.

## DTOs und Mapping

Die Antworten der API werden zunächst in DTOs (Data Transfer Objects) abgebildet.
Diese DTOs entsprechen der Struktur der API-Antworten.

Über dedizierte Mapper-Funktionen werden die DTOs anschließend
in Domain-Modelle umgewandelt.

Dadurch bleibt die Domain-Schicht unabhängig von der API-Struktur
und kann stabil bleiben, selbst wenn sich die API ändert.

## Repository-Implementierung

Repositories fungieren als Vermittler zwischen Data- und Domain-Schicht.

- Sie implementieren die Repository-Interfaces aus der Domain-Schicht
- Sie kapseln die Logik zum Laden und Mappen der Daten
- Die UI- und Domain-Schicht greifen niemals direkt auf Retrofit oder DTOs zu

Aktuell werden alle Daten aus der Remote-Quelle geladen.
Eine Erweiterung um lokale Datenquellen (z. B. Caching oder Favoriten)
ist für zukünftige Versionen vorgesehen.