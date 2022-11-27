package net.chugun.h3

import com.uber.h3core.H3Core

object H3 {
  val core: H3Core = H3Core.newInstance()
}