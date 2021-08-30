package axifabric

import chisel3._
import chisel3.util._

//the defination of the interface of axi is defined as a slave write interface

class DChannel extends Bundle with AxiConfig{
  val id    = Input(UInt(idWidth.W))
  val data  = Input(UInt(dataWidth.W))
  val strb  = Input(UInt(strbWidth.W))
  val last  = Input(Bool())
  val user  = Input(UInt(userWidth.W))
}