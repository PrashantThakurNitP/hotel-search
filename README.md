# 🏨 Search Service - Hotel Booking Platform

This microservice provides hotel search functionality using **Elasticsearch** and **Kafka**. It exposes REST APIs for searching hotels by city or using advanced filters. It listens to Kafka events for hotel and room creation, and indexes them into Elasticsearch.

---

## 🚀 Tech Stack

- **Spring Boot**
- **Java 17+**
- **Apache Kafka**
- **Elasticsearch**
- **Swagger / OpenAPI 3**
- **Lombok**

---

## 📦 Features

- ✅ Listens to Kafka topics (`hotel-create`, `room-create`) for indexing.
- ✅ Exposes city-based and filter-based search APIs.
- ✅ Uses Swagger UI for interactive API docs.
- ✅ Fast and flexible search powered by Elasticsearch.

---

## 🔄 Kafka Integration

| Kafka Topic         | Event Type        | Description                        |
|---------------------|-------------------|------------------------------------|
| `hotel-create-topic`| `HotelCreatedEvent` | Indexes a new hotel into ES        |
| `room-create-topic` | `RoomCreatedEvent`  | Adds room data to indexed hotel    |

---

## 🔎 Search APIs

### 1. 🏙️ Search Hotels by City

- **Method:** `GET`
- **Endpoint:** `/v1/search?city={city}`
- **Description:** `Returns hotels available in the specified city.`

### 2. 🧾 Search Hotels with Filters

- **Method:** `POST`

- **Endpoint:** `/v1/search`

- **Content-Type:** `application/json`

- **Description:** `Filters hotels by city, name, and minimum rating.`

