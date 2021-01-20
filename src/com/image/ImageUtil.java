package com.image;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	/**
	 * 將�?�形縮�?��?��?�傳，�?��?�發??�錯誤就?��?��??�傳??��?��?? 例�?��?�imageSize??<=1?�無法�?��?��?��?�寬高�?�發??�Exception
	 * 
	 * @param srcImageData
	 *            來�?��?�形資�??
	 * @param scaleSize
	 *            欲�?��?�形縮至多�?�尺寸以下�??50�?表�?�尺寸縮小至50x50以內
	 * @return 縮�?��?�畢??��?�形資�??
	 */
	public static byte[] shrink(byte[] srcImageData, int scaleSize) {
		ByteArrayInputStream bais = new ByteArrayInputStream(srcImageData);
		// 縮�?��?��?��??4�?表除�?4
		int sampleSize = 1;
		int imageWidth = 0;
		int imageHeight = 0;

		// 如�?�imageSize?��0?��?�數(�?表錯�?)???1(1�?表�?��?��?��?�?大�??)，就?��?��??�傳??��?��?��??
		if (scaleSize <= 1) {
			return srcImageData;
		}

		try {
			BufferedImage srcBufferedImage = ImageIO.read(bais);
			// 如�?�無法�?�別??��?�格�?(TYPE_CUSTOM)就�?�傳TYPE_INT_ARGB；否??��?�傳?��??�格�?
			int type = srcBufferedImage.getType() == BufferedImage.TYPE_CUSTOM ? BufferedImage.TYPE_INT_RGB
					: srcBufferedImage.getType();
			imageWidth = srcBufferedImage.getWidth();
			imageHeight = srcBufferedImage.getHeight();
			if (imageWidth == 0 || imageHeight == 0) {
				return srcImageData;
			}
			// ?��要�?��?��?�長??��???��?��?��?��?�長�?(desireSize)，就計�?�欲縮�?��?��?��??
			// 並�?��?��?�寬高都?��以欲縮�?��?��??
			int longer = Math.max(imageWidth, imageHeight);
			if (longer > scaleSize) {
				sampleSize = longer / scaleSize;
				imageWidth = srcBufferedImage.getWidth() / sampleSize;
				imageHeight = srcBufferedImage.getHeight() / sampleSize;
			}
			BufferedImage scaledBufferedImage = new BufferedImage(imageWidth,
					imageHeight, type);
			Graphics graphics = scaledBufferedImage.createGraphics();
			graphics.drawImage(srcBufferedImage, 0, 0, imageWidth, imageHeight,
					null);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(scaledBufferedImage, "jpg", baos);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return srcImageData;
		}
	}
}
