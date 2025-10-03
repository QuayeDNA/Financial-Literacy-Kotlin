# Gamified Financial Literacy Mobile App

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A mobile application designed to teach financial literacy through gamification, making learning fun and engaging for users of all ages.

## 📋 Table of Contents

- [About](#about)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## About

This project aims to empower users with essential financial knowledge by integrating game mechanics such as challenges, rewards, and progress tracking. The app covers key topics like budgeting, saving, investing, and debt management.

The application targets Android users aged 16-45, including students, early-career professionals, and young families looking to improve their financial habits in an enjoyable way.

For detailed product requirements, see [docs/PRD.md](docs/PRD.md).  
For technical specifications and implementation details, see [docs/JSON_PROMPT.json](docs/JSON_PROMPT.json).

## Features

- **User Authentication & Onboarding**: Secure registration with email/password or Google Sign-In, personalized onboarding flow
- **Educational Modules**: Interactive bite-sized lessons organized by financial topics with quizzes and progressive unlocking
- **Budget Tracking Tool**: Comprehensive expense/income tracking with visual dashboards, AI tips, and export functionality
- **Gamification System**: XP points, levels, badges, daily streaks, leaderboards, and savings challenges
- **Goal Setting & Tracking**: SMART financial goals with visual progress indicators and milestone celebrations
- **Personalized Financial Advice**: AI-driven tips based on user behavior and spending patterns
- **Social & Community Features**: Friend system, community forum, and achievement sharing
- **Profile & Settings**: User profile management, customizable themes, and notification preferences

## Technology Stack

- **Platform**: Android (API 24+)
- **Primary Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **UI Framework**: Jetpack Compose with Material Design 3
- **Backend Services**:
  - Firebase Authentication (Email/Password, Google Sign-In)
  - Firebase Firestore (Database)
  - Firebase Analytics
  - Firebase Cloud Functions
  - Firebase Cloud Storage
- **Local Storage**: Room Database
- **Networking**: Retrofit 2 with OkHttp
- **Dependency Injection**: Hilt (Dagger)
- **Async Operations**: Kotlin Coroutines + Flow
- **Navigation**: Jetpack Navigation Component
- **Image Loading**: Coil

## 🚀 Installation

### Prerequisites

- Android Studio (latest stable version)
- JDK 11 or higher
- Android SDK API 34
- Firebase project configured

### Setup Steps

1. **Clone the repository**:

   ```bash
   git clone https://github.com/QuayeDNA/Financial-Literacy-Kotlin.git
   cd Financial-Literacy-Kotlin
   ```

2. **Open in Android Studio**:

   - Launch Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the cloned directory and select it

3. **Configure Firebase**:

   - Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
   - Enable Authentication, Firestore, Storage, and Analytics
   - Download `google-services.json` and place it in the `app/` directory
   - See [FIREBASE_SETUP.md](FIREBASE_SETUP.md) for detailed instructions

4. **Build the project**:
   - Sync Gradle files
   - Build > Make Project
   - Run on device or emulator

## Usage

1. **Registration/Login**: Create an account or sign in with Google
2. **Onboarding**: Complete the personalized onboarding flow
3. **Learning**: Browse and complete educational modules with quizzes
4. **Budget Tracking**: Log expenses and income, view analytics
5. **Goal Setting**: Create and track financial goals
6. **Gamification**: Earn XP, unlock badges, maintain streaks
7. **Community**: Connect with friends and view leaderboards

### Key User Flows

- Daily login streak maintenance
- Lesson completion with quiz scoring
- Expense logging with category selection
- Goal progress tracking with contributions
- Achievement unlocking with celebrations

## Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Guidelines

- Follow Kotlin coding standards
- Write unit tests for new features
- Update documentation as needed
- Ensure UI follows Material Design 3 principles

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For questions or support, please open an issue on GitHub or contact the development team.

---

**Note**: This repository currently contains project documentation and planning materials. The Android application code will be added in upcoming commits following the development roadmap outlined in the PRD.
