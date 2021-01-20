package com;

import java.util.List;

import com.huaban.analysis.jieba.JiebaSegmenter;

public class JebiaTest {

	public static void main(String args[]) {
		JiebaSegmenter segmenter = new JiebaSegmenter();
		final String string = "英文口說";
		System.out.println(segmenter.sentenceProcess(string));
	}
}
