package com.mdeng.quiz;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class GeneralTest {

	@Test
	public void integerTest()
	{
		int i =3;
		Integer i3 = i;
		Integer i32 = 3;
		assertTrue(i3==i32);
	}
	
	@Test
	public void integerTest2() throws IOException
	{
		Files.write(Files.createTempDirectory("aa"), "aaa".getBytes());
	}
}
