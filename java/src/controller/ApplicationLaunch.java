package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;
import models.Heading;
import models.MissionControl;
import models.Plateau;
import models.Position;
import models.Program;
import models.Rover;

public class ApplicationLaunch {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("You need to provide a file input!");
      return;
    }

    String configurationFile = args[0];

    MissionControl missionControl = null;
    Plateau plateau = null;

    try {
      FileReader fileReader = new FileReader(configurationFile);

      BufferedReader bufferedReader = new BufferedReader(fileReader);

      Stream<String> lineByLine = bufferedReader.lines();
      Iterator<String> lineIterator = lineByLine.iterator();

      String plateauLine = lineIterator.next();

      plateau = new Plateau(plateauLine);
      missionControl = new MissionControl(plateau);

      while (lineIterator.hasNext()) {
        String headingPositionLine = lineIterator.next();

        if (!lineIterator.hasNext()) {
          throw new IllegalArgumentException(
            "You're missing a line for a rover"
          );
        }

        String[] splits = headingPositionLine.trim().split(" ");
        Integer xPosition = Integer.parseInt(splits[0]);
        Integer yPosition = Integer.parseInt(splits[1]);

        Position position = new Position(xPosition, yPosition);
        Heading heading = Heading.fromCharacter(splits[2].charAt(0));
        Rover rover = new Rover(position, heading);

        String programLine = lineIterator.next();
        Program program = new Program(programLine);

        if (!missionControl.addRover(program, rover)) {
          System.err.println(
            "Could not add rover, rover already here or out of bounds at " +
            rover.getPosition()
          );
        }
      }

      bufferedReader.close();
    } catch (FileNotFoundException e) {
      System.err.println(
        "Rover/Plateau Configuration Program File Not Found: " + e.getMessage()
      );
    } catch (IOException e) {
      System.err.println("Error Reading File: " + e.getMessage());
    } catch (NumberFormatException e) {
      System.err.println(
        "File contains invalid number for position coordinate"
      );
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    } catch (Exception e) {
      System.err.println("UNEXPECTED ERROR " + e.getMessage());
      e.printStackTrace();
    }

    // There might have been exceptions so missionControl might not have been initialised.
    // Need this to avoid the null pointer exception
    if (missionControl != null) {
      missionControl.go();
    } else {
      System.err.println(
        "Too many errors in the file, not running. Please fix input and try again"
      );
    }
  }
}
