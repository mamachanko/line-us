package lineus

import org.scalatest._

class GCodeSpec extends FunSpec with Matchers {

  describe("GCode") {


    describe("when creating a rectangle") {
      it("should return instructions for a rectangle") {
        GCode.rect(25, 45, 200, 300) shouldEqual List(
          MoveTo(25, 45),
          MoveToX(25 + 200),
          MoveToY(45 + 300),
          MoveToX(25 - 200),
          MoveToY(45 - 300)
        )
      }
    }

    describe("when creating a polygon") {
      it("should return instructions for a polygon") {
        GCode.polygon((0, 0), (-50, 100), (50, 150)) shouldEqual List(
          MoveTo(0, 0),
          MoveTo(-50, 100),
          MoveTo(50, 150),
          MoveTo(0, 0)
        )
      }
    }

    describe("when drawing a list of moves") {
      it("should lower the arm after the first move and raise it after the last") {
        GCode.draw(List(MoveTo(1, 2), MoveTo(3, 4), MoveTo(5, 6))) shouldEqual List(
          MoveTo(1, 2),
          ArmDown,
          MoveTo(3, 4),
          MoveTo(5, 6),
          ArmUp
        )
      }
    }

    describe("when rendering a list of GCodes") {
      it("returns the list of GCodes") {
        GCode.render(List(ArmDown, MoveTo(1, 2), MoveTo(3, 4), ArmUp): _*) shouldEqual List(
          "G01 Z1000",
          "G01 X1 Y2",
          "G01 X3 Y4",
          "G01 Z0"
        )
      }
    }

    describe("when rendering a rect") {
      it("returns the list of codes") {
        GCode.render(GCode.draw(GCode.rect(800, 1100, 800, 2100)): _*) shouldEqual List(
          "G28",
          "G01 X800 Y1100",
          "G01 Z0",
          "G01 Y-1000",
          "G01 X1600",
          "G01 Y1100",
          "G01 X800",
          "G01 Z1000",
          "G28"
        )
      }
    }
  }

}
