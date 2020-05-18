# Java 8 s2i builder image

This is a basic s2i builder image that let's you build Java 8 compatible projects using gradle.


Requirements:
 - Your project needs to contain the grale wrapper (gradlew)
 - Your build.gradle needs to contain a task called "fatjar" that outputs a all in one jar file to build/libs/
