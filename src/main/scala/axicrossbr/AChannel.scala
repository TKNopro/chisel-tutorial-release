package axifabric

import chisel3._
import chisel3.util._


//the defination of the interface of axi is defined as a slave write interface

class AChannel extends Bundle with AxiConfig{
  val id      = Input(UInt(idWidth.W))
  val addr    = Input(UInt(addrWidth.W))
  val len     = Input(UInt(lenWidth.W))
  val size    = Input(UInt(sizeWdith.W))
  val burst   = Input(UInt(burstWidth.W))
  val lock    = Input(UInt(lockWidth.W))
  val cache   = Input(UInt(cacheWidth.W))
  val prot    = Input(UInt(protWidth.W))
  val qos     = Input(UInt(qosWidth.W))
  val region  = Input(UInt(regionWidth.W))
  val user    = Input(UInt(userWidth.W))
}