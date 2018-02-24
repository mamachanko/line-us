package lineus

import org.scalatest._

class LineUsSpec extends FunSpec with Matchers {

  describe("LineUs") {
    describe("when executing Gcode") {
      it("returns a list of commands") {
        object LineUs {
          var messages: List[String] = Nil

          def in(): String = {
            messages = messages :+ "recv"
            "ok"
          }

          def out(msg: String): Unit = {
            messages = messages :+ msg
          }
        }

        new LineUs(LineUs.in, LineUs.out).execute(List("command 1", "command 2"): _*)

        LineUs.messages shouldEqual List("recv", "command 1", "recv", "command 2", "recv")
      }
    }
  }

}
