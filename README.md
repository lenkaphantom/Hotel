# 🏨 Hotel Management System — OOP1 Project
This repository contains an implementation of an informational system for a hotel, developed as a student project for the Object-Oriented Programming 1 (OOP1) course. The system is implemented in Java (target: Java 17) and provides both console and Swing-based GUI components depending on the chosen build/run configuration.

## 📌 Features

- **User Roles:** Administrator, Receptionist (Guest Services Agent), Housekeeper, Guest — each with role-specific permissions.
- **Reservations:** Guests can request reservations; receptionists can confirm/reject; statuses: `NA ČEKANJU` (PENDING), `POTVRĐENA` (CONFIRMED), `ODBIJENA` (REJECTED), `OTKAZANA` (CANCELLED).
- **Check-in / Check-out:** Receptionists perform check-in (assign room) and check-out (mark room for cleaning).
- **Automatic Cleaning Assignment:** When a room becomes `SPREMANJE` (CLEANING), the system assigns it to the housekeeper with the least assignments that day.
- **Room Types & Availability:** Guests choose room types (single, double, twin, triple, etc.) and see availability for chosen dates.
- **Additional Services & Pricing:** Support for additional services (breakfast, lunch, dinner, etc.) and date-range price lists. Reservation price is calculated at the time of booking and stored with the reservation.
- **Data Persistence:** CSV-based storage (`data/*.csv`) — human-readable files for employees, guests, rooms, reservations, prices, services.
- **Reports & Charts (optional):** Income/expense reports, housekeeper workload, daily arrivals/departures and occupancy. XChart is included for charting in the GUI.
- **Unit Tests (optional):** Manager classes have unit tests in `src/tests` (if implemented).

## 📁 Project Structure
```
`/`                     # Project root
├─ `src/`               # Java source files grouped by packages
│  ├─ `main/Hotel.java`           # Program entry point
│  ├─ `controler/`                # Controllers (ReservationControler, RoomControler...)
│  ├─ `entity/`                   # Domain entities (Guest, Room, Reservation...)
│  ├─ `model/`                    # Models for table views and business logic
│  ├─ `manage/`                   # Managers for CRUD operations
│  ├─ `view/`                     # Swing frames and dialogs
│  ├─ `validation/Validation.java`# Input validation utilities
│  └─ `charts/`                   # Chart helpers (XChart wrappers)
├─ `data/`               # CSV data files used for persistence
│  ├─ `rooms.csv`
│  ├─ `room_types.csv`
│  ├─ `reservations.csv`
│  ├─ `guests.csv`
│  ├─ `employees.csv`
│  ├─ `administrators.csv`
│  └─ `additional_services.csv`
├─ `img/`                # Images / icons used by GUI
├─ `jcalendar/`          # External calendar dependency (if used)
└─ `xchart-3.8.8/`       # Charting library used for GUI charts (optional)
```

**Important files**
- `src/main/Hotel.java` — main entry point (run this class in IDE or via `java -cp bin main.Hotel` after compilation)
- `data/*.csv` — persistent data store (CSV format)
