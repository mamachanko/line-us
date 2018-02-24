package lineus

import java.net._
import java.io._
import scala.io._



object LineUsApp extends App {

  val s = new LineUsSocket

  GCode.rect()

  X800 Y1100

  //  receive()
  //
  //  Thread.sleep(1000)
  //
  //  send("G28")
  //  receive()
  //
  //  send("G01 X800 Y1100 Z1000")
  //  receive()
  //
  //  send("G01 Z0")
  //  receive()
  //
  //  send("G01 Y-1000")
  //  receive()
  //
  //  send("G01 X1600")
  //  receive()
  //
  //  send("G01 Y1100")
  //  receive()
  //
  //  send("G01 X800")
  //  receive()
  //
  //  send("G01 Z1000")
  //  receive()
  //
  //  send("G28")
  //  receive()
  //
  //  Thread.sleep(1000)
  //  lineUs.close()
  //
}