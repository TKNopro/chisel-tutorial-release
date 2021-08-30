package axifabric

import chisel3._
import chisel3.util._

//the defination of the interface of axi is defined as a slave write interface

class RChannel extends Bundle with AxiConfig{
  val id    = Output(UInt(idWidth.W))
  val resp  = Output(UInt(respWidth.W))
  val user  = Output(UInt(userWidth.W))
}