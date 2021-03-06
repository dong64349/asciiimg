package org.gadget.image.imgchar.component.util;

import org.gadget.image.imgchar.component.ImgMatrixData;

import java.awt.image.BufferedImage;

class PixelHandler {

    /**
     * 遍历像素，获取A、R、G、B 的值
     *
     * @param buff 图像数据
     * @return ARGB 矩阵
     */
    static ImgMatrixData getPixelMatrix(BufferedImage buff) {
        ImgMatrixData data = new ImgMatrixData(buff.getWidth(), buff.getHeight());
        // 扫描像素点，取AR、G、B 值
        for (int y = 0; y < buff.getHeight(); y++) {
            for (int x = 0; x < buff.getWidth(); x++) {
                int pixel = buff.getRGB(x, y);
                data.red[y][x] = (pixel & 0xff0000) >> 16;
                data.green[y][x] = (pixel & 0xff00) >> 8;
                data.blue[y][x] = pixel & 0xff;
            }
        }
        return data;
    }

    /**
     * 获取灰度矩阵
     *
     * @param img 图像数据
     */
    static void getGSMatrix(ImgMatrixData img) throws Exception {
        for (int y = 0; y < img.height; y++) {
            for (int x = 0; x < img.width; x++) {
                int red = img.red[y][x];
                int blue = img.blue[y][x];
                int green = img.green[y][x];
                // Gray = R*0.299 + G*0.587 + B*0.114
                int gs = (int) Math.round(red * 0.299 + green * 0.587 + blue * 0.114);
                if (gs >= 0) {
                    img.gray[y][x] = gs;
                } else {
                    throw new Exception("图像异常！");
                }
            }// x of width
        }// y of height
    }
}
