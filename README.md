# Investment Management System

> A comprehensive Java-based investment portfolio management platform with support for individual and corporate investors, multiple asset types, and automated income calculations.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Technology Stack](#technology-stack)
- [Installation](#installation)
- [Usage](#usage)
- [Architecture](#architecture)
- [Classes and Models](#classes-and-models)
- [License](#license)

## 🎯 Overview

This project implements a robust investment management system designed to handle complex financial operations. It provides a flexible framework for managing investment portfolios, tracking asset performance, and managing investor information across different investment types.

The system supports two investor profiles (individual and corporate), multiple asset categories with configurable income ranges, and automated title rendering calculations based on daily interest rates.

## ✨ Features

- **Multi-Investor Support**
  - Individual Investor (Pessoa Física) with CPF and income bracket classification
  - Corporate Investor (Pessoa Jurídica) with CNPJ and revenue tracking
  - Shared base functionality through inheritance

- **Asset Management**
  - Flexible asset configuration with minimum/maximum application limits
  - Income bracket restrictions and validation
  - Customizable monthly tax rates and investment horizons

- **Investment Titles**
  - Automated title status management (Active, Canceled, Expired)
  - Dynamic income calculation using compound interest formula
  - Support for both individual and corporate investor types
  - Real-time title rendering with validation

- **Income Management**
  - Bonus credit and debit operations
  - Income bracket classification (Regular, Differentiated, Premium)
  - Automatic age/company tenure calculation

- **Data Validation**
  - Comprehensive status tracking through enums
  - Income range validation via income tiers
  - Date-based rendering conditions

## 📁 Project Structure
```
Java-Project/
├── src/
│   └── br/
│       └── edu/
│           └── cs/
│               └── poo/
│                   └── ac/
│                       └── bolsa/
│                           └── entidades/
│                               ├── Endereco.java           # Address entity
│                               ├── Contatos.java           # Contact information
│                               ├── Investidor.java         # Base investor class
│                               ├── InvestidorPessoa.java   # Individual investor
│                               ├── InvestidorEmpresa.java  # Corporate investor
│                               ├── Ativo.java              # Asset class
│                               ├── Titulo.java             # Investment title
│                               ├── StatusTitulo.java       # Title status enum
│                               └── FaixaRenda.java         # Income bracket enum
├── bin/                        # Compiled classes (auto-generated)
├── lib/                        # External dependencies
├── .gitignore                  # Git ignore rules
├── README.md                   # This file
└── .git/                       # Git repository
```

## 🛠️ Technology Stack

- **Language**: Java 8+
- **Date/Time**: `java.time` (LocalDate, Period)
- **Numeric Operations**: `java.math.BigDecimal`
- **IDE**: Visual Studio Code / Eclipse
- **Version Control**: Git
- **Build**: Standard Java compilation

## 💻 Installation

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Git
- Visual Studio Code or Eclipse IDE

### Steps

1. **Clone or initialize the repository**
```bash
   cd Java-Project
   git init
```

2. **Create the package structure**
```bash
   mkdir -p src/br/edu/cs/poo/ac/bolsa/entidades
```

3. **Copy source files**
   - Place all `.java` files in the `entidades` package directory

4. **Compile the project**
```bash
   javac -d bin src/br/edu/cs/poo/ac/bolsa/entidades/*.java
```

## 📖 Usage

### Example: Creating an Individual Investor
```java
import br.edu.cs.poo.ac.bolsa.entidades.*;
import java.time.LocalDate;
import java.math.BigDecimal;

// Create address
Endereco endereco = new Endereco(
    "Rua das Flores, 123",
    "50050-000",
    "123",
    "Apt 456",
    "Brasil",
    "Pernambuco",
    "Recife"
);

// Create contact
Contatos contatos = new Contatos(
    "investor@email.com",
    "8132123456",
    "81999999999",
    "81999999999",
    "João Silva"
);

// Create individual investor
InvestidorPessoa investidor = new InvestidorPessoa(
    "12345678900",                          // CPF
    50000.00,                               // Income
    FaixaRenda.REGULAR,                     // Income bracket
    "João Silva",                           // Name
    endereco,                               // Address
    LocalDate.of(1990, 5, 15),             // Birth date
    new BigDecimal("1000.00"),             // Bonus
    contatos                                // Contact info
);

// Access investor information
System.out.println("Age: " + investidor.getIdade());
System.out.println("Name: " + investidor.getNome());
```

### Example: Creating an Investment Title
```java
// Create asset
Ativo ativo = new Ativo(
    1001,                           // Code
    "Fixed Income Fund",            // Description
    1000.00,                        // Min application
    100000.00,                      // Max application
    0.5,                            // Min monthly rate
    1.5,                            // Max monthly rate
    FaixaRenda.REGULAR,            // Min income bracket
    12                              // Months term
);

// Create title
Titulo titulo = new Titulo(
    investidor,                     // Individual investor
    null,                           // No corporate investor
    ativo,                          // Asset
    new BigDecimal("10000.00"),    // Invested value
    new BigDecimal("10000.00"),    // Current value
    new BigDecimal("0.05"),        // Daily rate
    LocalDate.now(),               // Application date
    LocalDate.now().plusMonths(12),// Expiration date
    null,                          // No last income date yet
    StatusTitulo.ATIVO             // Status
);

// Render income
boolean rendered = titulo.render();
if (rendered) {
    System.out.println("New value: " + titulo.getValorAtual());
}
```

## 🏗️ Architecture

### Class Hierarchy
```
Investidor (Abstract Base)
├── InvestidorPessoa (Individual)
└── InvestidorEmpresa (Corporate)

Enums:
├── StatusTitulo (ATIVO, CANCELADO, VENCIDO)
└── FaixaRenda (REGULAR, DIFERENCIADA, PREMIUM)

Supporting Entities:
├── Endereco
├── Contatos
├── Ativo
└── Titulo
```

### Design Patterns

- **Inheritance**: Investor hierarchy (base class + specializations)
- **Composition**: Titulo uses Investidor, Endereco, Contatos, and Ativo
- **Enum Pattern**: Type-safe status and income range management
- **Value Object**: BigDecimal for financial calculations

## 📊 Classes and Models

### Endereco (Address)
- Represents complete address information
- Attributes: logradouro, cep, numero, complemento, pais, estado, cidade
- Used in composition with Investidor

### Contatos (Contact)
- Manages investor contact information
- Attributes: email, telefoneFixo, telefoneCelular, numeroWhatsApp, nomeParaContato
- Used in composition with Investidor

### Investidor (Base Investor)
- Base class for investor types
- Key methods: getIdade(), creditarBonus(), debitarBonus()
- Protected date access for subclass specialization

### InvestidorPessoa (Individual Investor)
- Extends Investidor
- Attributes: cpf, renda, faixaRenda
- Specialized date method: getDataNascimento()

### InvestidorEmpresa (Corporate Investor)
- Extends Investidor
- Attributes: cnpj, faturamento
- Specialized date method: getDataAbertura()

### Ativo (Asset)
- Investment product definition
- Attributes: codigo, descricao, valorMinimoAplicacao, valorMaximoAplicacao, taxaMensalMinima, taxaMensalMaxima, faixaMinimaPermitida, prazoEmMeses

### Titulo (Investment Title)
- Represents an investment position
- Supports both investor types
- Core method: render() - calculates income based on compound interest formula
- Formula: valorAtual = valorAtual × (1 + TD/100)^DD

### StatusTitulo (Enum)
- States: ATIVO (Active), CANCELADO (Canceled), VENCIDO (Expired)
- Attributes: codigo, descricao

### FaixaRenda (Enum)
- Tiers: REGULAR, DIFERENCIADA, PREMIUM
- Attributes: codigo, descricao, valorInicial, valorFinal

## 🔧 Development Guidelines

### Code Style
- Follow Java naming conventions (camelCase for variables/methods, PascalCase for classes)
- Use meaningful variable names
- Keep methods focused and single-responsibility

### Commits
Use conventional commit format:
```
feat: Add new feature
fix: Bug fix
docs: Documentation changes
refactor: Code refactoring
test: Add tests
chore: Build/tooling changes
```

### Testing
- Test all investor types
- Validate date calculations
- Verify render() conditions
- Test bonus operations

---

**Author**: Diego  
**Institution**: Centro de Informática - UFPE  
**Last Updated**: March 2026
EOF
