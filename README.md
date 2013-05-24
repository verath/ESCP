#PsychoHero
A Java based 2D top-down shooter game demo for desktop. Developed for the course TDA367, "Objektorienterat programmeringsprojekt".

## Story
You play as the _Hero_, an office worker who one day had enough of his job and turned insane. Your goal is to help the _Hero_ eliminate all his annoying co-workers and ultimately finish off his boss once and for all.

To help with the quest, the _Hero_ brought all of his personal weapons;
* His mighty fists, stronger than steel, tougher than titanium.
* His magic axe, blessed by the mighty gods to provide health when stuck against another being.
* His trusty pistol, packed with bullets. Lots and lots of bullets.

## Game Features
### Score
The game features a time based score system, giving the player only a few minutes to successfully complete the game. After a successful attempt the user's score is also tracked on a local high score.
### Rooms
There are multiple rooms in the game, each packed with fearless workers that has to be disposed of. Experience the exiting world outside the office, the crowded cubical space and the dark dangers of the water closet!
### Original Music
Two all new stunning tracks composed by Erik Tholén will keep the user engaged throughout the entirety of the game. Highly authentic sound effects will also draw the user in to this exciting new world.

## Options
*PsychoHero* provides the user with various customization options including, but not limited to;
* Enabling disabling of both the music and sound effects
* Toggle displaying the game in full screen mode
* Fully rebindable keyboard keys.

Not only is the user presented with these options, but the application will also remember these settings between game sessions.

## Implementation
*PsychoHero* is written in Java 7. The goal of the project was to make a game built on the [MVC-pattern](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller). The primary IDE used was Eclipse, and Git was used for source control.
### External libraries
*PsychoHero* uses a couple of external libraries;
* Slick2D, http://www.slick2d.org/
* LWJGL, http://www.lwjgl.org/
* Commons DbUtils: JDBC Utility Component, http://commons.apache.org/proper/commons-dbutils/
* SQLite JDBC Driver, https://bitbucket.org/xerial/sqlite-jdbc
