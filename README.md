# Spring Boot Engineer Management API

Une API REST complète développée avec **Spring Boot 4.0.2** pour gérer des ingénieurs logiciels avec des recommandations de parcours d'apprentissage générées par IA.

##  Table des matières
- [Fonctionnalités](#-fonctionnalités)
- [Technologies utilisées](#-technologies-utilisées)
- [Prérequis](#-prérequis)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Utilisation](#-utilisation)
- [Endpoints API](#-endpoints-api)
- [Contribution](#-contribution)

##  Fonctionnalités

-  **CRUD complet** : Créer, lire, mettre à jour et supprimer des ingénieurs logiciels
-  **Intégration IA** : Génération automatique de recommandations d'apprentissage via Ollama (Gemma 2B)
-  **Base de données PostgreSQL** : Persistance des données avec Docker
-  **API RESTful** : Architecture REST standard avec JSON
-  **Containerisation** : Docker Compose pour PostgreSQL
-  **JPA/Hibernate** : ORM pour simplifier les interactions avec la base de données

## 🛠 Technologies utilisées

| Technologie | Version | Usage |
|------------|---------|-------|
| Java | 23 | Langage principal |
| Spring Boot | 4.0.2 | Framework backend |
| Spring Data JPA | 4.0.2 | Persistance des données |
| PostgreSQL | 18.1 | Base de données |
| Docker | Latest | Containerisation |
| Ollama | Latest | Modèle IA local (Gemma 2B) |
| Maven | 3.x | Gestion des dépendances |

##  Prérequis

Avant de commencer, assurez-vous d'avoir installé :

-  **Java JDK 17+** ([Télécharger](https://adoptium.net/))
-  **Docker Desktop** ([Télécharger](https://www.docker.com/products/docker-desktop))
-  **Ollama** ([Télécharger](https://ollama.com/download))
-  **Git** (optionnel)

## 🚀 Installation

### 1. Cloner le repository

git clone https://github.com/VOTRE-USERNAME/spring-boot-engineer-management-api.git
cd spring-boot-engineer-management-api


### 2. Installer Ollama et télécharger le modèle

# Windows PowerShell
ollama pull gemma:2b

# Vérifier l'installation
ollama list


### 3. Lancer PostgreSQL avec Docker

docker compose up -d


Vérifier que le conteneur fonctionne :

docker compose ps


### 4. Créer la base de données

docker exec -it postgres-spring-boot psql -U ammiaya


Puis dans le shell PostgreSQL :

CREATE DATABASE aya_db;
\q


### 5. Compiler et lancer l'application

# Avec Maven
./mvnw clean install
./mvnw spring-boot:run

# Ou avec votre IDE (IntelliJ IDEA)
# Run → Run 'Application'


L'application démarre sur : `http://localhost:8080`

## ⚙️ Configuration

### `application.properties`

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5332/aya_db
spring.datasource.username=ammiaya
spring.datasource.password=password

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Ollama AI Configuration
spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.chat.options.model=gemma:2b
spring.ai.ollama.chat.options.temperature=0.7


## Utilisation

### Endpoints API

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `GET` | `/api/v1/software-engineers` | Récupérer tous les ingénieurs |
| `GET` | `/api/v1/software-engineers/{id}` | Récupérer un ingénieur par ID |
| `POST` | `/api/v1/software-engineers` | Créer un nouvel ingénieur |
| `PUT` | `/api/v1/software-engineers/{id}` | Mettre à jour un ingénieur |
| `DELETE` | `/api/v1/software-engineers/{id}` | Supprimer un ingénieur |

### Exemples de requêtes

####  Créer un ingénieur (avec recommandation IA)

POST http://localhost:8080/api/v1/software-engineers
Content-Type: application/json

{
  "name": "Alice Dupont",
  "techStack": "Java, Spring Boot, PostgreSQL"
}


**Réponse** : L'IA génère automatiquement une recommandation de parcours d'apprentissage personnalisée.

#### 📋Récupérer tous les ingénieurs

GET http://localhost:8080/api/v1/software-engineers


####  Récupérer un ingénieur par ID

GET http://localhost:8080/api/v1/software-engineers/1


####  Mettre à jour un ingénieur

PUT http://localhost:8080/api/v1/software-engineers/1
Content-Type: application/json

{
  "name": "Alice Dupont",
  "techStack": "Java, Spring Boot, Docker, Kubernetes"
}


#### Supprimer un ingénieur

DELETE http://localhost:8080/api/v1/software-engineers/1


##  Structure du projet

engineer-management-api/
├── src/
│   ├── main/
│   │   ├── java/com/tuts/springboot/
│   │   │   ├── Application.java              # Point d'entrée
│   │   │   ├── SoftwareEngineer.java         # Entité JPA
│   │   │   ├── SoftwareEngineerController.java # Contrôleur REST
│   │   │   ├── SoftwareEngineerService.java   # Logique métier
│   │   │   ├── SoftwareEngineerRepository.java # Repository JPA
│   │   │   └── AiService.java                 # Service IA (Ollama)
│   │   └── resources/
│   │       └── application.properties         # Configuration
├── docker-compose.yml                         # Configuration Docker
├── pom.xml                                    # Dépendances Maven
└── README.md


##  Docker

### Commandes utiles

# Démarrer PostgreSQL
docker compose up -d

# Arrêter PostgreSQL
docker compose down

# Voir les logs
docker compose logs -f

# Accéder au shell PostgreSQL
docker exec -it postgres-spring-boot psql -U ammiaya -d aya_db


##  Ollama

### Commandes utiles

# Lister les modèles installés
ollama list

# Tester le modèle
ollama run gemma:2b


##  Tests

Testez l'API avec :
- **IntelliJ HTTP Client** (fichier `requests.http`)
- **Postman**
- **cURL**
- **Thunder Client** (VS Code)

##  Dépannage

### Problème : Erreur de connexion PostgreSQL

# Vérifier que le conteneur est bien lancé
docker compose ps

# Vérifier les logs
docker compose logs postgres-spring-boot
```

### Problème : Ollama - Erreur de mémoire
```bash
# Utiliser un modèle plus léger
ollama pull gemma:2b
ollama pull phi3:mini
```

##  Auteur

**AYA AMMI**
- GitHub: [@AYA-AMMI](https://github.com/AYA-AMMI)


## Remerciements

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Amigoscode Tutorial](https://amigoscode.com)
- [Ollama](https://ollama.com)
- [Docker](https://www.docker.com)

---
