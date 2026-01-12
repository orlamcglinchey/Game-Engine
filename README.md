# moteur_de_jeu

## 2D Moteur de Jeu - LibGDX & Tiled

Ce projet est un 2D Moteur de Jeu, developpe en utilisant la bibliotheque LiGDX et Tiled map editor
Il marche directement depuis Tiled, sans modifier le code Java.
Les entites du jeu sont cofigurees a l'aide de cartes (.tmx) et de tilesets (.tsx)

Le jeu implementé est proche d'un jeu de type RPG avec gestion des collisions, ennemis, bonus et conditions de victoire/defaite. 

## L'utilisation
--------------
Les entites sont placees directement sur la carte avec couche "entities". Chaque tile peut avoir une propriete player/enemy/exit/powerUp qui est definit en "Class" en Tiled.
Les collisions sont gerees avec une couche "collision" qui contient des rectangles.

## Compilation
------------
J'utilise le Graddle Wrapper 
Prerequis: Java JDK 25 / Gradle

Pour compiler (Windows): .\gradlew build 
Pour lancer: .\gradlew lwjgl3:run 

Le projet peut etre importe comme projet Gradle dans un IDE et lance via la classe "Lwjgl3Launcher"


