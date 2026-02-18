# AfiaPass Java SDK 🚀 🛡️

## The Unified Logistics & Tax Infrastructure for Nigerian Roads

[![Java Version](https://img.shields.io/badge/Java-21-orange.svg)](https://jdk.java.net/21/)
[![Stellar](https://img.shields.io/badge/Blockchain-Stellar-purple.svg)](https://stellar.org)

## 📦 Overview
The **AfiaPass Java SDK** is a high-performance middleware library designed for Nigerian logistics platforms (like Drive-Thru Afia). It abstracts the complexity of the Stellar blockchain into simple Java methods, allowing developers to issue unforgeable digital transit permits and automate tax splits in seconds.

## ✨ Key Features
- **⚡ Project Loom Integration**: Utilizes Java 21 Virtual Threads for non-blocking transaction signing.
- **🔄 Multi-RPC Failover**: High-availability logic that automatically switches between Stellar RPC providers.
- **🛡️ Offline-First Verification**: Generates cryptographically signed JWTs (SEP-10) for verification in areas with poor network.
- **💸 Automated Tax-Splitting**: Native support for Soroban smart contracts to split payments between LGAs, Unions, and Operators.

## 🛠️ Tech Stack
- **Language**: Java 21
- **Core**: Stellar SDK (Java)
- **Concurrency**: Project Loom (Virtual Threads)
- **Security**: nimbus-jose-jwt / Ed25519 Signatures

## 🚀 Quick Start
```java
// Initialize the client with failover support
AfiaPassClient client = new AfiaPassClient(Config.builder()
    .primaryRpc("[https://soroban-rpc.mainnet.stellar.org](https://soroban-rpc.mainnet.stellar.org)")
    .backupRpc("[https://rpc.thetwohorsemen.com](https://rpc.thetwohorsemen.com)")
    .network(Network.MAINNET)
    .build());

// Issue a permit for a rider
PermitResponse permit = client.issuePermit(
    "RIDER_ID_001", 
    500.00, // Amount in Naira (NGNC)
    "LAGOS-IBADAN-EXPRESS"
).join();

System.out.println("Permit QR Data: " + permit.getOfflineToken());
