package lineus

import org.scalatest._

class GCodeSpec extends FunSpec with Matchers {

  describe("GCode") {

    describe("when creating a rectangle") {
      it("should return instructions for a rectangle") {
        GCode.rect(0, 0, 200, 300) shouldEqual List(
          Move(0, 0),
          MoveX(200),
          MoveY(300),
          MoveX(-200),
          MoveY(-300)
        )
      }
    }

    describe("when creating a polygon") {
      it("should return instructions for a polygon") {
        GCode.polygon((0, 0), (-50, 100), (50, 150)) shouldEqual List(
          Move(0, 0),
          Move(-50, 100),
          Move(50, 150),
          Move(0, 0)
        )
      }
    }

    describe("when drawing a list of moves") {
      it("should lower the arm before and raise it afterwards") {
        GCode.draw(List(Move(1, 2), Move(3, 4))) shouldEqual List(
          ArmDown,
          Move(1, 2),
          Move(3, 4),
          ArmUp
        )
      }
    }

    describe("when rendering a list of GCodes") {
      it("returns the list of GCodes") {
        GCode.render(List(ArmDown, Move(1, 2), Move(3, 4), ArmUp): _*) shouldEqual List(
          "G01 Z1000",
          "G01 X1 Y2",
          "G01 X3 Y4",
          "G01 Z0"
        )
      }
    }

  }

}
