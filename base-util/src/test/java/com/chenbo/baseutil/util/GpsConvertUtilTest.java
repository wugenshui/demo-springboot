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
        // 坐标系(纬度lat,经度lon) wgs-84(0,1) gcj-02(2,3) bd-08(4,5)
        List<AllMap> allMaps = new ArrayList<>();
        // 金星大厦
        allMaps.add(new AllMap(113.34712152, 23.14410896,
                113.347131, 23.144113,
                113.35368518, 23.14979204));
        // 广州塔
        allMaps.add(new AllMap(113.3244465700, 23.1064679000,
                113.3244600000, 23.1064700000,
                113.3309751400, 23.1123809800));
        // 花城汇
        allMaps.add(new AllMap(113.3246238100, 23.1201367700,
                113.3246380000, 23.1201380000,
                113.3311448400, 23.1260471300));
        // 允许的误差
        double delta = 0.001;
        double[] transPoint = null;
        double dValue;

        for (AllMap allMap : allMaps) {
            System.out.println("wgs84ToGcj02");
            transPoint = GpsConvertUtil.wgs84ToGcj02(allMap.wgs.lat, allMap.wgs.lng);
            dValue = transPoint[0] - allMap.gcj.lat;
            System.out.println("纬度lat：" + allMap.wgs.lat + "->" + transPoint[0] + " 误差：" + dValue);
            Assert.assertEquals(transPoint[0], allMap.gcj.lat, delta);
            dValue = transPoint[1] - allMap.gcj.lng;
            System.out.println("经度lon：" + allMap.wgs.lng + "->" + transPoint[1] + " 误差：" + dValue);
            Assert.assertEquals(transPoint[1], allMap.gcj.lng, delta);

            System.out.println("gcj02ToWgs84");
            transPoint = GpsConvertUtil.gcj02ToWgs84(allMap.gcj.lat, allMap.gcj.lng);
            dValue = transPoint[0] - allMap.wgs.lat;
            System.out.println("纬度lat：" + allMap.gcj.lat + "->" + transPoint[0] + " 误差：" + dValue);
            Assert.assertEquals(transPoint[0], allMap.wgs.lat, delta);
            dValue = transPoint[1] - allMap.wgs.lng;
            System.out.println("经度lon：" + allMap.gcj.lng + "->" + transPoint[1] + " 误差：" + dValue);
            Assert.assertEquals(transPoint[1], allMap.wgs.lng, delta);

            System.out.println("gcj02ToBd09");
            transPoint = GpsConvertUtil.gcj02ToBd09(allMap.gcj.lat, allMap.gcj.lng);
            dValue = transPoint[0] - allMap.bd.lat;
            System.out.println("纬度lat：" + allMap.gcj.lat + "->" + transPoint[0] + " 误差：" + dValue);
            Assert.assertEquals(transPoint[0], allMap.bd.lat, delta);
            dValue = transPoint[1] - allMap.bd.lng;
            System.out.println("经度lon：" + allMap.gcj.lng + "->" + transPoint[1] + " 误差：" + dValue);
            Assert.assertEquals(transPoint[1], allMap.bd.lng, delta);

            System.out.println("bd09ToGcj02");
            transPoint = GpsConvertUtil.bd09ToGcj02(allMap.bd.lat, allMap.bd.lng);
            dValue = transPoint[0] - allMap.gcj.lat;
            System.out.println("纬度lat：" + allMap.bd.lat + "->" + transPoint[0] + " 误差：" + dValue);
            Assert.assertEquals(transPoint[0], allMap.gcj.lat, delta);
            dValue = transPoint[1] - allMap.gcj.lng;
            System.out.println("经度lon：" + allMap.bd.lng + "->" + transPoint[1] + " 误差：" + dValue);
            Assert.assertEquals(transPoint[1], allMap.gcj.lng, delta);

            System.out.println("wgs84ToBd09");
            transPoint = GpsConvertUtil.wgs84ToBd09(allMap.wgs.lat, allMap.wgs.lng);
            dValue = transPoint[0] - allMap.bd.lat;
            System.out.println("纬度lat：" + allMap.wgs.lat + "->" + transPoint[0] + " 误差：" + dValue);
            Assert.assertEquals(transPoint[0], allMap.bd.lat, delta);
            dValue = transPoint[1] - allMap.bd.lng;
            System.out.println("经度lon：" + allMap.wgs.lng + "->" + transPoint[1] + " 误差：" + dValue);
            Assert.assertEquals(transPoint[1], allMap.bd.lng, delta);

            System.out.println("bd09ToWgs84");
            transPoint = GpsConvertUtil.bd09ToWgs84(allMap.bd.lat, allMap.bd.lng);
            dValue = transPoint[0] - allMap.wgs.lat;
            System.out.println("纬度lat：" + allMap.bd.lat + "->" + transPoint[0] + " 误差：" + dValue);
            Assert.assertEquals(transPoint[0], allMap.wgs.lat, delta);
            dValue = transPoint[1] - allMap.wgs.lng;
            System.out.println("经度lon：" + allMap.bd.lng + "->" + transPoint[1] + " 误差：" + dValue);
            Assert.assertEquals(transPoint[1], allMap.wgs.lng, delta);

            System.out.println("------------------------------------------------------------");
        }
    }

    @Test
    public void mercatorTransTest() {
        double[] mercator = GpsConvertUtil.lonLatToMercator(113.3473512844152, 23.14398871029491);
        Assert.assertEquals(12617769.427747251, mercator[0], 0);
        Assert.assertEquals(2649440.948904634, mercator[1], 0);
    }

    @Test
    public void distanceTest() {

        double distance = GpsConvertUtil.getDistance(23.14979204, 113.35368518, 23.143511618621396, 113.34764834035583);
        System.out.println("误差" + distance + "米");
    }
}
