=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D array. The grid in which the mines, flags, and empty spaces are stored is a 2D array. I used a 2D array
     in order to store all the different types of cells. It stores values of type char because each type of cell
     is represented by a different char value.

  2. Recursion. When the player uncovers an empty cell, playTurn recursively calls playTurn on all neighboring cells.
     We have to use recursion, not iteration, because we only want the neighboring cells of the cells that the player
     clicked to be uncovered. For each call of playTurn, the base case is any case where the cell playTurn is called
     on is not an empty cell.

  3. Testable component. I made test cases for different outcomes and edge cases in GameTest.

  4. I/O. The game state is stored in save_state.txt. It stores the char value of each cell, separated by spaces.
          It also stores whether the game has been won, whether the game has been lost, and the number of flags placed
          by the player so that it can display the right amount of mines remaining.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

    1. RunMinesweeper. This is the GUI that constructs the game window and puts together the different frames.
    2. Minesweeper. This is the game itself, which runs all the logic. It changes the board based on user input.
    3. GameGrid. This is the area where the user can click, which updates a Minesweeper object. It then repaints the
    game board based on the array stored in the Minesweeper object. It also updates the number of remaining mines.
    4. InfoScreen. This is the JPanel for my instruction screen. I made a new class to change the layout.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

    When first writing the method for generating exactly 35 mines on the board, I initially stored all existing mine
    spaces in an array. Later, I realized this was inefficient and was messing up my code, because somehow it would
    still generate duplicates. I changed it to utilize a counter and a while loop.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
    If I knew how, I would try to make it so that the mines counter was a separate class and not contained in the
    GameGrid object. Otherwise, I think I tried to make it as neat as I can, even if it's a little messy with all
    the private static final chars in Minesweeper.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

Writing text in GUI:
https://www.tutorialspoint.com/javaexamples/gui_text.htm
https://www.oreilly.com/library/view/java-awt-reference/9781565922402/06_chapter-03.html

Making borders:
https://docs.oracle.com/javase/tutorial/uiswing/components/border.html

Drawing shapes:
https://www.geeksforgeeks.org/draw-polygon-java-applet/

Formatting buttons:
https://www.codejava.net/java-se/swing/jbutton-basic-tutorial-and-examples