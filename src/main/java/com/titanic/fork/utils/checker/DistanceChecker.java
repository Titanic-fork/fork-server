package com.titanic.fork.utils.checker;

public interface DistanceChecker {

    double getDistance(double lat1, double lon1, double lat2, double lon2);
    boolean isPossible(double distance);

    /**
     * decimal 각도를 radians으로 바꿔주는 메서드
     */
    static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**
     * radians을 decimal 각도로 바꿔주는 메서드
     */
    static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
