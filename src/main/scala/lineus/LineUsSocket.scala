package lineus

import java.io.PrintStream
import java.net.{InetAddress, Socket}

import scala.io.BufferedSource

class LineUsSocket {
  private val PORT = 1337
  private val ADDRESS = "line-us.local"

  private val lineUs = new Socket(InetAddress.getByName(ADDRESS), PORT)

  def send(command: String): Unit = {
    val out = new PrintStream(lineUs.getOutputStream())
    out.println(command)
  }

  def receive(): String = {
    lazy val in = new BufferedSource(lineUs.getInputStream()).getLines()
    in.next()
  }
}