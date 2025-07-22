# Shop App 🛒

Prosta aplikacja backendowa sklepu internetowego napisana w Spring Boot z wykorzystaniem PostgreSQL

## 🔧 Technologie
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Docker (baza danych)

## 📦 Funkcjonalności
- Dodawanie produktu do koszyka
- Pobieranie koszyka na podstawie ID użytkownika
- Usuwanie produktów z koszyka, aktualizacja ilości

## 🚀 Jak uruchomić

1. Upewnij się, że masz Dockera i Java 17
2. Odpal bazę danych z Dockera (np. `postgres:16`)
3. Uruchom aplikację z poziomu IDE lub `./mvnw spring-boot:run`
4. Użyj Postmana, żeby testować endpointy (`localhost:8080`)

## 📬 Przykład endpointu

Dodawanie produktu do koszyka:

POST /api/cart/add
{
    "userId": 1,
    "productId": 5,
    "quantity": 2
}
