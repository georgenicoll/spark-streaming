package org.monkeynuthead.spark

import junit.framework.TestSuite
import org.apache.spark.{SparkContext, SparkConf}
import org.scalatest.{Suite, BeforeAndAfterAll}

trait SparkContextSpec extends BeforeAndAfterAll { self: Suite =>

  val Master = "local[*]"
  var context: Option[SparkContext] = None

  override protected def beforeAll(): Unit = {
    super.beforeAll()
    val conf = new SparkConf().setAppName(getClass.getName()).setMaster(Master)
    context = Some(new SparkContext(conf))
  }

  override protected def afterAll(): Unit = {
    context.foreach(_.stop())
    super.afterAll()
  }
}
