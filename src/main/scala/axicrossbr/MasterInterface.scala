package axifabric

import chisel3._
import chisel3.util._

class MasterInterface extends Bundle {
  val aw   = Decoupled(Flipped(new AChannel))
  val w    = Decoupled(Flipped(new DChannel))
  val b    = Decoupled(Flipped(new RChannel))
  val ar   = Decoupled(new AChannel)
  val r    = Decoupled(new DChannel)
}