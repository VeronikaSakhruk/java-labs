# Labs 9 and 10: Creational patterns, variant 3 "Parcel+"

Lab 9 (Singleton, Prototype, Object Pool), package parcelplus:

    mvn compile exec:java

Lab 10 (Abstract Factory, Factory Method, Builder), package parcelplus.lab10:

    mvn compile exec:java -Dmain.class=parcelplus.lab10.Main

Package and run from the jar:

    mvn clean package
    java -cp target/labs.jar parcelplus.Main
    java -cp target/labs.jar parcelplus.lab10.Main

Diagrams: docs/diagrams/. Peer reviews: docs/PEER_REVIEW.md (lab 9), docs/PEER_REVIEW_LAB10.md (lab 10).
