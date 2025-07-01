# Medical-Records-Databasee

A modular Java-based system for managing patients, prescriptions, and drug contraindications. This project is designed as a lightweight foundation for healthcare applications, emphasizing clean object-oriented design, efficient data structures, and test-driven development.

---

## 💡 Features

- 📋 **Patient Identity Management**  
  Store and compare patients using immutable `PatientIdentity` objects composed of name and date of birth.

- 💊 **Prescription Tracking**  
  Maintain chronologically ordered prescriptions using a custom `PrescriptionList` linked list.

- 🔍 **Patient Search & Deduplication**  
  `BinarySearchTree<PatientIdentity>` enables fast insertions and lookups, with duplicate prevention.

- ⚠️ **Drug Interaction Detection**  
  Lightweight hash table implementation to store and check known contraindications, with support for multiple hash strategies.

- 🧪 **Unit Testing Support**  
  Comprehensive test suite validates core functionality and iterator behavior.

---
