Informacioni sistem hotela
🏨 Hotel Management System — OOP1 Project
This repository contains an implementation of an informational system for a hotel, developed as a student project for the Object-Oriented Programming 1 (OOP1) course. The system is implemented in Java (target: Java 17) and provides both console and Swing-based GUI components depending on the chosen build/run configuration.

📌 Features

- **User Roles:** Administrator, Receptionist (Guest Services Agent), Housekeeper, Guest — each with role-specific permissions.
- **Reservations:** Guests can request reservations; receptionists can confirm/reject; statuses: `NA ČEKANJU` (PENDING), `POTVRĐENA` (CONFIRMED), `ODBIJENA` (REJECTED), `OTKAZANA` (CANCELLED).
- **Check-in / Check-out:** Receptionists perform check-in (assign room) and check-out (mark room for cleaning).
- **Automatic Cleaning Assignment:** When a room becomes `SPREMANJE` (CLEANING), the system assigns it to the housekeeper with the least assignments that day.
- **Room Types & Availability:** Guests choose room types (single, double, twin, triple, etc.) and see availability for chosen dates.
- **Additional Services & Pricing:** Support for additional services (breakfast, lunch, dinner, etc.) and date-range price lists. Reservation price is calculated at the time of booking and stored with the reservation.
- **Data Persistence:** CSV-based storage (`data/*.csv`) — human-readable files for employees, guests, rooms, reservations, prices, services.
- **Reports & Charts (optional):** Income/expense reports, housekeeper workload, daily arrivals/departures and occupancy. XChart is included for charting in the GUI.
- **Unit Tests (optional):** Manager classes have unit tests in `src/tests` (if implemented).

📁 Project Structure
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

**How to run (recommended)**
- Open the project in an IDE that supports Java 17 (IntelliJ IDEA or Eclipse).
- Set the project JDK to Java 17.
- Run the `main.Hotel` class.

If you prefer command line compilation, you can compile sources into `bin/` and run the main class (recommended: use IDE to avoid classpath pitfalls):

PowerShell example (basic idea — adjust paths if needed):
```
javac -d bin $(Get-ChildItem -Path src -Recurse -Filter "*.java" | ForEach-Object { $_.FullName })
java -cp bin main.Hotel
```

Note: The PowerShell one-liner above may need adaptation on Windows (depending on your shell and `javac` availability). Using an IDE is the simplest approach.

**Data files**
- The `data/` folder contains CSV files with saved entities. The application reads/writes these files for persistence. Do not delete these files if you want to preserve application state.

**Grading & Requirements (summary)**
- Project developed for OOP1 course (academic year 2023/2024). Target Java 17.
- GUI must use Swing for grades >7; console-only implementations are accepted up to grade 7.
- CSV (human-readable) files are required for persistence; serialisation-based storage limits max grade to 6.
- Optional features for higher grades: Swing GUI, charts (XChart), unit tests for managers, advanced filtering and guest search criteria.

**Tips & Notes**
- If Eclipse autocompletion hides `java.awt` classes, enable them in `Window > Preferences > Java > Appearance > Type Filters` and uncheck `java.awt*`.
- Use WindowBuilder if you prefer — it is allowed.
- For questions about features or to request a Gradle/Maven build script, open an issue or ask here and I can add it.

---

If you'd like this README translated entirely to Serbian, shortened, or expanded with build scripts (Gradle/Maven) and exact compile commands, tell me which option you prefer and I'll update the file.
