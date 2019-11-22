import org.scalatest.{FunSuite, Matchers}
import java.io._
import java.math._
import java.security._
import java.text._
import java.util
import java.util._
import java.util.concurrent._
import java.util.function._
import java.util.regex._
import java.util.stream._

class Solution extends FunSuite with Matchers {
  def sockMerchant(n: Int, ar: Array[Int]): Int = {
    val pairs = ar.foldLeft(scala.collection.Map.empty[Int, Int]) { (result, s) =>
      val existing = result.getOrElse(s, 0)
      result.updated(s, existing + 1)
    }

    pairs.values.map(_ / 2).sum
  }

  def countingValleys(n: Int, s: String): Int = {
    var level   = 0
    var valleys = 0
    (0 until n).foreach { i =>
      if (s.charAt(i) == 'U') {
        level += 1
        if (level == 0) valleys += 1
      } else {
        level -= 1
      }
    }

    valleys
  }

  def jumpingOnClouds(c: Array[Int]): Int = {
    val n     = c.length
    var jumps = 0
    var i     = 0
    while (i < n - 1) {
      if (i < n - 2 && c(i + 2) == 0) {
        i = i + 2
      } else i = i + 1
      jumps += 1
    }
    jumps
  }

  def repeatedString(s: String, n: Long): Long = {
    val fulls     = n / s.length
    val remaining = (n % s.length).toInt
    s.count(_ == 'a') * fulls + s.take(remaining).count(_ == 'a')
  }

  //  Array(
  //    Array(-9, -9, -9, 1, 1, 1),
  //    Array(0, -9, 0, 4, 3, 2),
  //    Array(-9, -9, -9, 1, 2, 3),
  //    Array(0, 0, 8, 6, 6, 0),
  //    Array(0, 0, 0, -2, 0, 0),
  //    Array(0, 0, 1, 2, 4, 0)
  //  )
  def hourglassSum(arr: Array[Array[Int]]): Int = {
    var max = Int.MinValue

    (1 to 4).foreach { i =>
      (1 to 4).foreach { j =>
        var sum = arr(i)(j)

        Seq(-1, 1).foreach { row =>
          (-1 to 1).foreach { col =>
            sum += arr(i + row)(j + col)
          }
        }

        if (sum > max) max = sum
      }
    }
    max
  }

  def rotLeft(a: Array[Int], d: Int): Array[Int] = {
    (a ++ a).slice(d, a.length + d)
  }

  def minBribes(q: Array[Int]): String = {
    var bribes = 0

    q.zipWithIndex.foreach {
      case (p, i) =>
        val swaps = p - i - 1
        if (swaps > 2)
          return "Too chaotic"
        val start = Math.max(0, i + swaps - 2)
        bribes += q.slice(start, i).count(_ > p)
    }
    bribes.toString
  }

  def minimumBribes(q: Array[Int]) {
    println(minBribes(q))
  }

  def bonetrousle(n: Long, k: Long, b: Int): Array[Long] = {
    val min = b * (b + 1) / 2
    val max = b * (k - (b - 1) / 2)
    if (n < min || n > max) Array(-1)
    else {
      val stepmin = (n - min) / b
      val stepmax = (max - n) / b
      val start   = 1 + stepmin
      val result  = (start to (start + b - 1)).toArray
      var sum     = min + stepmin * b

      val end       = max - stepmax / b
      val maxResult = ((end - b + 1) to end).toArray

      if (sum == n) return result

      (0 until b).reverse.foreach { i =>
        while (result(i) < maxResult(i)) {
          result.update(i, result(i) + 1)
          sum += 1

          if (sum == n) return result
        }
      }
      Array.empty
    }
  }

  def minimumSwaps(arr: Array[Int]): Int = {
    var i     = 0
    var swaps = 0
    while (i < arr.length) {
      while (arr(i) != i + 1) {
        val iToSwap = arr(i) - 1
        val x       = arr(iToSwap)
        arr.update(iToSwap, arr(i))
        arr.update(i, x)
        swaps += 1
      }
      i += 1
    }
    swaps
  }

  val count = new java.util.HashMap[Char, Int]()

  def checkAnagram(arr1: Array[Char], arr2: Array[Char]): Boolean = {
    count.clear()

    def countChars(arr: Array[Char], modifier: Int): Unit = {
      arr.foreach { c =>
        val existing = count.getOrDefault(c, 0)
        count.put(c, existing + modifier)
      }
    }

    countChars(arr1, +1)
    countChars(arr2, -1)

    count.values().forEach { e =>
      if (e != 0) return false
    }

    true
  }

  def sherlockAndAnagrams(s: String): Int = {
    var result = 0
    val arr    = s.toCharArray
    (1 until arr.length).foreach { size =>
      (0 until (arr.length - size)).foreach { start =>
        ((start + 1) to (arr.length - size)).foreach { revStart =>
          if (checkAnagram(arr.slice(start, start + size), arr.slice(revStart, revStart + size)))
            result += 1
        }
      }
    }
    result
  }

  test("anagram") {
    checkAnagram("afai".toCharArray, "faia".toCharArray) shouldBe true
  }

  test("solution") {
    sherlockAndAnagrams("abba") shouldBe 4
    sherlockAndAnagrams("abcd") shouldBe 0
    sherlockAndAnagrams("ifailuhkqq") shouldBe 3
    sherlockAndAnagrams("kkkk") shouldBe 10
  }
}
