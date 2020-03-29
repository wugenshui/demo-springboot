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
        allMaps.add(new AllMap(23.1466403500, 113.3416945400,
                23.1440792900, 113.3471489000,
                23.1497583300, 113.3537030700));
        // 广州塔
        allMaps.add(new AllMap(23.1090977200, 113.3190660600,
                23.1064700000, 113.3244600000,
                23.1123809800, 113.3309751400));
        // 花城汇
        allMaps.add(new AllMap(23.1227614600, 113.3192433000,
                23.1201380000, 113.3246380000,
                23.1260471300, 113.3311448400));
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
    public void outOfChinaTest() {
        double lon = 113.34736228460538;
        double lat = 23.144000709888573;
        double[] a = GpsConvertUtil.gcj02ToWgs84(lat, lon);
        System.out.println(a[0]);
        System.out.println(a[1]);


        List<double[]> points = new ArrayList<>();
        points.add(new double[]{113.34712152, 23.14410896});
        points.add(new double[]{113.3244600000, 23.1064700000});
        for (double[] point : points) {
            Assert.assertFalse(GpsConvertUtil.outOfChina(point[0], point[1]));
        }
    }

    @Test
    public void mercatorTransTest() {
        double[] mercator = GpsConvertUtil.lngLatToMercator(113.3473512844152, 23.14398871029491);
        Assert.assertEquals(12617769.427747251, mercator[0], 0);
        Assert.assertEquals(2649440.948904634, mercator[1], 0);
    }
}
