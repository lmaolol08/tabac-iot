# 🚀 IoT Trust-Based ABAC Simulator

![Java](https://img.shields.io/badge/Java-21-blue)
![Maven](https://img.shields.io/badge/Maven-3.9-brightgreen)
![Build](https://img.shields.io/badge/build-passing-success)

A Java-based simulator that implements **Trust-Aware Attribute-Based Access Control (T-ABAC)** for IoT devices.  
Simulates cameras 📷, sensors 📡, and actuators ⚙️ — measures **accuracy ✅, precision 🎯, recall 🔄, and scalability 📊**.

---

## 📌 Features
- 🛰 Simulates multiple IoT devices (camera / sensor / actuator).  
- 🔐 T-ABAC policy: ABAC + dynamic trust scores.  
- 👾 Malicious vs. normal behavior simulation.  
- 📊 Exports metrics and scalability CSVs for plotting.

---

## ⚡ Quick start

```bash
git clone https://github.com/YOUR-USERNAME/iot-trust-abac.git
cd iot-trust-abac
mvn clean compile
mvn exec:java






📊 Scalability Experiment Results
Devices Accuracy Precision Recall Time(ms)
10      0.92     0.90      0.95   25
100     0.89     0.86      0.92   142
500     0.87     0.84      0.90   920
1000    0.85     0.82      0.89   2100

✅ Results saved to scalability_results.csv






src/main/java/iot/
  ├── devices/
  ├── accesscontrol/
  ├── trust/
  ├── simulation/
  └── utils/


