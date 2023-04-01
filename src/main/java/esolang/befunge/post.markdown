---
layout: post
title:  "Denken in 2 Dimensionen"
date:   2023-01-11 09:27:35 +0200
categories: java junit5 code languages
---

### Die Sprache 'Befunge'

Die Programmiersprache Befunge ist eine Sprache, die auf einem veränderlichen zweidimensionalen Feld operiert. 

Auf diesem Feld sind die einzelnen Befehle der Sprache angeordnet. Ein Zeiger gibt an wo auf diesem Feld der nächste Befehl gelesen und ausgeführt wird.

Der Befehlssatz ist für eine esoterische Sprache schon recht umfangreich. Ich werde versuchen sie zu gruppieren und dann blockweise mit datengetriebenen Tests zu implementieren.

Die numerischen Befehle:

| Befehl  | Semantik                                                                                                                                 |
|---------|------------------------------------------------------------------------------------------------------------------------------------------|
| 0-9     | Schiebe diese Ziffer auf den Stack                                                                                                       |
| +       | Addition: Hole a und b, dann schiebe a+b auf den Stack                                                                                   |
| -       | Subtraktion: Hole a und b, dann schiebe b−a auf den Stack                                                                                |
| *       | Multiplikation: Hole a und b, dann schiebe a·b auf den Stack                                                                             |
| /       | Ganzzahl-Division: Hole a und b, dann schiebe b/a (abgerundet) auf den Stack. Wenn a=0, frage den Benutzer nach dem Ergebnis.            |
| %       | Modulo: Hole a und b, dann schiebe den Rest der Ganzzahl-Division von b/a auf den Stack. Wenn a=0, frage den Benutzer nach dem Ergebnis. |
| !       | Logisches NICHT: Hole den Wert; wenn Null, schiebe 1, sonst Null auf den Stack.                                                          |
| `       | Größer als: Hole a und b, dann schiebe 1 wenn b>a, sonst Null auf den Stack.                                                             |

Die Zeigerbefehle:

| Befehl  | Semantik                                                                                                                                 |
|---------|------------------------------------------------------------------------------------------------------------------------------------------|
| >       | Gehe nach rechts                                                                                                                         |
| <       | Gehe nach links                                                                                                                          |
| ^       | Gehe nach oben                                                                                                                           |
| v       | Gehe nach unten                                                                                                                          |
| ?       | Gehe in zufällige Richtung                                                                                                               |
| _       | Hole einen Wert vom Stack; gehe nach rechts, wenn Wert=0, sonst nach links.                                                              |
| _       | Hole einen Wert vom Stack; gehe nach unten, wenn Wert=0, sonst nach oben.                                                                |

Die String-Befehle:

| Befehl  | Semantik                                                                                                                                 |
|---------|------------------------------------------------------------------------------------------------------------------------------------------|
| "       | Starte String-Modus: Schiebe den ASCII-Wert jedes Zeichens bis zum nächsten " nach oben.                                                 |
| g       | Hole y und x vom Stack, dann schiebe den ASCII-Wert des Zeichens an der Position x/y im Programm auf den Stack.                          |
| p       | Hole y, x und v vom Stack, dann ändere das Zeichen an der Position x/y im Programm zu dem Zeichen mit dem ASCII-Wert v.                  |

Die Stack-Befehle:

| Befehl  | Semantik                                                                                                                                 |
|---------|------------------------------------------------------------------------------------------------------------------------------------------|
| :       | Dupliziere Wert auf der Spitze des Stacks.                                                                                               |
| \       | Vertausche zwei Werte auf der Spitze des Stacks.                                                                                         |
| $       | Hole einen Wert vom Stack.                                                                                                               |
| .       | Hole einen Wert vom Stack und gib ihn als Ganzzahl aus.                                                                                  |
| ,       | Hole einen Wert vom Stack und gib ihn als ASCII-Zeichen aus.                                                                             |

Die Input-Befehle:

| Befehl  | Semantik                                                                                                                                 |
|---------|------------------------------------------------------------------------------------------------------------------------------------------|
| &       | Frage den Benutzer nach einer Zahl und schiebe diese auf den Stack.                                                                      |
| ~       | Frage den Benutzer nach einem Zeichen und schiebe dessen ASCII-Wert auf den Stack.                                                       |

Die restlichen Befehle:

| Befehl  | Semantik                                                                                                                                 |
|---------|------------------------------------------------------------------------------------------------------------------------------------------|
| #       | Trampolin: Übergehe nächste Zelle.                                                                                                       |
| @       | Beende Programm                                                                                                                          |

...

### 0. Die Kata

Die Sprache ist nicht ganz so einfach wie Brainfuck, dafür ist die Darstellung des Codes sehr übersichtlich.

Es soll ein Interpreter für ein Stück Quellcode in der Sprache Befunde geschrieben werden. Ich werde mir aus dem Internet verschiedene Beispielprogramme laden und schauen ob die Ausgabe mit unserer Erwartung übereinstimmt.

### 1. Das System

#### 1.1. Das Feld mit Befehlen und Zeiger

...

```java
```

...

```java
```

#### 1.2. Der Stack

...

```java
```

...

```java
```

### 2. Die numerischen Befehle

...

```java
```

...

```java
```

### 3. Die Die Zeigerbefehle
### 4. Die String-Befehle
### 5. Die Stack-Befehle
### 6. Die Input-Befehle
### 7. Die restlichen Befehle
