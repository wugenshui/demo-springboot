package com.chenbo.baseutil.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-03-25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class GpsConvertUtilTest {
    @Data
    public class MapPoint {
        private double lat;
        private double lng;

        public MapPoint(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }
    }

    @Data
    public class AllMap {
        private MapPoint wgs;
        private MapPoint gcj;
        private MapPoint bd;

        public AllMap(double wgsLat, double wgsLng, double gcjLat, double gcjLng, double bdLat, double bdLng) {
            this.wgs = new MapPoint(wgsLat, wgsLng);
            this.gcj = new MapPoint(gcjLat, gcjLng);
            this.bd = new MapPoint(bdLat, bdLng);
        }
    }

    @Test
    public void gpsTransTest() {
        // 坐标系(纬度lat,经度lon)
        List<AllMap> allMaps = new ArrayList<>();
        // 金星大厦
        allMaps.add(new AllMap(23.14664035, 113.34169454,
                23.14407929, 113.3471489,
                23.14975833, 113.35370307));
        // 广州塔
        allMaps.add(new AllMap(23.10909772, 113.31906606,
                23.10647, 113.32446,
                23.11238098, 113.33097514));
        // 花城汇
        allMaps.add(new AllMap(23.12276146, 113.3192433,
                23.120138, 113.324638,
                23.12604713, 113.33114484));
        // 允许的误差
        double delta = 30;
        double[] transPoint = null;
        double distance;

        for (AllMap allMap : allMaps) {
            System.out.println("wgs84ToGcj02");
            transPoint = GpsConvertUtil.wgs84ToGcj02(allMap.wgs.lat, allMap.wgs.lng);
            distance = GpsConvertUtil.getDistance(allMap.gcj.lat, allMap.gcj.lng, transPoint[0], transPoint[1]);
            System.out.println(allMap.wgs.lat + "," + allMap.wgs.lng + "->" + transPoint[0] + "," + transPoint[1] + " 误差：" + distance + "米");
            Assert.assertTrue(distance < delta);

            System.out.println("wgs84ToBd09");
            transPoint = GpsConvertUtil.wgs84ToBd09(allMap.wgs.lat, allMap.wgs.lng);
            distance = GpsConvertUtil.getDistance(allMap.bd.lat, allMap.bd.lng, transPoint[0], transPoint[1]);
            System.out.println(allMap.wgs.lat + "," + allMap.wgs.lng + "->" + transPoint[0] + "," + transPoint[1] + " 误差：" + distance + "米");
            Assert.assertTrue(distance < delta);

            System.out.println("gcj02ToWgs84");
            transPoint = GpsConvertUtil.gcj02ToWgs84(allMap.gcj.lat, allMap.gcj.lng);
            distance = GpsConvertUtil.getDistance(allMap.wgs.lat, allMap.wgs.lng, transPoint[0], transPoint[1]);
            System.out.println(allMap.gcj.lat + "," + allMap.gcj.lng + "->" + transPoint[0] + "," + transPoint[1] + " 误差：" + distance + "米");
            Assert.assertTrue(distance < delta);

            System.out.println("gcj02ToBd09");
            transPoint = GpsConvertUtil.gcj02ToBd09(allMap.gcj.lat, allMap.gcj.lng);
            distance = GpsConvertUtil.getDistance(allMap.bd.lat, allMap.bd.lng, transPoint[0], transPoint[1]);
            System.out.println(allMap.gcj.lat + "," + allMap.gcj.lng + "->" + transPoint[0] + "," + transPoint[1] + " 误差：" + distance + "米");
            Assert.assertTrue(distance < delta);

            System.out.println("bd09ToGcj02");
            transPoint = GpsConvertUtil.bd09ToGcj02(allMap.bd.lat, allMap.bd.lng);
            distance = GpsConvertUtil.getDistance(allMap.gcj.lat, allMap.gcj.lng, transPoint[0], transPoint[1]);
            System.out.println(allMap.bd.lat + "," + allMap.bd.lng + "->" + transPoint[0] + "," + transPoint[1] + " 误差：" + distance + "米");
            Assert.assertTrue(distance < delta);

            System.out.println("bd09ToWgs84");
            transPoint = GpsConvertUtil.bd09ToWgs84(allMap.bd.lat, allMap.bd.lng);
            distance = GpsConvertUtil.getDistance(allMap.wgs.lat, allMap.wgs.lng, transPoint[0], transPoint[1]);
            System.out.println(allMap.bd.lat + "," + allMap.bd.lng + "->" + transPoint[0] + "," + transPoint[1] + " 误差：" + distance + "米");
            Assert.assertTrue(distance < delta);

            System.out.println("------------------------------------------------------------");
        }
    }

    @Test
    public void mercatorTransTest() {
        double lng = 113.34169454;
        double lat = 23.14664035;
        double mercatorX = 12617139.721839404;
        double mercatorY = 2649761.967173373;

        double delta = 0.000001;

        double[] mercator = GpsConvertUtil.lngLatToMercator(lng, lat);
        System.out.println(mercator[0] + "," + mercator[1]);
        Assert.assertEquals(mercatorX, mercator[0], delta);
        Assert.assertEquals(mercatorY, mercator[1], delta);

        double[] lngLat = GpsConvertUtil.mercatorToLngLat(mercatorX, mercatorY);
        System.out.println(lngLat[0] + "," + lngLat[1]);
        Assert.assertEquals(lng, lngLat[0], delta);
        Assert.assertEquals(lat, lngLat[1], delta);
    }
}