# Maven Hibernate HQL Project - Transport Entity

## Project Overview
This is a Maven-based Hibernate project that demonstrates:
- **Requirement I**: Inserting records using persistent objects
- **Requirement II**: Viewing all records using HQL without WHERE clause (with named parameters support)

## Project Structure
```
fsad-insemlab/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/klef/fsad/exam/
│   │   │   ├── Transport.java          (Entity Class)
│   │   │   └── ClientDemo.java         (Demo Class with HQL Operations)
│   │   └── resources/
│   │       └── hibernate.cfg.xml       (Hibernate Configuration)
│   └── test/
│       └── java/
└── README.md
```

## Prerequisites
- **Java JDK 1.8 or higher**
- **Maven 3.6 or higher**
- **MySQL Server 5.7 or higher**
- **MySQL JDBC Driver** (included in pom.xml)

## Step 1: Database Setup

### Create the Database
Execute the following SQL command in MySQL:

```sql
CREATE DATABASE fsadexam;
USE fsadexam;
```

The `transport` table will be automatically created when you run the application (via `hbm2ddl.auto=update`).

## Step 2: Update Hibernate Configuration

Edit `src/main/resources/hibernate.cfg.xml`:

```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/fsadexam</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">root</property>
```

Update the username and password if your MySQL credentials are different.

## Step 3: Build the Project

Navigate to the project directory and run:

```bash
mvn clean install
```

This will download all dependencies and build the project.

## Step 4: Run the Application

Execute the ClientDemo class:

```bash
mvn exec:java -Dexec.mainClass="com.klef.fsad.exam.ClientDemo"
```

Or compile and run directly:

```bash
mvn compile
java -cp target/classes:target/dependency/* com.klef.fsad.exam.ClientDemo
```

## Project Components

### 1. Transport.java (Entity Class)
Located in: `src/main/java/com/klef/fsad/exam/Transport.java`

**Properties:**
- `id`: Primary key (auto-generated)
- `name`: Transport name
- `date`: Date of transport
- `status`: Transport status (Active/Inactive)
- `description`: Description
- `vehicleType`: Type of vehicle
- `source`: Source location
- `destination`: Destination location
- `distance`: Distance in miles
- `cost`: Cost of transport
- `driverName`: Name of driver

### 2. ClientDemo.java (Demo Class)
Located in: `src/main/java/com/klef/fsad/exam/ClientDemo.java`

**Methods:**

#### I. insertTransportRecords()
- Demonstrates inserting records using persistent objects
- Creates 5 sample Transport objects
- Uses `session.save()` for persistence
- Commits the transaction

#### II. viewAllTransportsUsingHQL()
- Retrieves all Transport records using HQL
- HQL Query: `FROM Transport t`
- Displays all records in formatted output

#### III. viewAllTransportsWithNamedParameters()
- Demonstrates HQL with named parameters concept
- Shows how to use queries that are scalable
- Uses SELECT with alias mapping

## HQL Features Implemented

### Basic HQL Query
```hql
FROM Transport t
```

### Named Parameters Support
```hql
SELECT NEW map(t.id as id, t.name as name, ...) 
FROM Transport t 
ORDER BY t.id
```

## Output Example

When you run the application, you'll see:

```
=== Hibernate HQL Demo - Transport Entity ===

Step 1: Inserting Transport Records...
✓ Successfully inserted 5 transport records!

Step 2: Viewing All Records Using HQL
========================================
All Transport Records (Using HQL)
========================================

Total Records: 5

ID: 1
Name: Truck-001
Date: [Current Date]
...
```

## Dependencies Used

| Dependency | Version |
|-----------|---------|
| Hibernate Core | 5.6.15.Final |
| MySQL JDBC | 8.0.33 |
| SLF4J | 1.7.36 |
| Logback | 1.2.11 |
| JUnit | 4.13.2 |

## Troubleshooting

### Issue: Connection Refused
**Solution**: Ensure MySQL is running and the connection URL is correct in `hibernate.cfg.xml`

### Issue: Table Not Created
**Solution**: Check that `hibernate.hbm2ddl.auto=update` is set in configuration and ensure the database exists

### Issue: Class Not Found
**Solution**: Run `mvn clean install` to download all dependencies

### Issue: Driver Not Found
**Solution**: Ensure MySQL JDBC driver is in classpath (automatically handled by Maven)

## Key Concepts Demonstrated

1. **Hibernate Entity Mapping**: Annotations-based ORM mapping
2. **Session Management**: SessionFactory and Session handling
3. **Transaction Management**: Begin, commit, and rollback operations
4. **HQL Queries**: FROM clause without WHERE conditions
5. **Named Parameters**: Parameterized query support
6. **Persistent Objects**: Creating and persisting objects to database

## Extension Ideas

- Add UPDATE operations using HQL
- Add DELETE operations with WHERE clause
- Add filtering using named parameters with WHERE clause
- Implement pagination with setFirstResult() and setMaxResults()
- Add aggregate functions (COUNT, SUM, AVG, etc.)

## Notes

- Database name: **fsadexam**
- Package name: **com.klef.fsad.exam**
- All classes use Hibernate 5.x with JPA annotations
- Configuration uses MySQL 5.x Dialect
- Auto-create tables on startup enabled (hbm2ddl.auto=update)

---

**Author**: FSAD Exam Project
**Version**: 1.0
**Last Updated**: March 2026
