---
layout: post
title:  "Mars Rover Kata"
date:   2021-11-14 14:02:35 +0200
categories: java junit5
---

### Die Aufgabe

Ich habe die Aufgabenstellung aus dem [Google Code Archive](https://code.google.com/archive/p/marsrovertechchallenge/) entnommen und sie mit [deepl](www.DeepL.com/Translator) übersetzt.

Die NASA will eine Gruppe von Roboter-Rovern auf einem Plateau auf dem Mars landen lassen.

Dieses Plateau, das merkwürdigerweise rechteckig ist, muss von den Rovern navigiert werden, damit ihre Bordkameras ein vollständiges Bild des umliegenden Geländes aufnehmen können, um es zur Erde zu senden.

Die Position eines Rovers wird durch eine Kombination aus x- und y-Koordinaten und einem Buchstaben dargestellt, der für eine der vier Himmelsrichtungen steht. Das Plateau ist zur Vereinfachung der Navigation in ein Raster eingeteilt. Eine Beispielposition könnte 0, 0, N sein, was bedeutet, dass sich der Rover in der linken unteren Ecke befindet und nach Norden zeigt.

Um einen Rover zu steuern, sendet die NASA eine einfache Buchstabenfolge. Die möglichen Buchstaben sind 'L', 'R' und 'M'. 'L' und 'R' bewirken, dass sich der Rover um 90 Grad nach links bzw. rechts dreht, ohne sich von seiner aktuellen Position zu entfernen.

'M' bedeutet, dass er sich um einen Rasterpunkt vorwärts bewegt und dabei denselben Kurs beibehält.

### Die Welt und ihre Objekte


