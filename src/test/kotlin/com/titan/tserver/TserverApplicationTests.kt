package com.titan.tserver

import com.titan.tserver.util.FileUtil
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.io.File

@RunWith(SpringRunner::class)
@SpringBootTest
class TserverApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun encodeBase64File(){
		try {

			val base64Code = FileUtil.encodeBase64File("/Users/whs/Downloads/images/图层渲染.png")
			println(base64Code)
			FileUtil.decoderBase64File(base64Code, "/Users/whs/Downloads/images/图层渲染_1.png")
			//FileUtil.toFile(base64Code, "D:\\three.txt")
		} catch (e: Exception) {
			//e.printStackTrace()
            println("异常"+e)
			//System.out.print("异常"+e)

		}
	}





}
