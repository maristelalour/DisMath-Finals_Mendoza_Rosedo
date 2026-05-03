=====================================================
  MINI STUDENT RECORDS & CAMPUS MAP
  Discrete Mathematics - Final Project
  De La Salle Lipa | BS Information Technology
=====================================================

ABOUT THIS PROGRAM
------------------
  This program demonstrates three core Discrete Mathematics
  concepts applied in Java:

  1. Binary Search Tree (Trees)
     - Student records are stored and retrieved using a BST,
       where the Student ID serves as the key. In-order
       traversal automatically prints students sorted by ID.

  2. Dijkstra's Shortest Path (Graph Theory)
     - The campus is modeled as a weighted, undirected graph.
       Dijkstra's Algorithm finds the shortest walking distance
       and path between any two buildings on campus.

  3. Quantifiers (Logic)
     - Universal (∀) and Existential (∃) quantifiers are
       implemented as loops that check logical conditions
       across all stored student records.


GROUP MEMBERS
-------------
1. Lourhin Maristela M. Mendoza   | ID: 2022317151
2. John Clarenze M. Rosedo        | ID: 2024373751


FILES INCLUDED
--------------
  Main.java           - Main menu and program entry point
  Student.java        - Student data class (id, name, course)
  StudentBST.java     - Binary Search Tree implementation
  CampusGraph.java    - Campus map + Dijkstra's shortest path
  QuantifierCheck.java- Universal (∀) and Existential (∃) checks
  README.txt          - This file


HOW TO RUN
----------
Option A - Command Line:
  1. Make sure Java is installed (any recent version).
  2. Place all .java files in the same folder.
  3. Open a terminal in that folder and run:
       javac Main.java Student.java StudentBST.java CampusGraph.java QuantifierCheck.java
       java Main

Option B - Online IDE (e.g. onlinegdb.com):
  1. Go to https://www.onlinegdb.com/online_java_compiler
  2. Upload or paste all .java files.
  3. Set language to Java and click Run.


PROGRAM FEATURES
----------------
  [1] Add Student
      - Inserts a student (ID, Name, Course) into a Binary Search Tree.
      - Duplicate IDs are rejected.

  [2] Search Student by ID
      - Searches the BST for a student by their ID.
      - Prints the student info if found, or "not found" if not.

  [3] Display All Students (In-order)
      - Prints all students sorted by ID using in-order BST traversal.

  [4] Find Shortest Path Between Buildings
      - Shows the campus map (5 buildings, 6 connections).
      - Uses Dijkstra's Algorithm to find the shortest path
        between any two buildings.

  [5] Run Logical Check (Quantifier)
      - [A] Universal (∀): checks if ALL students have course BSIT.
      - [B] Existential (∃): checks if ANY student has ID > 1000.

  [0] Exit


VIDEO EXPLANATION
-----------------
  Link: https://drive.google.com/file/d/1xw2GyfpZly9DGbWE7oUDbx-Y1RfHo721/view?usp=drive_link
  (Upload to Google Drive, YouTube Unlisted, or OneDrive
   and paste the link above before submitting.)


NOTES
-----
  - Four sample students are pre-loaded on startup for testing.
  - The program will not crash on invalid input (try/catch and
    if-checks are in place throughout).
=====================================================