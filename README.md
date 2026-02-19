# ⭐ AfiaPass — The Logistics Trust Engine for Nigerian Roads

**AfiaPass** is a high-performance, decentralized middleware infrastructure built on the **Stellar blockchain**. It enables logistics platforms, food-tech startups (like **Drive-Thru Afia**), and government agencies to generate immutable, on-chain transit permits.

By leveraging **Soroban smart contracts**, **AfiaPass** automates the complex "Single Point of Payment" model, programmatically splitting levies between Local Government Areas (LGAs), transport unions, and operators in 3–5 seconds.

### 🔑 Quick Summary

| Property | Value |
| :--- | :--- |
| **Project Name** | **AfiaPass Java SDK** |
| **Goal** | Eliminate checkpoint friction & harmonize Nigerian logistics taxes. |
| **Blockchain** | **Stellar Network** |
| **Smart Contracts** | **Soroban (Rust/WASM)** |
| **Backend Stack** | **Java 21** (Project Loom / Virtual Threads) |
| **Architecture** | **Hexagonal (Ports & Adapters)** |
| **Trust Model** | **Hybrid Web2 + Web3 (SEP-10 JWT)** |
| **Monorepo Manager** | **Maven** |

---

### 🌐 What AfiaPass Solves
The Nigerian logistics sector faces systemic "wicked problems" that **AfiaPass** addresses through cryptographic trust:

* ✅ **Tamper-proof Transit Permits**: Prevents forgery of physical paper receipts at checkpoints.
* ✅ **Automated Levy Splitting**: Instantly routes funds to multiple government/union wallets via Soroban, removing "informal" middle-men.
* ✅ **Offline-First Verification**: Uses signed **Ed25519 JWTs** to allow officials to verify permits in remote areas with zero network coverage.
* ✅ **High-Concurrency Signing**: Leveraging Java 21's Virtual Threads to handle massive spikes in permit issuance without system lag.

---

### 🚀 Core Architecture
**AfiaPass** utilizes a **Hexagonal** design (also known as Ports & Adapters) to ensure that core logistics logic remains independent of blockchain volatility or external library changes.

![Hexagonal Architecture Blueprint](https://via.placeholder.com/800x400.png?text=Hexagonal+Architecture+-+Ports+%26+Adapters)

### 🏗️ Project Structure
```text
afiapass-java-sdk/
├── src/main/java/com/twohorsemen/afiapass/
│   ├── core/                  <-- The "Hexagon" (Pure Java Logic)
│   │   ├── domain/            
│   │   │   ├── model/         <-- Permit, Rider, Route entities
│   │   │   ├── exception/     <-- Business-specific exceptions
│   │   │   └── service/       <-- PermitService (The Brain)
│   │   └── ports/             
│   │       ├── inbound/       <-- IssuePermitUseCase interface
│   │       └── outbound/      <-- BlockchainProvider, TokenSigner interfaces
│   │
│   ├── infrastructure/        <-- The "Adapters" (The Outside World)
│   │   ├── blockchain/        
│   │   │   ├── stellar/       <-- StellarAdapter (Implementation)
│   │   │   └── soroban/       <-- SorobanContractClient
│   │   ├── security/          
│   │   │   └── jwt/           <-- NimbusJwtSigner (Implementation)
│   │   └── config/            <-- SDK Configuration (RPC URLs, Keys)
│   │
│   └── api/                   <-- The Public SDK Surface
│       ├── AfiaPassClient.java <-- The main entry point for users
│       └── dto/               <-- Request/Response objects
│
├── src/test/java/             <-- Unit & Integration tests
└── pom.xml                    <-- Maven Dependencies


```
⚙️ Key Features
📂 Automated Tax-Splitting (Soroban)

    On-Chain Settlement: When a rider pays for a permit, the Soroban contract mathematically splits the NGNC stablecoin.

    Wallet Routing: Precision routing to LGA, Union, and Operator wallets defined at the contract level.

🔐 Offline-First Verification (SEP-10)

    Cryptographic Proof: The SDK generates a base64-encoded token containing the permit hash and expiry.

    Edge Validation: Officials scan a QR code; the app validates the signature locally against the AfiaPass Public Key.

🧠 Trusted Compute & Failover

    Multi-RPC Failover: Automatically switches between Stellar RPC nodes if the primary provider experiences latency.

    Project Loom: Non-blocking I/O ensures your main application thread is never stuck waiting for a ledger close.

🧪 Manifest-First Verification Design

Each permit is driven by a flexible JSON manifest to enable unlimited logistics use cases, from food delivery to interstate haulage:
JSON

{
  "riderId": "AFIA-001",
  "route": "LAG-IBD-EXPRESS",
  "timestamp": "2026-02-19T10:00:00Z",
  "metadata": {
    "vehiclePlate": "ABC-123-XY",
    "deliveryType": "DailyDrop"
  }
}

⚡ Getting Started

Prerequisites

    Java 21

    Maven 3.9+

    Stellar CLI

Installation
Bash
<pre>
    git clone [https://github.com/TheTwoHorsemen/afiapass-java-sdk.git](https://github.com/TheTwoHorsemen/afiapass-java-sdk.git)
    cd afiapass-java-sdk
    mvn clean install
</pre>

🗺️ Roadmap

    Phase 0: Architecture Design & Hexagonal structure setup.

    Phase 1: MVP logic implementation for Drive-Thru Afia pilot in Lagos.

    Phase 2: Multi-RPC failover and developer SDK public release.

    Phase 3: Automated tax-split governance dashboard for State Revenue agencies.

❤️ Vision

AfiaPass aims to become the universal authenticity and tax layer for the Nigerian logistics ecosystem, enabling trust and transparency through the power of the Stellar blockchain.
